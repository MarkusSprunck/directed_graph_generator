# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="sprunck.markus@gmail.com"

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=/target/DirectedGraphGenerator-1.0.4.jar

# Add the application's jar to the container
ADD ${JAR_FILE} /usr/local/bin/dgg/DirectedGraphGenerator-1.0.4.jar

# The application's input files
ARG EXCEL_FILES=/input/data*.xlsx

# Add the application's input files to the container
ADD ${EXCEL_FILES} ./input/

# add GraphViz (needed for PlantUML)
RUN apk add --update --no-cache graphviz ttf-freefont

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/usr/local/bin/dgg/DirectedGraphGenerator-1.0.4.jar"]