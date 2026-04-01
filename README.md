# 📝 Spring Boot Blog REST API

A production-ready **Blog REST API** built using **Spring Boot 3** following clean architecture and best practices.

This project currently implements **Post** and **Comment** modules with proper **One-to-Many relationship**, DTO pattern, and RESTful design principles.

---

## 🚀 Project Description

This application provides backend services for a blogging platform where users can:

- Create, update, delete, and fetch blog posts
- Add and manage comments for each post
- Maintain relational data between posts and comments

The project is designed with scalability and maintainability in mind using a layered architecture:

```

Controller → Service → Repository → Database

```

---

## 🛠️ Tech Stack

| Category        | Technology |
|----------------|------------|
| Language       | Java 17 |
| Framework      | Spring Boot 3 |
| ORM            | Spring Data JPA (Hibernate) |
| Database       | PostgreSQL / Oracle |
| Build Tool     | Maven |
| Boilerplate    | Lombok |
| API Style      | RESTful APIs |
| Architecture   | Layered Architecture (Controller-Service-Repository) |

---

## 📂 Project Structure

```

com.blog.api
│
├── controller        # REST Controllers
├── service           # Business Logic
├── service.impl      # Service Implementations
├── repository        # JPA Repositories
├── entity            # Database Entities
├── dto               # Data Transfer Objects
├── exception         # Custom Exceptions
└── config            # Configurations

````

---

## ✨ Features Implemented

### ✅ Post Module
- Create Post
- Get All Posts
- Get Post by ID
- Update Post
- Delete Post

### ✅ Comment Module
- Add Comment to Post
- Get All Comments by Post ID
- Get Comment by ID (within a post)
- Update Comment
- Delete Comment

### 🔗 Relationships
- **One-to-Many Mapping**
  - One Post → Many Comments
- Bidirectional mapping handled using JPA

---

## 📌 API Endpoints

### 📄 Post APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/posts` | Create new post |
| GET    | `/api/posts` | Get all posts |
| GET    | `/api/posts/{id}` | Get post by ID |
| PUT    | `/api/posts/{id}` | Update post |
| DELETE | `/api/posts/{id}` | Delete post |

---

### 💬 Comment APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/posts/{postId}/comments` | Add comment to a post |
| GET    | `/api/posts/{postId}/comments` | Get all comments of a post |
| GET    | `/api/posts/{postId}/comments/{id}` | Get specific comment |
| PUT    | `/api/posts/{postId}/comments/{id}` | Update comment |
| DELETE | `/api/posts/{postId}/comments/{id}` | Delete comment |

---

## 📦 Request & Response Example

### Create Post

### Create Post


````
```json
POST /api/posts

{
"title": "My First Blog", 
"description": "This is description",
"content": "This is content"
}

---

### Create Comment

POST /api/posts/1/comments

{
"name": "Yaamin",
"email": "yaamin@gmail.com",
"body": "Great post!"
}
```

---

## ⚙️ Setup & Run Locally

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Yaamin1921/spring-boot-blog-rest-api.git
cd spring-boot-blog-rest-api
```

### 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

### 3️⃣ Run Application

```bash
mvn spring-boot:run
```

App will start at:

```
http://localhost:8080
```

---

## 🧠 Concepts Covered

* REST API Design
* DTO Pattern
* Entity Relationships (One-to-Many)
* Exception Handling
* Layered Architecture
* Spring Data JPA
* Request Validation (if added)

---

## 🔄 Future Enhancements

* 🔐 Spring Security + JWT Authentication
* 👤 User Module
* 📄 Pagination & Sorting
* 🔍 Search & Filtering
* 📘 Swagger/OpenAPI Documentation
* ☁️ Deployment (Docker + AWS)

---

## 👨‍💻 Author

**Mohd Yaamin**

* GitHub: [https://github.com/Yaamin1921](https://github.com/Yaamin1921)
* LinkedIn: [https://www.linkedin.com/in/mohd-yaamin/](https://www.linkedin.com/in/mohd-yaamin/)

---

## ⭐ Contribute

Feel free to fork this repository and contribute by submitting a pull request.

---

## 📜 License

This project is open-source and available under the MIT License.

```

---


