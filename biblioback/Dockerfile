FROM openjdk:8-jdk-alpine
MAINTAINER juliencauwet@yahoo.fr
WORKDIR /app
VOLUME ["/app"]
COPY /assembly /assembly
COPY /target/biblioback.jar /app
ADD /target/biblioback.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
