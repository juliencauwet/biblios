FROM openjdk:8-jdk-alpine
MAINTAINER juliencauwet@yahoo.fr
WORKDIR /app
VOLUME ["/app"]
COPY /target/late-pickup-batch.jar /app
ADD /target/late-pickup-batch.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
