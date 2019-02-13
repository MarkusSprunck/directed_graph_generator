# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="sprunck.markus@gmail.com"

# add shell command Curl
RUN apk add --update curl && rm -rf /var/cache/apk/*

# add GraphViz (needed for PlantUML)
RUN apk add --update --no-cache graphviz ttf-freefont

# create default target folder for application data
RUN mkdir -p /usr/local/bin/data
RUN mkdir -p /usr/local/bin/conf

# Add the application's input files to the container
ADD /data/*.xlsx /usr/local/bin/data/

# Make port available to the world outside this container
EXPOSE 8080

# Add the application's jar to the container
ADD /target/ROOT.jar                     /usr/local/bin/ROOT.jar
ADD /src/main/resources/application.yml  /usr/local/bin/conf/application.yml

WORKDIR /usr/local/bin/conf

# Run the jar file
ENTRYPOINT ["java","-jar", "/usr/local/bin/ROOT.jar"]