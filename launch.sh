#!/bin/bash

docker system prune -y

cd biblioback
mvn clean install
cd docker
docker-compose  down
docker-compose up -d
sleep 30
cd ../../biblioweb/
mvn clean install
cd docker
docker-compose  down
docker-compose up -d
cd ../../bibliobatch
mvn clean install
cd docker
docker-compose  down
docker-compose up -d
cd ../../expiring-soon-batch/
mvn clean install
cd docker
docker-compose  down
docker-compose up -d
