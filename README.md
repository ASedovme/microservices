# 🚀 Микросервисная архитектура на Spring Boot + Docker

Проект реализует микросервисную архитектуру с использованием:
- Spring Boot 3
- Spring Cloud (Eureka, Config, Feign)
- Docker + Docker Compose
- Maven

## 🧩 Сервисы

| Сервис | Порт | Описание |
|-------|------|--------|
| `eureka-server` | `8761` | Service Discovery (реестр сервисов) |
| `config-server` | `8888` | Централизованное управление конфигурацией |
| `gateway` | `8080` | API Gateway (единая точка входа) |
| `user-service` | `8081` | Управление пользователями |
| `company-service` | `8082` | Управление компаниями и сотрудниками |

## 📦 Технологии

- Java 17
- Spring Boot 3.3+
- Spring Cloud 2023.0.1
- Maven
- Docker
- REST API
- Feign Client (межсервисное взаимодействие)

## 🐳 Запуск через Docker

1. Убедись, что установлено:
    - [Docker](https://www.docker.com/products/docker-desktop)
    - [Maven](https://maven.apache.org/install.html) (для локальной сборки)

2. Собери и запусти все сервисы:
   ```bash
   docker-compose up --build
