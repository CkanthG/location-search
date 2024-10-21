# Use the official OpenJDK 17 image from Docker Hub
FROM openjdk:17

WORKDIR /app
# Copy the compiled Java application JAR file into the container
COPY ./build/libs/location-search-1.0.0.jar /app
# Expose the port the Spring Boot application will run on
EXPOSE 8080
# Command to run the application
CMD ["java", "-jar", "location-search-1.0.0.jar"]