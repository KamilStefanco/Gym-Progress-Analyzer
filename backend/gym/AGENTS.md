# AGENTS: How to work with this codebase

This document gives focused, actionable guidance for AI coding agents to be productive in this Spring Boot backend.

1) Big-picture architecture
- Spring Boot 4 application (entry: `com.kstefanco.gym.GymApplication`).
- Layered layout under `src/main/java/com/kstefanco/gym/`:
  - `entity/` — JPA entities (e.g. `User` uses `@Entity`, table `users`, UUID id).
  - `repository/` — Spring Data `JpaRepository` interfaces (e.g. `UserRepository` with `findByEmail`).
  - `service/` — business logic (constructor injection via Lombok `@RequiredArgsConstructor`). Note: the file `UserService.java` contains a class named `AuthService` — watch for file/class name mismatches.
  - `controller/` — REST controllers (currently `UserController` is empty; implement endpoints here).
  - `dto/` — small DTOs implemented as Java records (example: `RegisterRequest` with `email` and `password`).

2) Key technical details and conventions
- Java version: 21 (see `pom.xml` property `<java.version>`).
- Spring Boot: 4.0.6 (parent in `pom.xml`).
- Uses Jakarta Persistence (imports like `jakarta.persistence.*`).
- UUID primary keys: `@GeneratedValue(strategy = GenerationType.UUID)` on `User.id`.
- Lombok is used extensively (`@Builder`, `@Getter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@RequiredArgsConstructor`). Maven compiler is configured to provide Lombok as an annotation processor.
- Persistence: `spring-boot-starter-data-jpa` + `org.postgresql:postgresql` (runtime). There are no datasource properties in `application.properties` — the app expects external DB configuration or environment-based properties during development.

3) Build / run / test (Windows PowerShell examples)
- Build (with wrapper):
  .\mvnw.cmd -DskipTests package
- Run (dev):
  .\mvnw.cmd spring-boot:run
- Run tests:
  .\mvnw.cmd test
- Notes:
  - The project contains a minimal `GymApplicationTests` that boots the context. If a real DB is required the test may need an in-memory config or Testcontainers.
  - IDEs must have Lombok annotation processing enabled (or tests/compilation will fail locally).

4) Integration points & environment
- Database: PostgreSQL at runtime. Because `application.properties` only sets `spring.application.name=gym`, agents should not assume an existing JDBC URL — either add `spring.datasource.*` for local dev or rely on environment variables / profiles.
- Security: `spring-boot-starter-security` is present in `pom.xml`. No security configuration currently in source; adding controllers/endpoints will likely surface default Spring Security behavior (401 on endpoints) unless disabled or configured.

5) Project-specific patterns and gotchas (do not ignore)
- File/class name mismatch: `src/main/java/.../service/UserService.java` defines `public class AuthService` — searches by filename may be misleading. Always open files and use fully-qualified class names rather than relying on filename.
- DTO style: small immutable Java records (see `RegisterRequest`). Favor record patterns for simple payloads.
- Repositories return Optional (e.g. `Optional<User> findByEmail(String email)`), follow that convention in service logic.
- Entity mapping: table name is explicitly `users` in `User` entity — SQL/schema tasks should reference that exact table.

6) Where to make common changes
- Add/modify REST endpoints: `src/main/java/com/kstefanco/gym/controller/UserController.java`.
- Business logic: `src/main/java/com/kstefanco/gym/service/` (note class name mismatch described above).
- Data model changes: `src/main/java/com/kstefanco/gym/entity/` and migration scripts (none present — use Flyway/Liquibase if adding migrations).
- DB config and profiles: `src/main/resources/application.properties` (currently minimal). Add `application-dev.properties`/`application-local.yml` as needed.

7) Quick examples (copy/paste)
- Register flow currently implemented in service:
  - `RegisterRequest` record -> `AuthService.register(RegisterRequest)` builds a `User` with `User.builder().email(...).password(...).build()` and calls `userRepository.save(user)`.

8) Recommended first tasks for an agent (in order)
- Fix the file/class naming confusion in `service/` (rename file or class) or document it in code comments.
- Implement `UserController` endpoints (POST /register) that call the service layer.
- Add local dev DB properties (or a `-dev` profile) and document how to start the app locally.

9) Where not to guess
- Do not assume any database URL, credentials, or migration tooling — none are present.
- Do not assume custom security rules — none are implemented; default Spring Security will apply.

References (key files)
- `pom.xml` — dependencies, Java version, Lombok config
- `src/main/java/com/kstefanco/gym/GymApplication.java`
- `src/main/java/com/kstefanco/gym/entity/User.java`
- `src/main/java/com/kstefanco/gym/repository/UserRepository.java`
- `src/main/java/com/kstefanco/gym/service/UserService.java` (class `AuthService`)
- `src/main/java/com/kstefanco/gym/dto/RegisterRequest.java`
- `src/main/resources/application.properties`

If you need more detail about any area, open the file referenced above and include the exact class and package path in your request.

