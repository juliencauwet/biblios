#FROM openjdk:8-jdk-alpine
#MAINTAINER juliencauwet@yahoo.fr
#WORKDIR /app
#VOLUME ["/app"]
#COPY /assembly /assembly
#COPY /target/biblioback-0.0.1-SNAPSHOT.jar /app
#ADD /target/biblioback-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]

FROM openjdk:8-jre
MAINTAINER Julien Cauwet <juliencauwet@yahoo.fr>

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/myservice/myservice.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
ADD target/lib           /usr/share/myservice/lib
# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/myservice/myservice.jar