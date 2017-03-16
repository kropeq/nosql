# Projekt z przedmiotu Technologie nosql

### Wybrany przeze mnie zbiór danych:

Nazwa zbioru: **_Twitter Data For Sentiment Analysis_**

Wielkość zbioru: **_1 600 000 tweetów_**

Źródło danych: **_[kliknij mnie!](http://help.sentiment140.com/for-students/)_**



### Parametry komputera testowego

|Jednostka|Parametr|
|------------|:-------------:|
|System|Windows 7 32bit|
|Procesor|Intel(R) Core(TM)2 Quad CPU|
|Ilość rdzeni|4|
|Moc rdzenia|2.66GHz|
|Pamięć RAM|4GB(3.25GB dostępne)|

### Przedstawienie dancyh

Przykładowy rekord( TODO ):

``` json
{
	"pole1": "wartosc1",
	"pole2": "wartosc2"
}
```

### Przykładowe zapytania

Jakich informacji szukam? Przykłady wpisywanych zapytań.(TODO)

### Mapka

Zapytanie mapkowe w celu określania lokalizacji jakichś obiektów.(TODO)

curl localhost:9200/... | jq.hits.hits[] | przerabiamy na GEOJsona za pomocą programu wyszukanego w internecie, np TopoJSON.

Wynik umieszczamy na mapce.

### Czyszczenie danych

Zmiana nazwy pól, jakie pola i dlaczego wybrano te.(TODO)

## Elasticsearch

Mapping - przygotowac i zapisać(TODO)

Import danych:

sh gunzip -c dane.json.gz | ... #całość | #próbka / sample

Policzyć czas ile to zajęło.

## PostgreSQL

### Zadanie 1

Dane: _[Pobierz](https://archive.org/download/stackexchange/gamedev.stackexchange.com.7z)_ (Game Development Posts)

Plik: _Posts.xml_

##### Wstawiamy plik _Posts.xml_ do wspólnego folderu z plikiem _xml_to_csv.class_ i z linii poleceń uruchamiamy konwersję:

```java -cp źródło\pliku xml_to_csv```

#### W wyniku otrzymujemy plik posts.csv, który zawiera "oszczyszczone" dane, a także okrojoną liczbę kolumn. Kolumny to kolejno:

|Id|ViewCount|CreationData|Body|Title|Tags|AnswerCount|CommentCount|
|---|--------|------------|----|-----|----|-----------|------------|

#### Łączenie się z postgresem

```psql -U postgres -h localhost```

#### Uruchomienie pomiarów czasowych

```\timing```

#### Tworzenie bazy danych baza

```CREATE database baza```

#### Czas tworzenia bazy

```5,533938s```

#### Łączenie z bazą danych

```\c baza```

#### Tworzenie schematu

```CREATE SCHEMA schema```

#### Czas tworzenia schematu

```0,000694s```

#### Tworzenie tabeli posts

```CREATE TABLE schema.posts( Id integer, ViewCount integer, CreationDate date, Body varchar, Title varchar, Tags varchar, AnswerCount integer, CommentCount integer)```

#### Czas tworzenia tabeli

```0,214664s```

#### Import danych

```\copy schema.posts FROM 'C:/Users/MINIO/Desktop/TEST/posts.csv' DELIMITER ';' CSV HEADER```

#### Czas importowania

```10,092721s```

#### Liczba danych

```SELECT COUNT(*) FROM schema.posts```

#### Liczba danych

```94185```

#### Czas zliczania rekordów tabeli

```0,089496s```


### Zadanie 2 ( EDA )

Dane: _[Pobierz](https://docs.google.com/uc?id=0B04GJPshIjmPRnZManQwWEdTZjg&export=download)_ (Twitter Data For Sentiment Analysis)

Plik: _training.1600000.processed.noemoticon.csv_

##### Wstawiamy plik _training.1600000.processed.noemoticon.csv_ do wspólnego folderu z plikiem _edycja_csv.class_ i z linii poleceń uruchamiamy konwersję:

```java -cp źródło\pliku edycja_csv```

#### W wyniku otrzymujemy plik tweets.csv, który zawiera "oszczyszczone" dane, okrojoną liczbę kolumn i dodaną geolokalizację. Kolumny to kolejno:

|Id|CreationData|Username|Tweet|Latitude|Longitude|
|---|-----------|--------|-----|--------|---------|

#### Łączenie się z postgresem

```psql -U postgres -h localhost```

#### Uruchomienie pomiarów czasowych

```\timing```

Korzystamy z utworzonej w poprzednim zadaniu bazy i schematu.

```\c baza```

#### Tworzymy nową tabelę dla nowego zbioru.

```CREATE TABLE schema.tweets(Id bigint, CreationDate date, Username varchar, Tweet varchar, Latitude numeric, Longitude numeric)```

#### Czas tworzenia tabeli

```0,536147s```

#### Importowanie danych do tabeli z przygotowanego pliku

```\copy schema.tweets FROM 'C:/Users/MINIO/Desktop/TEST/tweets.csv' DELIMITER ',' CSV HEADER```

#### Czas importowanie danych

```20,253833s```

#### Zliczenie ilości danych

```SELECT COUNT(*) FROM schema.tweets```

#### Czas zliczania danych

```0,557999s```

#### Liczba danych

```1 600 000```

#### Znalezienie 5 najbardziej aktwynych użytkowników

```SELECT username, COUNT(*) AS tweets FROM schema.tweets GROUP BY username ORDER BY tweets DESC LIMIT 5```

#### Czas szukania i wyliczania

```29,728347s```

#### Zwrócony wynik:

|username|tweets|
|------------|:-------------:|
|lost_dog|549|
|webwoke|345|
|tweetpet|310|
|SallytheShizzle|281|
|VioletsCRUK|279|

#### Sprawdzanie, w którym miesiącu roku( 2009 ) było najwięcej tweetów

```SELECT extract(month FROM creationdate) as month, COUNT(*) as tweets FROM schema.tweets GROUP BY 1 ORDER BY tweets DESC```

#### Czas sprawdzania

```1,529245s```

#### Zwrócony wynik

|month|tweets|
|------------|:-------------:|
|6|923608|
|5|576367|
|4|100025|

#### Sprawdzanie, w którym miesiącu roku( 2009 ) było najwięcej tweetów z nazwą miesiąca

```SELECT to_char(to_timestamp(to_char(extract(month FROM creationdate),'999'),'MM'),'Month') as month, COUNT(*) as tweets FROM schema.tweets GROUP BY 1 ORDER BY tweets DESC```

#### Czas sprawdzania

```10,160789s```

#### Zwrócony wynik

|month|tweets|
|------------|:-------------:|
|June|923608|
|May|576367|
|April|100025|


## MongoDB

Formaty DateTime i liczb powinny być poprawnie zaimportowane.(TODO)

### Zadanie 1

Dane: _[Pobierz](https://archive.org/download/stackexchange/gamedev.stackexchange.com.7z)_ (Game Development Posts)

Plik: _Posts.xml_

##### Wstawiamy plik _Posts.xml_ do wspólnego folderu z plikiem _xml_to_csv.class_ i z linii poleceń uruchamiamy konwersję:

```java -cp źródło\pliku xml_to_csv```

#### W wyniku otrzymujemy plik posts.csv, który zawiera "oszczyszczone" dane, a także okrojoną liczbę kolumn. Kolumny to kolejno:

|Id|ViewCount|CreationData|Body|Title|Tags|AnswerCount|CommentCount|
|---|--------|------------|----|-----|----|-----------|------------|

#### Tworzenie bazy danych w MongoDB:

```use baza```

#### Import danych z przygotowanego pliku CSV

```mongoimport -d baza -c posts --type csv --file C:\folder\posts.csv --headerline```

##### Liczba importowanych danych

```94185```

#### Komenda pomiaru czasu

```powershell "Measure-Command{mongoimport -d baza -c posts --type csv --file C:\folder\posts.csv --headerline}"```

#### Czas importu

```Total seconds : 13,1237809```
