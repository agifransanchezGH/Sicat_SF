# ---------- ETAPA 1: Compilación ----------
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B

COPY src ./src
ENV MAVEN_OPTS="-Xmx1024m -XX:+UseSerialGC"
RUN ./mvnw clean package -DskipTests -B

# ---------- ETAPA 2: Ejecución ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]