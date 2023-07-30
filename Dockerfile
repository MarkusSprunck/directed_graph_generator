# Start with a base image containing Java runtime
FROM eclipse-temurin:17-jre-alpine

# Add Maintainer Info
LABEL maintainer="sprunck.markus@gmail.com"

# add shell command Curl
RUN apk add --update curl && rm -rf /var/cache/apk/*

# add GraphViz (needed for PlantUML)
RUN apk add --update --no-cache graphviz ttf-freefont

# create default target folder for application data
RUN mkdir -p /usr/local/bin
RUN mkdir -p /usr/local/bin/data

# Add the application's input files to the container
ADD /data/*.xlsx /usr/local/bin/data

# Make port available to the world outside this container
EXPOSE 8444

# Add the application's jar to the container
ADD /target/beteigeuze.jar               /usr/local/bin/beteigeuze.jar
ADD /src/main/resources/application.yml  /usr/local/bin/application.yml

WORKDIR /usr/local/bin

# Run the jar file
ENTRYPOINT ["java","-jar", "/usr/local/bin/beteigeuze.jar"]