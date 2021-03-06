#!/bin/sh
mvn clean package && docker build -t org.example/atx-web .
docker rm -f atx-web || true && docker run -d -p 8080:8080 -p 4848:4848 --name atx-web org.example/atx-web 
