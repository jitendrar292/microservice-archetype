# Start with a base image containing the Java runtime
FROM openjdk:8-jre-alpine

# Add Maintainer Info
LABEL maintainer="christian.choi@cbre.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Copy the application and start up script to the container
COPY build/libs/site-api*.jar /tmp/site-api.jar
COPY ./startup-script.sh /tmp/startup-script.sh

# Start the application
CMD ["sh", "/tmp/startup-script.sh"]