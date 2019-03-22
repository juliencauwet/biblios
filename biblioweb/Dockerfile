#FROM openjdk:8-jdk-alpine
#MAINTAINER juliencauwet@yahoo.fr
#WORKDIR /app
## Copy the current directory contents into the container at /app
#COPY . /app
#COPY ${JAR_FILE} /biblioweb.war
#
## Install any needed packages specified in requirements.txt
##RUN pip install --trusted-host pypi.python.org -r requirements.txt
#
## Make port 310 available to the world outside this container
#EXPOSE 310
#
## Define environment variable
##ENV NAME World
#
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/biblioweb.war"]


FROM tomcat:8.5-alpine
MAINTAINER juliencauwet@yahoo.fr
VOLUME /tmp
#COPY . /app
COPY target/biblioweb.war /usr/local/tomcat/webapps/app.war
ADD target/biblioweb.war /usr/local/tomcat/webapps/app.war
#RUN sh -c 'touch /usr/local/tomcat/webapps/app.war'
#CMD mvn clean install
CMD echo "Hello"
CMD ["catalina.sh", "start"]
