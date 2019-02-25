FROM openjdk:8-jdk-alpine
MAINTAINER juliencauwet@yahoo.fr
WORKDIR /app
# Copy the current directory contents into the container at /app
COPY . /app
COPY ${JAR_FILE} /biblioweb.war

# Install any needed packages specified in requirements.txt
#RUN pip install --trusted-host pypi.python.org -r requirements.txt

# Make port 80 available to the world outside this container
EXPOSE 310

# Define environment variable
#ENV NAME World

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/biblioweb.war"]