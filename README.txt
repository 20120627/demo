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