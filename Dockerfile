# ============================================================
# Dockerfile para desplegar la app Java Web (Tomcat 9) en Render
# ============================================================

# Etapa 1: compilar el proyecto con Maven
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: correr el WAR en Tomcat 9
FROM tomcat:9.0-jdk11-temurin
# Limpiar apps de ejemplo de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*
# Desplegar como ROOT para que la app quede en la raíz del dominio
COPY --from=build /app/target/trivia-webapp.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
