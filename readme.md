# 🔗 URL Shortener

A URL Shortener backend application built with **Java, Spring Boot, Spring Data JPA, and PostgreSQL**.

The application lets users submit a long URL, validates it, generates a unique short key, and stores the mapping between the original URL and the short key in PostgreSQL.

> 🚧 **Status:** This project is currently under development.

---

## 📑 Table of Contents

- [Features](#-features)
- [Tech Stack](#️-tech-stack)
- [Architecture](#️-architecture)
- [Project Structure](#-project-structure)
- [How URL Shortening Works](#-how-url-shortening-works)
- [Short Key Generation](#-short-key-generation)
- [API Endpoints](#-api-endpoints)
- [Database](#️-database)
- [Configuration](#️-configuration)
- [Running the Project](#️-running-the-project)
- [Testing with Postman](#-testing-with-postman)
- [Development Roadmap](#️-development-roadmap)
- [Current Limitations](#️-current-limitations)
- [Author](#-author)

---

## 🚀 Features

### Currently Implemented

- User registration
- User login
- URL validation
- Random short key generation
- Unique short key verification
- Storing the original URL and short key
- PostgreSQL database integration
- Spring Data JPA
- REST APIs
- Click count tracking field

### Upcoming Features

- Short URL redirection
- Click count increment
- Custom short URLs
- URL expiration
- Private URLs
- User-specific URLs
- Password encryption
- JWT authentication
- URL analytics

---

## 🛠️ Tech Stack

| Category | Technologies |
|----------|--------------|
| **Language** | Java 21 |
| **Framework** | Spring Boot, Spring Web |
| **Persistence** | Spring Data JPA, Hibernate |
| **Database** | PostgreSQL |
| **Build Tool** | Maven |
| **Tools** | Postman, IntelliJ IDEA, Git, GitHub |

---

## 🏗️ Architecture

The project follows a simple layered architecture:

```text
Client / Postman
       │
       ▼
   Controller
       │
       ▼
    Service
       │
       ▼
   Repository
       │
       ▼
  PostgreSQL
```

| Layer | Responsibility |
|-------|----------------|
| **Controller** | Handles HTTP requests and responses. |
| **Service** | Contains the business logic: URL validation, short key generation, uniqueness checking, and creating shortened URLs. |
| **Repository** | Uses Spring Data JPA to interact with PostgreSQL. |
| **Entity** | Represents database tables and their relationships. |

---

## 📂 Project Structure

```text
src/main/java/com/url/Shortner/
│
├── Controller/
│   ├── ShortUrlController.java
│   └── Home.java
│
├── Entities/
│   ├── ShortUrl.java
│   └── User.java
│
├── Repository/
│   ├── ShortUrlRepository.java
│   └── UserRepository.java
│
├── Services/
│   ├── ShortUrlService.java
│   └── Validity.java
│
└── ShortnerApplication.java
```

---

## 🔄 How URL Shortening Works

The user sends a long URL, for example:

```text
https://www.google.com
```

The application then processes it as follows:

```text
Long URL
   │
   ▼
Validate URL
   │
   ▼
Generate Random Short Key
   │
   ▼
Check Key Uniqueness
   │
   ▼
Create ShortUrl Entity
   │
   ▼
Save in PostgreSQL
   │
   ▼
Return Short Key
```

**Example**

```text
Original URL:      https://www.google.com
Generated Key:     Iltnb9
```

The mapping stored in the database:

```text
shortkey    → Iltnb9
originalUrl → https://www.google.com
clickCount  → 0
```

---

## 🔑 Short Key Generation

The application generates a random **6-character** key using uppercase letters, lowercase letters, and digits:

```text
ABCDEFGHIJKLMNOPQRSTUVWXYZ
abcdefghijklmnopqrstuvwxyz
0123456789
```

Keys are generated with `SecureRandom`.

**Example keys**

```text
Iltnb9
aB72xK
P91ksL
X8mQ2z
```

Before saving a URL, the application checks whether the generated key already exists. If it does, a new key is generated.

---

## 📡 API Endpoints

Base URL: `http://localhost:8086`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/saveuser` | Register a new user |
| `POST` | `/api/login` | Log in a user |
| `POST` | `/api/sendurl` | Shorten a URL |

### 1. Register User

**Endpoint**

```http
POST /api/saveuser
```

**Request Body**

```json
{
  "email": "hritik@example.com",
  "name": "Hritik",
  "password": "123456"
}
```

**Example Response**

```text
Registered Successfully
```

### 2. Login User

**Endpoint**

```http
POST /api/login
```

**Request Parameters**

| Parameter | Example |
|-----------|---------|
| `email` | `hritik@example.com` |
| `password` | `123456` |

**Example Request**

```http
POST http://localhost:8086/api/login?email=hritik@example.com&password=123456
```

**Success Response**

```text
Login successfully
```

### 3. Shorten URL

**Endpoint**

```http
POST /api/sendurl
```

**Request Parameter**

| Parameter | Example |
|-----------|---------|
| `url` | `https://www.google.com` |

**Example Request**

```http
POST http://localhost:8086/api/sendurl?url=https://www.google.com
```

**Example Response**

```text
URL shortened successfully: Iltnb9
```

---

## 🗄️ Database

The project uses **PostgreSQL**.

- **Database:** `postgres`
- **Tables:** `users`, `shorturl`

### 👤 User Entity

The `users` table stores registered user information.

| Field | Description |
|-------|-------------|
| `id` | Unique identifier |
| `email` | User's email address |
| `password` | User's password |
| `name` | User's name |
| `createdAt` | Registration timestamp |

### 🔗 ShortUrl Entity

The `shorturl` table stores shortened URL information.

| Field | Description |
|-------|-------------|
| `id` | Unique identifier |
| `shortkey` | Generated short key |
| `originalUrl` | The original long URL |
| `createdBy` | User who created the short URL |
| `privateOnly` | Private-access setting |
| `isPrivate` | Whether the URL is private |
| `createdAt` | Creation timestamp |
| `expiredAt` | Expiration timestamp |
| `clickCount` | Number of clicks |

---

## ⚙️ Configuration

The application configuration is located at:

```text
src/main/resources/application.yaml
```

**Example configuration**

```yaml
spring:
  application:
    name: Shortner

  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: YOUR_PASSWORD

  jpa:
    hibernate:
      ddl-auto: update

server:
  port: 8086
```

Replace `YOUR_PASSWORD` with your PostgreSQL password.

> ⚠️ **Do not commit your actual database password to GitHub.**

---

## ▶️ Running the Project

### Prerequisites

- Java 21
- Maven
- PostgreSQL

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd Shortner
```

### 2. Start PostgreSQL

Make sure PostgreSQL is running on `localhost:5432`.

### 3. Configure the Database

Update your PostgreSQL username and password in `src/main/resources/application.yaml`.

### 4. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run `ShortnerApplication.java` directly from IntelliJ IDEA.

The application will start on:

```text
http://localhost:8086
```

---

## 🧪 Testing with Postman

You can use Postman to test the APIs.

**Example: Shorten a URL**

```text
POST http://localhost:8086/api/sendurl
```

Add the following query parameter:

| KEY | VALUE |
|-----|-------|
| `url` | `https://www.google.com` |

**Expected response**

```text
URL shortened successfully: Iltnb9
```

---

## 🗺️ Development Roadmap

The next major feature is **URL Redirection**. The planned flow:

```text
http://localhost:8086/Iltnb9
              │
              ▼
       Find Short Key
              │
              ▼
     Fetch Original URL
              │
              ▼
     Increase Click Count
              │
              ▼
    Redirect to Original URL
```

### Future Improvements

- [ ] URL redirection
- [ ] Click count increment
- [ ] Custom short URLs
- [ ] URL expiration
- [ ] Private URLs
- [ ] User URL dashboard
- [ ] BCrypt password hashing
- [ ] JWT authentication
- [ ] Exception handling
- [ ] DTOs
- [ ] API documentation
- [ ] Unit testing
- [ ] Deployment

---

## ⚠️ Current Limitations

This project is under development. At the moment:

- Passwords are stored without encryption.
- JWT authentication is not implemented.
- URL redirection is not implemented yet.
- Advanced authorization is not implemented.
- API error handling is still basic.
- Login credentials are sent as query parameters, which is not secure.

These items will be addressed in future versions.

---

## 👨‍💻 Author

**Hritik Raj**
B.Tech Computer Science (Data Science)

**Technologies:** Java · Spring Boot · Spring Data JPA · PostgreSQL · Git · REST API

---

## ⭐ Support

If you find this project useful, consider giving the repository a star ⭐