# The One Project

A personal Spring Boot / Spring Cloud practice project exploring a microservices architecture: service discovery (Eureka), an API gateway, JWT-based authentication, and a server-rendered admin web front end, backed by MariaDB.

## Modules

- **OneEureka** — Eureka discovery server (port 8010)
- **OneApiGateway** — Spring Cloud Gateway, routes requests to services registered in Eureka (port 8082)
- **OneApiUsers** — Users microservice (JPA + MariaDB), registers with Eureka
- **OneApiAccountManagement** — Account microservice skeleton (controller only, no persistence yet)
- **OneAP** — Standalone app with JWT auth and role-based (`ADMIN`/`USER`) menu/timetable endpoints (port 8080, context path `/api`)
- **OneWEB** — Server-rendered login/index/logout pages (Thymeleaf + static Vue 2 / Bootstrap assets, port 8443)

## Requirements

- Java 11
- Maven (or use the bundled `./mvnw` wrapper in each module — no separate Maven install needed)
- MariaDB — schema in `doc/table.sql`, optional sample seed data in `doc/data.sql`; `OneApiUsers` and `OneAP` expect a `the_one_project` database

## Running

Each module is a standalone Maven project (there is no shared reactor pom). Start Eureka first, then the other services you need, each in its own terminal:

```bash
cd OneEureka && ./mvnw spring-boot:run       # discovery server, port 8010
cd OneApiGateway && ./mvnw spring-boot:run   # gateway, port 8082
cd OneApiUsers && ./mvnw spring-boot:run     # users service, registers with Eureka
cd OneAP && ./mvnw spring-boot:run           # standalone app with JWT auth, port 8080
cd OneWEB && ./mvnw spring-boot:run          # web front end, port 8443
```

Database connection settings (URL/username/password) are configured per-module in `src/main/resources/application.properties` or `application.yml`. Update these to point at your own local MariaDB instance before running `OneApiUsers` or `OneAP` — do not reuse the checked-in values in any shared or production environment.

## Status

This is a learning project. Some parts are incomplete: `OneAP`'s `MenuController` and `TimeTableController` are stubs that return empty responses, and `OneApiAccountManagement` has a controller but no service/repository/persistence layer yet.
