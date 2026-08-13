# Dev Notes

## Local setup

1. Install Java 11 and MariaDB.
2. Create the database and load the schema (optionally the sample seed data too):
   ```bash
   mysql -u root -p < doc/table.sql
   mysql -u root -p < doc/data.sql   # optional sample data
   ```
3. Each module keeps its own datasource config in `src/main/resources/application.properties` or `application.yml` — update host/port/username/password there to match your local MariaDB before running `OneApiUsers` or `OneAP`. See Security note below before committing any change to these files.

## Running / testing

There is no root build; every module is an independent Maven project with its own `./mvnw` wrapper:

```bash
cd <module> && ./mvnw spring-boot:run   # run
cd <module> && ./mvnw test              # run tests (JUnit 5 / Spring Boot Test)
```

Startup order matters for the microservice modules: bring up `OneEureka` (discovery, port 8010) before `OneApiGateway`, `OneApiUsers`, or `OneApiAccountManagement`, since they register with it on boot. `OneAP` and `OneWEB` run standalone and don't depend on Eureka.

## Structure

- `doc/` — raw MariaDB schema (`table.sql`) and seed data (`data.sql`), dumped via HeidiSQL; not wired into any migration tool (no Flyway/Liquibase).
- `OneEureka/`, `OneApiGateway/`, `OneApiUsers/`, `OneApiAccountManagement/` — Spring Cloud microservices (`com.oscar.theoneproject` groupId), Spring Boot 2.6.7 / Spring Cloud 2021.0.2.
- `OneAP/` — separate standalone Spring Boot app (`com.oscar` groupId, Boot 2.6.1) with its own JWT-based auth (`auth/util/JwtUtil.java`) and role-gated `MenuController` / `TimeTableController`. Different groupId and no Eureka client dependency, so it appears to be a separate exercise rather than part of the microservices split.
- `OneWEB/` — separate standalone Spring Boot app (Boot 2.6.4) serving Thymeleaf pages (`login.html`, `index.html`, `logout.html`) with static Vue 2 / Bootstrap assets checked in directly under `resources/static` (no npm/webpack build step).

## Known gaps

- `OneAP`'s `MenuController` / `TimeTableController` return empty maps — unimplemented.
- `OneApiAccountManagement` has a controller but no service/repository/entity layer.
- `OneApiUsers`'s `WebSecurityConfig` currently `permitAll()`s every request; the intended IP-restricted authentication filter is written but commented out.

## Security note

Some config files in this repo currently hardcode local-dev-style values (a MariaDB password in `OneApiUsers`'s and `OneAP`'s datasource config, and a placeholder JWT signing key in `OneAP`'s `JwtUtil`). Since this repo is public, treat any value already in git history as compromised: rotate it before reusing it anywhere real, and move future secrets out of source (environment variables or a local, gitignored properties file) rather than back into these files.
