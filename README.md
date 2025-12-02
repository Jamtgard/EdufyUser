# EdufyUser
[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)  
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen.svg)](https://spring.io/projects/spring-boot)

## 👤 Overview
EdufyUser manages user accounts within the Edufy platform.  
The service handles profile creation, updates, user retrieval, and provides user-related data to other microservices.  
It runs as part of the Edufy microservice ecosystem via Docker Compose.

---

## 🧩 Related projects

### Organization
- [EdufyProjects](https://github.com/EudfyProjects)

### Connections
- [Edufy-infra](https://github.com/EudfyProjects/Edufy-infra) – Docker-compose + init.db
- [EdufyEurekaServer](https://github.com/Sommar-skog/EdufyEurekaServer) – Service discovery
- [Gateway](https://github.com/SaraSnail/EdufyGateway) – Entry point for all requests
- [EdufyKeycloak](https://github.com/Sommar-skog/EdufyKeycloak) – Keycloak pipeline for auth

### Media connections
- [EdufyCreator](https://github.com/Sommar-skog/EdufyCreator) – Creators
- [EdufyGenre](https://github.com/a-westerberg/EdufyGenre) – Genres
- [EdufyThumb](https://github.com/a-westerberg/EdufyThumb) – Thumbs up/down records
- [EdufyUtility](https://github.com/a-westerberg/EdufyUtility) – Placeholder for algorithms

### Media Services
- [EdufyMusic](https://github.com/Jamtgard/EdufyMusic) - Music
- [EdufyVideo](https://github.com/Sommar-skog/EdufyVideo) - Video
- [EdufyPod](https://github.com/SaraSnail/EdufyPod) - Pod

---

## 🚀 Tech Stack

- **Language:** Java 21
- **Build Tool:** Maven
- **Framework:** Spring Boot 3.5.7
    - Spring Web
    - Spring Data JPA
    - Spring Security
    - Eureka Client
    - Spring Cloud Loadbalancer
- **Databases:**
    - MySQL 8.0 (Docker)
    - H2 (Development)
- **Security:** 
  - OAuth2 Resource Server (Keycloak)


---

## 🏁 Getting Started

### Prerequisites
- Java 21
- Maven
- Docker
- Postman
- Keycloak

---

## 🔌 Ports

#### Connections
- **Eureka:** `8761`
- **Gateway:** `4545`
- **MySQL:** `3307`
- **User:** `8686`
- **Keycloak:** `8080`

#### Media connections
- **Creator:** `8787`
- **Genre:** `8585`
- **Thumb:** `8484`
- **Utility:** `8888`

#### Media services
- **Video:** `8383`
- **Music:** `8181`
- **Pod:** `8282`

---

## 🔒 Authentication & Roles

EdufyUser uses **OAuth2 + Keycloak** for authentication and authorization.

### User Roles
- **edufy_realm_admin** – Full administrative privileges
- **user_admin** – Manage user accounts
- **user_user** – Standard user
- **microservice_access** – Internal microservice communication

| Role                | Username            | Password |
|---------------------|---------------------|----------|
| user_admin          | user_admin          | admin    |
| edufy_realm_admin   | edufy_realm_admin   | admin    |
| user_user           | user_user           | user     |
| microservice_access | –                   | –        |

> Unauthenticated requests will receive `401 Unauthorized`.  
> `microservice_access` is used between microservices for internal authorization.

---

## 📚 API Endpoints

### Admin – Roles `user_admin` & `edufy_realm_admin`
- **POST** `/user/admin/create-user` – Create a new user
- **GET** `/user-id/{id}` – Get user by id
- **GET** `/user-sub/{sub}` - get user by sub/uuid

---

### Common – Any authenticated user
- **GET** `/user/all-users` – Get all users

---

### Client – Role `microservice_access`
- **GET** `/user/user-sub/{sub}/clientcall` – Get user by sub/uuid
- **GET** `/user-id/{id}/clientcall` – Get user by id

---

## 🐳 Docker

- This service runs through `docker-compose.yml` located in **Edufy-infra**.
- Network: `edufy-network`.

---

## 🛢️ MySQL Database

| Name               | User | Pass | Database |
|--------------------|------|------|----------|
| edufy_mysql        | assa | assa | main     |
| edufy_user_db      | assa | assa | user     |

- **Version:** MySQL 8.0
- **Port:** `3306` mapped as `3307:3306`
- Global init scripts come from Edufy-infra.
- Dev mode uses `data.sql`.

- **Connection Example :**
  ```
    spring.datasource.url=jdbc:mysql://edufy-mysql:3306/edufy_pod_db
    spring.datasource.username=assa
    spring.datasource.password=assa
    spring.jpa.hibernate.ddl-auto=update
  ```

> _README made by [Sommar-skog](https://github.com/Sommar-skog)_
