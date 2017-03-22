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

### Przedstawienie danych

Przykładowy rekord( TODO ):

``` json
{
	"id": "1467812416",
	"CreationData": "Mon Apr 06 22:20:16 PDT 2009",
	"Username": "erinx3leannexo",
	"Tweet": "spring break in plain city... it's snowing ",
	"Latitude": "36.1749700",
	"Longitude": "-115.1372200"
}
```

#### Wyjaśnienie pól

* id - numer id tweeta
* CreationData - data udostępnienia tweeta
* Username - nazwa użytkownika tweeta
* Tweet - tekst tweeeta
* Latitude - szerokość geograficzna
* Longitude - długość geograficzna

### Przykładowe zapytania

Informacje, których będę szukał to np. najaktywniejsi użytkownicy, miejsca, z których zostało wysłanych najwięcej tweetów czy statystyka miesięczna( zbiór zawiera tweety z 3 miesięcy 2009 roku ).

Przykład zapytania w PostgreSQL( 5 najaktywniejszych użytkowników ):

```SELECT username, COUNT(*) AS tweets FROM schema.tweets GROUP BY username ORDER BY tweets DESC LIMIT 5```

### Czyszczenie danych

[Plik](https://docs.google.com/uc?id=0B04GJPshIjmPRnZManQwWEdTZjg&export=download) zawierający dane nie zawierał nagłówków, a opis poszczególnych kolumn znajdował się na [stronie](http://help.sentiment140.com/for-students/), z której został pobrany zbiór. Postanowiłem usunąć kolumnę pierwszą, zawierającą liczby(0,2 lub 4) oznaczającą ocenę tweeta( 0 - negatywny, 2 - neutralny, 4 - pozytywny), a także kolumnę 4-tą, zawierającą zapytania do postów. Ponieważ we wszystkisch rekordach była ta sama wartość "NO_QUERY", postanowiłem pozbyć się tej kolumny. Dodatkowo postanowiłem o dodaniu lokalizacji tych tweetów wprowadzając listę współrzędnych 31 miejscowości USA i losując( programistycznie ) do każdego tweeta jedną z nich.


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

#### Import danych z przygotowanego pliku CSV z pomiarem czasu

```powershell "Measure-Command{mongoimport -d baza -c posts --type csv --file C:\folder\posts.csv --headerline}"```

#### Czas importu

```Total seconds : 13,1237809```

##### Liczba zaimportowanych danych

```94185```


### Zadanie 2 ( EDA )

Dane: _[Pobierz](https://docs.google.com/uc?id=0B04GJPshIjmPRnZManQwWEdTZjg&export=download)_ (Twitter Data For Sentiment Analysis)

Plik: _training.1600000.processed.noemoticon.csv_

##### Wstawiamy plik _training.1600000.processed.noemoticon.csv_ do wspólnego folderu z plikiem _edycja_csv.class_ i z linii poleceń uruchamiamy konwersję:

```java -cp źródło\pliku edycja_csv```

#### W wyniku otrzymujemy plik tweets.csv, który zawiera "oszczyszczone" dane, okrojoną liczbę kolumn i dodaną geolokalizację. Kolumny to kolejno:

|Id|CreationData|Username|Tweet|Latitude|Longitude|
|---|-----------|--------|-----|--------|---------|

#### Tworzenie bazy danych w MongoDB:

```use baza```

#### Import danych z przygotowanego pliku CSV z pomiarem czasu

```powershell "Measure-Command{mongoimport -d baza -c tweets --type csv --file C:\folder\tweets.csv --headerline}"```

#### Czas importu

```Total seconds : 73,2462208```

##### Liczba zaimportowanych danych

```1 600 000```


### Mapka

##### Wstawiamy wcześniej przygotowany plik( w zadaniu 2 EDA ) _tweets.csv_ do wspólnego folderu z plikiem csv_to_geojson.class_ i z linii poleceń uruchamiamy konwersję:

```java -cp źródło\pliku csv_to_geojson```

W wyniku otrzymujemy plik tweets.geojson, który zawiera przygotowane dane w formacie GeoJSON.

Mapa zawiera miejsca, z których wysyłane były tweety( nazwa kraju( USA ) i miasta ) oraz zliczoną liczbę tweetów wysłanych z tego miejsca.

[Zobacz mapkę](https://github.com/kropeq/nosql/blob/master/tweets.geojson)

![alt tag](https://github.com/kropeq/nosql/blob/master/screens/mapka_geojson.png)

Zapytanie mapkowe w celu określania lokalizacji jakichś obiektów.(TODO)

## Elasticsearch

Mapping - przygotowac i zapisać(TODO)

Import danych:

sh gunzip -c dane.json.gz | ... #całość | #próbka / sample

Policzyć czas ile to zajęło.
