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
