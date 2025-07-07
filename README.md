## 🧼 Clean Architecture Example – Spring Boot + React (TypeScript)

A simple and practical example of **Clean Architecture**, combining:

* 🧠 **Spring Boot** (Backend)
* ⚛️ **React + TypeScript** (Frontend)

Ideal for tutorials, studies, or learning best practices in **full-stack** projects.

---

### 📁 Project Structure

```

/backend        → REST API in Spring Boot (Clean Architecture)
/frontend       → Interface built with React + TypeScript

````

---

### 🚀 Technologies Used

#### Backend

- Java 21+
- Spring Boot:
  - Spring Web  
  - Spring Data JPA  
  - Spring Security (Basic Auth for Actuator)  
  - Spring Cache (Redis)  
  - Spring Actuator (metrics, health, threaddump…)  
  - Springdoc OpenAPI (Swagger UI)  
- Hibernate / JPA  
- PostgreSQL  
- Redis (cache)  
- Clean Architecture (Domain, Use Cases, Infra)

#### Frontend

- React  
- TypeScript  
- Vite  
- React Hooks (`useState`, `useEffect`, `useRef`, etc.)

---

### 📦 How to Run the Project

#### 1. Clone the repository

```bash
git clone https://github.com/DRTX2/clean-architecture.git
cd clean-architecture
````

#### 2. Configure environment variables

Copy the example and edit if necessary:

```bash
cp .env.example .env
```

#### 3. Start all services with Docker Compose

```bash
docker compose up --build -d   # Builds images and starts the app, Postgres and Redis in background
```

Verify that everything is running:

```bash
docker compose ps
```

Check application logs if needed:

```bash
docker compose logs -f app
```

To stop and remove containers and volumes:

```bash
docker compose down -v
```

Access the backend at: `http://localhost:8080`

#### 4. Frontend (React + Vite)

```bash
cd frontend/clean
npm install
npm run dev
```

Access the frontend at: `http://localhost:5173`

---

### ⭐ Example Features

* 🔄 Frontend ↔ backend communication
* ✅ Clean Architecture: well-separated layers
* ⚙️ **Actuator**:

  * `GET /actuator/health`, `metrics`, `loggers`, `threaddump`
* 📄 **Swagger UI** (Springdoc OpenAPI):

  * UI: `http://localhost:8080/swagger-ui/index.html`
  * Spec JSON: `http://localhost:8080/v3/api-docs`
* 🗄️ **Cache** with Redis (`@Cacheable("users")`)
* 🔒 **Security**: Basic Auth for Actuator (`admin` / bcrypt password)

---

### ✍️ Author

Developed by **David**, Software Engineer, passionate about Java, Spring, PHP, TS and React.
