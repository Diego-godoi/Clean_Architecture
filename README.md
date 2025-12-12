## 📦 Clean Architecture — Java + Spring Boot

This project was developed to study and apply **Clean Architecture**, organizing code in independent layers for maintainability, scalability, and testability.

---

## 🏗 Technologies Used

* **Java 21 (JDK 21)**
* **Spring Boot**
* **Maven**
* **PostgreSQL**
* **Docker**
* **Clean Architecture**
* **IntelliJ IDEA**

---

## 📁 Project Structure

```
com.diego.cleanArch/
 ├─ application/    → Use cases (application rules)
 ├─ core/           → Entities, business rules, exceptions, and ports
 ├─ infra/          → Config, Persistence, Repository
 └─ adapter         → Controllers, DTOs, handlers
```

* The **core** is independent of frameworks.
* The **application** contains application-specific rules.
* The **infra** layer contains external details (Spring, database, etc.).

---

# ▶ How to Run the Project

## 1️⃣ Clone the repository

```sh
git clone https://github.com/Diego-godoi/Clean_Architecture.git
cd Clean_Architecture
```

---

## 2️⃣ Create the `.env` file

Create a **.env** file in the project root with your database configuration:

```env
POSTGRES_DB=cleanArch
POSTGRES_USER=postgres
POSTGRES_PASSWORD=password
POSTGRES_PORT=5432
POSTGRES_HOST=localhost
PORT=8080
```

> These environment variables are automatically loaded by Docker and Spring Boot.

---

# ▶ Running with Docker

You can run **PostgreSQL and the API** together using Docker and Docker Compose.

## 3️⃣ Build and start everything

```sh
docker-compose up --build
```

This will:

1. Build the API image.
2. Start a PostgreSQL container configured with your `.env`.
3. Start the API container and wait for Postgres to be healthy before launching.

> **Note:** If you previously ran Postgres containers, remove old volumes to ensure the database is recreated correctly:

```sh
docker-compose down
docker volume rm cleanarch_postgres_data
```

---

## 4️⃣ Access the API

Once everything is up, the API will be available at:

```
http://localhost:8080
```

### Example endpoint:

```
http://localhost:8080/users
```

---

# ▶ Running Without Docker

If you want to run the API locally:

1. Install dependencies:

```sh
mvn clean install -DskipTests
```

2. Start the application:

```sh
mvn spring-boot:run
```

> Make sure PostgreSQL is running locally and the `.env` values match your database.

---

# 📡 Endpoints

Use tools like:

* Postman
* Thunder Client
* Insomnia
* cURL

Swagger is not configured yet (can be added later).

---

# 🤝 Contribution

Feel free to open PRs or issues with suggestions or improvements.

---

# 📄 License

MIT License

---
