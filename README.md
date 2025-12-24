# 📦 Clean Architecture API — Java + Spring Boot

A RESTful API built with **Clean Architecture** principles, providing user management endpoints with complete CRUD operations.

---

## 🏗 Technologies

* **Java 21 (JDK 21)**
* **Spring Boot 4.0.0**
* **Maven**
* **PostgreSQL 16**
* **Docker & Docker Compose**
* **Swagger/OpenAPI 3.0** for API documentation
* **Clean Architecture Pattern**

---

## 📁 Project Structure

```
com.diego.cleanArch/
 ├─ adapter/        → Controllers, DTOs, Exception Handlers
 ├─ application/    → Use Cases (Application Business Rules)
 ├─ core/           → Entities, Domain Rules, Exceptions, Ports
 └─ infra/          → Configuration, Persistence, Repositories
```

**Key principles:**
- The **core** layer is completely independent of frameworks
- The **application** layer contains use cases and orchestration
- The **infra** layer handles external concerns (database, Spring, etc.)
- Dependencies point inward (infrastructure → application → core)

---

## 🚀 Quick Start

### Option 1: Using Docker Hub (Recommended)

Pull and run the pre-built image:

```bash
docker pull diegos01/clean-arch-api:latest

docker run -d \
  --name clean-arch-api \
  -e POSTGRES_HOST=your-postgres-host \
  -e POSTGRES_PORT=5432 \
  -e POSTGRES_DB=cleanArch \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=yourpassword \
  -p 8080:8080 \
  diegos01/clean-arch-api:latest
```

### Option 2: Using Docker Compose

Clone the repository and start everything with one command:

```bash
git clone https://github.com/Diego-godoi/Clean_Architecture.git
cd Clean_Architecture
```

Create a `.env` file:

```env
POSTGRES_DB=cleanArch
POSTGRES_USER=postgres
POSTGRES_PASSWORD=yourpassword
POSTGRES_PORT=5432
PORT=8080
```

Start the services:

```bash
docker-compose up -d
```

This will:
1. Start a PostgreSQL 16 container
2. Wait for the database to be healthy
3. Start the API connected to the database

### Option 3: Running Locally

**Prerequisites:**
- Java 21 installed
- PostgreSQL running locally
- Maven installed

**Steps:**

1. Clone the repository:
```bash
git clone https://github.com/Diego-godoi/Clean_Architecture.git
cd Clean_Architecture
```

2. Create a `.env` file or set environment variables:
```env
POSTGRES_HOST=localhost
POSTGRES_PORT=5432
POSTGRES_DB=cleanArch
POSTGRES_USER=postgres
POSTGRES_PASSWORD=yourpassword
PORT=8080
```

3. Install dependencies:
```bash
./mvnw clean install -DskipTests
```

4. Run the application:
```bash
./mvnw spring-boot:run
```

---

## 📚 API Documentation (Swagger)

The API includes **interactive documentation** powered by Swagger/OpenAPI 3.0.

### Accessing Swagger UI

Once the application is running, access the documentation at:

**Swagger UI:** `http://localhost:8080/swagger-ui/index.html`

**OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

### Features

✅ **Interactive testing** - Try all endpoints directly from the browser  
✅ **Request/Response examples** - See exactly what data to send and expect  
✅ **Schema documentation** - Complete data models and validation rules  
✅ **Error responses** - All possible HTTP status codes documented (400, 404, 409, etc.)  
✅ **No authentication required** - Perfect for development and testing

### What's Documented

All endpoints include:
- **Operation summary** and detailed descriptions
- **Request body schemas** with validation rules
- **Response codes:** 200, 201, 204, 400, 404, 409
- **Error response models** for exception handling
- **Parameter descriptions** for path variables and query params

---

## 📡 API Endpoints

Once running, the API will be available at `http://localhost:8080`

### User Endpoints

| Method | Endpoint | Description | Status Codes |
|--------|----------|-------------|--------------|
| `POST` | `/users` | Create a new user | 201, 400, 409 |
| `GET` | `/users` | Get all users | 200 |
| `PUT` | `/users` | Update a user | 200, 400, 404 |
| `DELETE` | `/users/{id}` | Delete a user by ID | 204, 404 |

### Example Requests

**Create User:**
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "securepass123"
  }'
```

**Response (201 Created):**
```json
{
  "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
  "name": "John Doe",
  "email": "john@example.com"
}
```

**Get All Users:**
```bash
curl http://localhost:8080/users
```

**Response (200 OK):**
```json
[
  {
    "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "name": "John Doe",
    "email": "john@example.com"
  }
]
```

**Update User:**
```bash
curl -X PUT http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
    "name": "John Updated",
    "email": "john.new@example.com",
    "password": "newpass123"
  }'
```

**Delete User:**
```bash
curl -X DELETE http://localhost:8080/users/a1b2c3d4-e5f6-7890-abcd-ef1234567890
```

**Response (204 No Content)**

---

## 🐳 Docker Hub

The API is available as a public Docker image:

**Image:** [`diegos01/clean-arch-api:latest`](https://hub.docker.com/r/diegos01/clean-arch-api)

**Pull the image:**
```bash
docker pull diegos01/clean-arch-api:latest
```

**Build and push your own version:**
```bash
# Build the image
docker build -t diegos01/clean-arch-api:latest .

# Push to Docker Hub
docker push diegos01/clean-arch-api:latest
```

---

## 🔧 Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `POSTGRES_HOST` | PostgreSQL host | `localhost` |
| `POSTGRES_PORT` | PostgreSQL port | `5432` |
| `POSTGRES_DB` | Database name | `cleanArch` |
| `POSTGRES_USER` | Database user | `postgres` |
| `POSTGRES_PASSWORD` | Database password | (required) |
| `PORT` | API server port | `8080` |

---

## 🛠 Development

### Project Structure Explained

**Core Layer (Business Logic):**
- `User.java` - Domain entity with validation rules
- `UserRepository.java` - Repository port (interface)
- `DomainException.java` - Domain-specific exceptions

**Application Layer (Use Cases):**
- `CreateUserUseCase.java` - Creates a new user
- `UpdateUserUseCase.java` - Updates user information
- `DeleteUserUseCase.java` - Deletes a user
- `FindAllUserUseCase.java` - Retrieves all users

**Adapter Layer (External Interface):**
- `UserController.java` - REST API endpoints with Swagger annotations
- `GlobalExceptionHandler.java` - Centralized error handling
- DTOs for request/response mapping

**Infrastructure Layer (Technical Details):**
- `UserRepositoryImpl.java` - Concrete repository implementation
- `SpringDataUserRepository.java` - Spring Data JPA interface
- `UserJpaEntity.java` - JPA entity for persistence
- `SwaggerConfig.java` - API documentation configuration
- `CorsConfig.java` - Cross-Origin Resource Sharing setup
- Configuration classes for dependency injection

---

## 📝 CORS Configuration

The API is configured to accept requests from:
- `http://localhost` (port 80)
- `http://localhost:3000`
- `http://localhost:5173`
- `http://127.0.0.1:5173`

To add more origins, edit `CorsConfig.java`.

---

## 🐛 Troubleshooting

**Database connection issues:**
```bash
# Check if PostgreSQL is running
docker ps | grep postgres

# View API logs
docker logs clean-arch-api

# Restart the API
docker-compose restart api
```

**Port already in use:**
```bash
# Change the PORT in .env file
PORT=8081

# Restart services
docker-compose up -d
```

**Swagger UI not loading:**
```bash
# Verify the application is running
curl http://localhost:8080/actuator/health

# Access Swagger at the correct URL
http://localhost:8080/swagger-ui/index.html
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author

**Diego Godoi**

- GitHub: [@Diego-godoi](https://github.com/Diego-godoi)
- Docker Hub: [diegos01](https://hub.docker.com/u/diegos01)
- Instagram: [@_.godoi_](https://www.instagram.com/_.godoi_/)

---

## 🎯 Roadmap

- [x] ~~Add Swagger/OpenAPI documentation~~ ✅ **Done!**
- [x] ~~Set up CI/CD pipeline~~ ✅ **Done!**
- [x] ~~Add unit and integration tests~~ ✅ **Done!**
- [ ] Implement authentication (JWT)
- [ ] Add Docker multi-stage builds
- [ ] Implement caching with Redis
- [ ] Add monitoring and health checks
- [ ] Add pagination for GET /users endpoint
- [ ] Implement request/response logging

---

**⭐ If you found this project helpful, please consider giving it a star!**