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
- A separate branch `test-william` was created to safely test William’s updates before merging
- After confirming everything worked, `test-william` was merged into `muhammad-backend` and pushed

### Current DB Connection (Azure)
```properties
spring.datasource.url=jdbc:mysql://fintrack-server.mysql.database.azure.com:3306/fintrack_db
spring.datasource.username=wjdm123
spring.datasource.password=farm123$
spring.jpa.hibernate.ddl-auto=update

✅ Updates

Added and tested login + registration API endpoints

Integrated SecurityConfig.java and CustomUserDetailsService.java

Encoded passwords using BCryptPasswordEncoder

Tested user registration using Postman

Verified redirect behavior and login form setup

Merged updates into muhammad-backend and pushed

vbnet
Copy code
