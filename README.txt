SPRING BOOT APPLICATION FOR TASK MANAGER (TODO)

PG ADMIN: run sql query to create table task
INIT: VSCode >spring initializr

-Controller: Handles HTTP requests and maps them to service methods.

-Domain: Contains the JPA entity classes representing the database tables.

-DTO (Data Transfer Object): Used to transfer data between different layers of the application (request & respond)

-Repository: Interfaces for database operations, extending Spring Data JPA.

-Service: Contains business logic and interacts with the repository layer.

-application.properties: Configuration file for the application, including database connection settings.

-pom.xml: Maven configuration file, managing project dependencies and build configuration.

run in postman: 

- get task list: GET http://localhost:8081/api/task/all

- update task detail: PUT http://localhost:8081/api/task/1/update
        body: {
                "title": "New Task 1",
                "description": "This is a new task",
                "dueDate": "2024-12-31",
                "priority": 1,
                "completed": true
            }

- add task: POST http://localhost:8081/api/task/add
        body: {
                "title": "New Task",
                "description": "This is a new task",
                "dueDate": "2023-12-31",
                "priority": 1
                }

- delete task: http://localhost:8081/api/task/5/delete

- get all dependencies: GET http://localhost:8081/api/task/1/dependencies

- add task dependencies: PUT http://localhost:8081/api/task/1/update/dependecy
        body: {"dependentTaskId": 2}

- remove task dependency: PUT http://localhost:8081/api/task/1/delete/dependency