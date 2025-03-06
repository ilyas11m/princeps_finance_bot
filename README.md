# 📊 Telegram Финансовый Бот

## 📌 Описание
Этот бот помогает пользователям контролировать свои финансы: учитывать доходы, расходы, устанавливать бюджеты и получать отчеты.

## 🚀 Функционал
✅ Добавление доходов и расходов
✅ Просмотр баланса
✅ Категории трат
✅ Установка бюджета
✅ Генерация отчетов
✅ Уведомления о превышении бюджета
✅ Админ-панель для управления ботом

## 🛠️ Технологии
- **Java 17**
- **Spring Boot**
- **TelegramBots API**
- **PostgreSQL**
- **Spring Data JPA**

## 🔧 Установка и запуск

### 1️⃣ Клонирование репозитория
```sh
git clone https://github.com/ilyas11m/princeps_finance_bot.git
cd princeps_finance_bot
```
### 2️⃣ Сборка и запуск бота
```sh
mvn clean package
java -jar target/princeps_finance_bot.jar
```

## 🔗 API
Если бот имеет REST API для админки, примеры запросов можно добавить сюда.

## 📝 Структура проекта
```
finance-bot/
│── src/main/java/com/project/bot/
│   ├── config/       # Конфигурация бота
│   ├── controller/   # REST API
│   ├── model/        # Сущности БД
│   ├── repository/   # Репозитории JPA
│   ├── service/      # Логика бота
│   ├── utils/        # Утилиты
│   ├── TelegramBot.java  # Основной бот-класс
│── src/main/resources/
│   ├── application.properties
│── README.md
```
---
⚡ *Разработано с любовью!* ❤️

