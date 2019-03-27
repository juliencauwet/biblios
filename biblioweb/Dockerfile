FROM tomcat:8.5-alpine
MAINTAINER juliencauwet@yahoo.fr
VOLUME /tmp
#COPY . /app

ADD target/biblioweb.war /usr/local/tomcat/webapps/
#RUN sh -c 'touch /usr/local/tomcat/webapps/app.war'
#CMD mvn clean install
CMD echo "Hello"
CMD ["catalina.sh", "run"]
