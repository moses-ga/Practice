# learning — Spring Boot admin template

A starting point for Spring Boot + Thymeleaf back-office apps. No login: running the app
opens straight on the dashboard. It ships with one model (`User`) and a full CRUD for it,
so a new model is a copy of the User files.

## Stack

- Spring Boot 4.1 (Java 17), Spring MVC, Thymeleaf + Layout Dialect
- Spring Data JPA + PostgreSQL, Flyway migrations
- Metronic 7 UI (static assets under `src/main/resources/static/assets`)
- Server-side DataTables via `spring-data-jpa-datatables`

## Run

1. Start the database (Docker):

       docker compose up -d

2. Run the app:

       ./mvnw spring-boot:run

3. Open <http://localhost:8080> — the dashboard. Users CRUD is at <http://localhost:8080/users/index>.

Database settings live in `src/main/resources/application-dev.properties` and `flyway.conf`
(both point at the compose database on port 5435). Copy `application-example.properties`
for another environment.

## Project layout

    io.kenzo.learning
    ├── model/            BaseEntity (id, isActive, createdAt, updatedAt) + User (name, email, phone, address)
    ├── repositories/     UserRepository (DataTablesRepository)
    ├── services/         UserService
    ├── controllers/      HomeController (dashboard), UserController (pages + forms)
    │   └── apis/         UserApiController (DataTables JSON at /api/users/list)
    ├── config/           MvcConfig (uploads folder, forwarded headers)
    ├── utils/            DataTablesUtils, Utils, GeneralResponse, ValidationUtils
    ├── exception/        ModelNotFoundException
    ├── enums/            Status
    └── viewModels/       DashboardStats

    resources/
    ├── db/migration/     V1__createUserTable.sql  (add V2__..., V3__... for new tables)
    ├── templates/
    │   ├── layout/master.html        page shell (header, side menu, alerts, scripts)
    │   ├── partials/side_menu.html   left menu
    │   ├── partials/_alerts.html     flash messages (success / error / warning / info)
    │   ├── dashboard.html
    │   └── users/index.html          DataTable + add/edit modals + delete confirm
    └── static/assets/                Metronic theme

## Adding a new model

1. `model/Thing.java` extending `BaseEntity`.
2. `db/migration/V2__createThingTable.sql` with the four base columns plus your own.
3. `repositories/ThingRepository extends DataTablesRepository<Thing, UUID>`.
4. `services/ThingService`, `controllers/ThingController`, `controllers/apis/ThingApiController`
   (use `DataTablesUtils.findAll(repository, input)` for the list endpoint).
5. `templates/things/index.html` copied from `users/index.html`, and a menu entry in `partials/side_menu.html`.
