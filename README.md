## Описание
<div>
<h3>
  REST API сервис для управления пользователями и их задачами, реализованный на Spring Boot.
  Сервис предоставляет полный CRUD-функционал для сущностей User и Task, 
  связанных отношением 1:M (один пользователь — много задач).
</h3>
</div>
<br>

## Стек технологий
* **Java 17+**
* **Spring Boot**
* **Spring MVC**
* **Spring Data JPA**
* **Hibernate**
* **Hibernate Validation**
* **PostgreSQL**
* **Lombok**
* **Gradle**

<br>

## Архитектура БД
### Сущности
```
User

 - user_id
 - name
 - surname
 - registered_at


Task

 - task_id,
 - description,
 - user_id,
 - created_at,
 - status

```

<br>

### Структура проекта ###
```
    │   │           └───tracker
    │   │               │   TrackerApplication.java
    │   │               │   
    │   │               ├───controller
    │   │               │       HandlerController.java
    │   │               │       TaskController.java
    │   │               │       UserController.java
    │   │               │       
    │   │               ├───dto
    │   │               │       TaskDto.java
    │   │               │       UserDto.java
    │   │               │       
    │   │               ├───entity
    │   │               │       Task.java
    │   │               │       User.java
    │   │               │       
    │   │               ├───repository
    │   │               │       TaskRepository.java
    │   │               │       UserRepository.java
    │   │               │       
    │   │               ├───service
    │   │               │       TaskService.java
    │   │               │       UserService.java
    │   │               │       
    │   │               └───utils
    │   │                   │   TaskNotFound.java
    │   │                   │   UserNotFound.java
    │   │                   │   
    │   │                   ├───enums
    │   │                   │       TaskStatus.java
    │   │                   │       
    │   │                   └───exceptions
    │   │                           TaskNotFoundException.java
    │   │                           UserNotFoundException.java
    │   │                           
    │   └───resources
    │       │   application.properties
    │       │   
    │       ├───static
    │       │       sql.sql
    │       │       
    │       └───templates
    └───test
        └───java
            └───by
                └───alexkrug
                    └───tracker
                            TrackerApplicationTests.java
```
<br>

## Эндпоинты API

### Пользователи (Users)

| Метод | URL | Описание |
|-------|-----|----------|
| `GET` | `/api/v1/users` | Получить список всех пользователей |
| `GET` | `/api/v1/user/{id}` | Получить пользователя по ID |
| `POST` | `/api/v1/users` | Создать нового пользователя |
| `PATCH` | `/api/v1/users/{id}` | Частично обновить пользователя |
| `DELETE` | `/api/v1/users/{id}` | Удалить пользователя |
| `GET` | `/api/v1/users/{id}/tasks` | Получить все задачи пользователя |

### Задачи (Tasks)

| Метод | URL | Описание |
|-------|-----|----------|
| `GET` | `/api/v1/tasks` | Получить список всех задач |
| `GET` | `/api/v1/task/{id}` | Получить задачу по ID |
| `GET` | `/api/v1/tasks/{id}/user` | Получить пользователя задачи |
| `POST` | `/api/v1/tasks` | Создать новую задачу |
| `PATCH` | `/api/v1/tasks/{id}` | Частично обновить задачу |
| `DELETE` | `/api/v1/tasks/{id}` | Удалить задачу |

<br>

##  Коды ответов

| Код | Статус | Описание | Когда используется |
|-----|--------|----------|--------------------|
| `200` | **OK** | Успешный запрос | Успешное получение (GET) или обновление (PATCH) данных |
| `201` | **Created** | Ресурс создан | Успешное создание пользователя или задачи (POST) |
| `204` | **No Content** | Нет содержимого | Успешное удаление сущности (DELETE) |
| `400` | **Bad Request** | Некорректный запрос | Ошибка валидации входных данных или неверный формат JSON |
| `404` | **Not Found** | Не найдено | Пользователь или задача с указанным ID не существует в БД |
| `500` | **Internal Server Error** | Внутренняя ошибка | Непредвиденная ошибка на стороне сервера |
