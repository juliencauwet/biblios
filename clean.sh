#!/bin/bash

cd biblioback
mvn clean
cd ../biblioweb
mvn clean
cd ../bibliobatch
mvn clean
cd ../expiring-soon-batch
mvn clean
