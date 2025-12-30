# Eureka Server - Обнаружение сервисов

Централизованный сервис обнаружения для микросервисной архитектуры на базе Netflix Eureka.

## Назначение
Eureka Server предоставляет централизованный реестр, где все микросервисы регистрируются и могут обнаруживать друг друга.

## Особенности
- Автоматическая регистрация и deregistration сервисов
- Health-check через heartbeat механизм
- Web-интерфейс для мониторинга
- Интеграция с Spring Cloud Gateway для динамической маршрутизации

## Технологии
- Spring Boot 3.5.8
- Spring Cloud Netflix Eureka Server 2025.0.1
- Java 17

## Запуск
    mvn spring-boot:run

## Проверка работоспособности
1. Web интерфейс: http://localhost:8761
2. Health endpoint: http://localhost:8761/actuator/health
3. REST API: http://localhost:8761/eureka/apps

## Регистрируемые сервисы
- `user-service` (порт 8081) - сервис управления пользователями
- `notification-service` (порт 8082) - сервис уведомлений
- `config-server` (порт 8888) - сервер конфигураций
- `api-gateway` (порт 8080) - API шлюз

## Конфигурация
    server:
      port: 8761
    eureka:
      client:
        register-with-eureka: false  # Сервер не регистрирует себя
        fetch-registry: false        # Не получает реестр других серверов

## Интеграция с другими сервисами
Все микросервисы подключаются как Eureka Client с настройкой:

    eureka:
      client:
        service-url:
          defaultZone: http://localhost:8761/eureka