# Etapa 1: compilar con Maven
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: construir la imagen de la app
FROM eclipse-temurin:21-jdk-jammy

ARG SERVER_PORT=8080
# Valor por defecto
ENV SERVER_PORT=${SERVER_PORT}

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE ${SERVER_PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]