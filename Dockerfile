# Etapa de build: Gradle + JDK 17 (Temurin)
FROM gradle:7.6.4-jdk17-alpine AS build

# Configurar el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos de proyecto (build.gradle, settings.gradle, y código fuente)
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Ejecutar el comando Gradle para compilar y empaquetar la aplicación
RUN gradle clean build -x test

# Etapa de runtime: JDK 17 (Temurin)
FROM eclipse-temurin:17-jdk-alpine

# Configurar el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR construido desde la fase de compilación anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8002

# Definir el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
