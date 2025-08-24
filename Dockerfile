# Usar una imagen base de Gradle para construir la aplicación
FROM gradle:7.6-jdk17 AS build

# Configurar el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos de proyecto (build.gradle, settings.gradle, y código fuente)
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Ejecutar el comando Gradle para compilar y empaquetar la aplicación
RUN gradle build -x test

# Usar una imagen base de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-alpine

# Configurar el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR construido desde la fase de compilación anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Definir el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]