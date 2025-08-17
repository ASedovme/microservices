# Этап 1: Сборка с Maven
FROM maven:3-openjdk-17-slim AS builder
WORKDIR /app

# Копируем pom.xml и загружаем зависимости
COPY pom.xml .
RUN mvn dependency:go-offline

# Копируем исходники и собираем JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Этап 2: Запуск (легковесный образ)
FROM openjdk:17-slim
WORKDIR /app

# Копируем собранный JAR из этапа builder
COPY --from=builder /app/target/*.jar app.jar

# Активируем профиль docker
ENV SPRING_PROFILES_ACTIVE=docker

# Порт (уникальный для каждого сервиса)
EXPOSE 8082

# Запуск
ENTRYPOINT ["java", "-jar", "app.jar"]
