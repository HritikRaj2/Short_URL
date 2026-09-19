Yes — copy everything inside this block directly into your `README.md`:

````markdown
# 🔗 URL Shortener

A simple URL Shortener backend application built using **Java, Spring Boot, Spring Data JPA, and PostgreSQL**.

The application allows users to submit a long URL, validates it, generates a unique short key, and stores the mapping between the original URL and the generated short key in PostgreSQL.

> 🚧 This project is currently under development.

---

## 🚀 Features

### Currently Implemented

- User Registration
- User Login
- URL Validation
- Random Short Key Generation
- Unique Short Key Verification
- Store Original URL and Short Key
- PostgreSQL Database Integration
- Spring Data JPA
- REST APIs
- Click Count Tracking Field

### Upcoming Features

- Short URL Redirection
- Click Count Increment
- Custom Short URLs
- URL Expiration
- Private URLs
- User-specific URLs
- Password Encryption
- JWT Authentication
- URL Analytics

---

## 🛠️ Tech Stack

### Backend

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate

### Database

- PostgreSQL

### Tools

- Maven
- Postman
- IntelliJ IDEA
- Git
- GitHub

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
````

### Controller

Handles HTTP requests and responses.

### Service

Contains the main business logic such as:

* URL validation
* Short key generation
* Short key uniqueness checking
* Creating shortened URLs

### Repository

Uses Spring Data JPA to interact with PostgreSQL.

### Entity

Represents database tables and their relationships.

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

The user sends a long URL:

```text
https://www.google.com
```

The application then:

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

Example:

```text
Original URL:
https://www.google.com

Generated Short Key:
Iltnb9
```

The mapping is stored in the database:

```text
shortkey    → Iltnb9
originalUrl → https://www.google.com
clickCount  → 0
```

---

## 🔑 Short Key Generation

The application generates a random 6-character key using uppercase letters, lowercase letters, and numbers.

The available characters are:

```text
ABCDEFGHIJKLMNOPQRSTUVWXYZ
abcdefghijklmnopqrstuvwxyz
0123456789
```

`SecureRandom` is used to generate the key.

Example:

```text
Iltnb9
aB72xK
P91ksL
X8mQ2z
```

Before saving the URL, the application checks whether the generated key already exists.

If the key already exists, a new key is generated.

---

# 📡 API Endpoints

## 1. Register User

### Endpoint

```http
POST /api/saveuser
```

### Request Body

```json
{
  "email": "hritik@example.com",
  "name": "Hritik",
  "password": "123456"
}
```

### Example Response

```text
Registered Successfully
```

---

## 2. Login User

### Endpoint

```http
POST /api/login
```

### Request Parameters

```text
email=hritik@example.com
password=123456
```

### Example Request

```http
POST http://localhost:8086/api/login?email=hritik@example.com&password=123456
```

### Success Response

```text
Login successfully
```

---

## 3. Shorten URL

### Endpoint

```http
POST /api/sendurl
```

### Request Parameter

```text
url=https://www.google.com
```

### Example Request

```http
POST http://localhost:8086/api/sendurl?url=https://www.google.com
```

### Example Response

```text
URL shortened successfully: Iltnb9
```

---

# 🗄️ Database

The project uses **PostgreSQL**.

### Database

```text
postgres
```

### Tables

```text
users
shorturl
```

---

## 👤 User Entity

The `users` table stores registered user information.

Important fields:

```text
id
email
password
name
createdAt
```

---

## 🔗 ShortUrl Entity

The `shorturl` table stores shortened URL information.

Important fields:

```text
id
shortkey
originalUrl
createdBy
privateOnly
isPrivate
createdAt
expiredAt
clickCount
```

---

# ⚙️ Configuration

The application configuration is located at:

```text
src/main/resources/application.yaml
```

Example configuration:

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

Replace:

```text
YOUR_PASSWORD
```

with your PostgreSQL password.

> Do not commit your actual database password to GitHub.

---

# ▶️ Running the Project

## 1. Clone the Repository

```bash
git clone <your-repository-url>
```

Navigate to the project:

```bash
cd Shortner
```

---

## 2. Start PostgreSQL

Make sure PostgreSQL is running on:

```text
localhost:5432
```

---

## 3. Configure Database

Update your PostgreSQL username and password in:

```text
src/main/resources/application.yaml
```

---

## 4. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run:

```text
ShortnerApplication.java
```

from IntelliJ IDEA.

The application will start on:

```text
http://localhost:8086
```

---

# 🧪 Testing with Postman

You can use Postman to test the APIs.

### Example: Shorten URL

```text
POST http://localhost:8086/api/sendurl
```

Add the following parameter:

```text
KEY       VALUE
-------------------------------
url       https://www.google.com
```

Expected response:

```text
URL shortened successfully: Iltnb9
```

---

# 🗺️ Development Roadmap

The next major feature is **URL Redirection**.

The planned flow is:

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

* [ ] URL Redirection
* [ ] Click Count Increment
* [ ] Custom Short URLs
* [ ] URL Expiration
* [ ] Private URLs
* [ ] User URL Dashboard
* [ ] BCrypt Password Hashing
* [ ] JWT Authentication
* [ ] Exception Handling
* [ ] DTOs
* [ ] API Documentation
* [ ] Unit Testing
* [ ] Deployment

---

# ⚠️ Current Limitations

This project is currently under development.

At the moment:

* Passwords are stored without encryption.
* JWT authentication is not implemented.
* URL redirection is not implemented yet.
* Advanced authorization is not implemented.
* API error handling is still basic.

These features will be added in future versions.

---

# 👨‍💻 Author

**Hritik Raj**

B.Tech Computer Science (Data Science)

### Technologies

```text
Java
Spring Boot
Spring Data JPA
PostgreSQL
Git
REST API
```

---

## ⭐ Support

If you find this project useful, consider giving the repository a star ⭐

```
```
