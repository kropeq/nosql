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

Schema -- przygotować i użyć w trakcie importu danych.(TODO) 

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

```0,000694s

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
