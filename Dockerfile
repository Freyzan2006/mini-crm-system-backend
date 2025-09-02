# -------------------------
# Stage 1: Build
# -------------------------
FROM maven:3.9.3-eclipse-temurin-20 AS build

WORKDIR /app

# Копируем POM и скачиваем зависимости заранее (кэширование слоёв)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем исходники
COPY src ./src

# Собираем jar
RUN mvn clean package -DskipTests

# -------------------------
# Stage 2: Run
# -------------------------
FROM eclipse-temurin:20-jre-jammy

WORKDIR /app

# Копируем собранный JAR из билд-стейджа
COPY --from=build /app/target/mini-crm-1.0-SNAPSHOT.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]
