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
