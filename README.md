# HTTP API для управления пользователями и их питомцами
Этот проект представляет собой учебное Spring Boot приложение, реализующее HTTP-эндпоинты для управления пользователями и их питомцами.

***Функциональные возможности***

Приложение поддерживает следующие операции:

**Пользователи (UserController):**
- Создание пользователя (POST /users): создание нового пользователя.
- Обновление пользователя (PUT /users/{id}): обновление данных существующего пользователя.
- Удаление пользователя (DELETE /users/{id}): удаление пользователя по ID.
- Получение пользователя (GET /users/{id}): получение данных пользователя по ID.

**Питомцы (PetController):**
- Создание питомца (POST /pets): создание нового питомца, привязанного к пользователю.
- Удаление питомца (DELETE /pets/{id}): удаление питомца по ID.

**Используемые технологии:**
- Java 
- Spring Boot 
- Spring Web
