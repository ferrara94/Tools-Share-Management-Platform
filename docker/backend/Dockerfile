#### Build stage
FROM maven:3.8.7-eclipse-temurin-17 AS build

# Set the working directory to /build
WORKDIR /build

# Copy pom.xml to the build folder
COPY pom.xml .

# Install dependencies (offline to reduce network traffic)
RUN mvn dependency:go-offline

# Copy the application source files into the container
COPY src ./src

# Build the application using Maven and skip tests for faster builds
RUN mvn clean package -DskipTests

#### Runtime stage
FROM amazoncorretto:17

# Arguments to pass the profile (default to 'dev') and version (default to '1.0.0')
ARG PROFILE=staging
ARG APP_VERSION=1.0.3

# Set the working directory for the runtime container
WORKDIR /app

# Copy the JAR file built from the 'build' stage into the container's /app folder
# Make sure the filename matches the one generated in the target directory (tool-sharing-1.0.0-SNAPSHOT.jar)
COPY --from=build /build/target/tool-sharing-${APP_VERSION}-SNAPSHOT.jar /app/

# Check if the JAR file is copied correctly by listing the /app directory
RUN ls -l /app

# Expose port 8080 for the Spring Boot app
EXPOSE 8080

# Define environment variables for DB URL and Spring profiles
# DB_URL should be pointing to your PostgreSQL container name or host
ENV DB_URL=jdbc:postgresql://postgres-sql-tsmp:5432/tool_share_management_platform
ENV ACTIVE_PROFILE=${PROFILE}
ENV JAR_VERSION=${APP_VERSION}


# Run the application with the appropriate environment variables (using exec form to avoid the warning)
CMD ["sh", "-c", "java -jar -Dspring.profiles.active=$ACTIVE_PROFILE -Dspring.datasource.url=$DB_URL /app/tool-sharing-$JAR_VERSION-SNAPSHOT.jar"]
