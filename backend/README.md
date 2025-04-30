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
spring.datasource.url=jdbc:mysql://<your-azure-server>:3306/fintrack
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
✅ April 30 Updates
Added and tested login + registration API endpoints

Integrated SecurityConfig.java and CustomUserDetailsService.java

Encoded passwords with BCryptPasswordEncoder

Tested registration with Postman

Confirmed redirects and form login setup

Merged changes into muhammad-backend and pushed