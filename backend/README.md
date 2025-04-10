# FinTrack – Backend (Spring Boot)

This is the backend module of the **FinTrack** personal finance tracker, developed using **Spring Boot**, **Java 17**, and connected to **Microsoft Azure MySQL** for database hosting.

---

## 🔧 Setup & Configuration

### Development Tools:
- **IntelliJ IDEA**
- **Spring Boot 3.x**
- **Java 17**
- **Maven**
- **MySQL (Azure Hosted)**

### Branching
- This work is done on: `muhammad-backend`
- Purpose: Track individual backend development work

### Current DB Connection (Azure)
```properties
spring.datasource.url=jdbc:mysql://<your-azure-server>:3306/fintrack
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
