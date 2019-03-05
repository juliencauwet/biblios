FROM openjdk:8-jdk-alpine
#WORKDIR /app
VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
#ADD /target/biblioback-0.0.1-SNAPSHOT.jar /app
COPY /target/biblioback-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/target/biblioback-0.0.1-SNAPSHOT.jar"]