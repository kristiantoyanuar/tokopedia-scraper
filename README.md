# Tokopedia Scraper

## Requirements:
JDK 8, Maven, Chrome

## To build:
```sh
mvn clean compile assembly:single
```
## To run:

```sh
java -jar target/*.jar handphone top100phones.csv
```
or
```sh
java -jar target/*.jar handphone top50phones.csv 50
```
