# Build con el wrapper del proyecto (misma versión de Gradle que en local)
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
COPY src ./src
RUN sed -i 's/\r$//' gradlew && chmod +x gradlew && ./gradlew --no-daemon bootJar -x test

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
# SQLite: el compose monta un volumen en /app/data
RUN mkdir -p /app/data
EXPOSE 8004
ENTRYPOINT ["java", "-jar", "app.jar"]
