# CLAUDE.md

This file provides guidance to Claude Code when working in this repository.

The One Project is a Spring Boot / Spring Cloud microservices learning project (Java 11, Spring Boot 2.6.x, Spring Cloud 2021.0.2) exploring service discovery, an API gateway, JWT-based auth, and a server-rendered admin web front end. Modules are independent Maven projects (no root reactor pom) that share one MariaDB database (`the_one_project`).

## Modules

| Module | Purpose | Port |
|---|---|---|
| `OneEureka` | Eureka service discovery server | 8010 |
| `OneApiGateway` | Spring Cloud Gateway, routes to services registered in Eureka | 8082 |
| `OneApiUsers` | Users microservice (JPA + MariaDB), registers with Eureka | random (`server.port=0`) |
| `OneApiAccountManagement` | Account microservice skeleton (controller only, no persistence yet) | random (`server.port=0`) |
| `OneAP` | Standalone app: JWT auth, role-based (`ADMIN`/`USER`) menu & timetable endpoints (currently stubs), Thymeleaf | 8080, context path `/api` |
| `OneWEB` | Server-rendered web front end (Thymeleaf + static Vue 2 / Bootstrap) — login/index/logout pages | 8443 |

## Commands

Each module is an independent Maven project with its own wrapper. From inside a module directory:

- Build: `./mvnw clean package`
- Run: `./mvnw spring-boot:run`
- Test: `./mvnw test`

There is no root-level build script tying the modules together. `OneEureka` should be started first, since `OneApiGateway`, `OneApiUsers`, and `OneApiAccountManagement` register with it on boot.

## Architecture notes

- No shared parent POM — each module pins its own `spring-boot-starter-parent` version (2.6.1–2.6.7) independently.
- `OneApiUsers` and `OneAP` connect directly to a MariaDB database `the_one_project` (schema in `doc/table.sql`, seed data in `doc/data.sql`).
- `MenuController` and `TimeTableController` in `OneAP` are unimplemented stubs (they return empty maps).
- `WebSecurityConfig` in `OneApiUsers` currently `permitAll()`s all requests — the IP/gateway-based auth filter is written but commented out.
- `OneAP` and `OneWEB` use different Maven groupIds (`com.oscar`) than the microservice modules (`com.oscar.theoneproject`), suggesting they were built as separate, earlier standalone apps rather than part of the same reactor.
