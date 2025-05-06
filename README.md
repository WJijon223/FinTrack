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
- This work is done on: `william-backend`
- Purpose: Track individual backend development progress
- Collaborated closely with Muhammad to align on database structure and API design
- A separate test branch (`test-william`) was used for safe testing before merging into main backend branches

---

## 🗄️ Database Design & Integration

- Designed and created the **MySQL database schema** for the FinTrack project
- Defined and implemented core **tables** for users, budgets, and transactions
- Established **foreign key relationships** and constraints for data integrity
- Connected the **Spring Boot backend** to the **Microsoft Azure-hosted MySQL server**
- Verified connection stability and tested queries using sample data

---

## 🚀 API Development

- Contributed to **API development**, focusing specifically on the **Budget Endpoint**
- Created RESTful routes for **budget creation, retrieval, update, and deletion**
- Ensured that budget data correctly maps to the MySQL database using **Spring Data JPA**
- Handled API response formatting and error handling to improve robustness

---

## ✅ Testing & Deployment

- Verified successful database interaction through local testing
- Used **Postman** to test `GET`, `POST`, `PUT`, and `DELETE` requests for the budget endpoint
- Collaborated on troubleshooting backend issues related to DB connectivity and request payloads
