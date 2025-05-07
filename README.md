# FinTrack – Backend Submission (Spring Boot + JavaFX Integration)

This branch (`muhammad-backend`) includes the backend implementation for FinTrack, a personal finance tracking application. The backend is built using Spring Boot and integrates with a JavaFX frontend. This version includes complete API functionality and frontend connection.

---

## 🛠 Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot 3.x
- **Frontend:** JavaFX
- **Database:** MySQL (hosted on Microsoft Azure)
- **Build Tool:** Maven
- **IDE:** IntelliJ IDEA

---

## 📌 Branch Info

- This work was done in the `muhammad-backend` branch.
- A separate branch `test-william` was used to test teammate updates and later merged for compatibility.
-  I focused on building, testing, and connecting backend functionality.

---

## ✅ Backend Features

### 1. Authentication & User Management
- User registration and login endpoints (`/api/users/register`, `/api/users/login`)
- Password hashing using `BCryptPasswordEncoder`
- Custom error handling for login failures and duplicate emails
- Full CRUD for user accounts

### 2. Budget Module
- Created Budget model, controller, service, and repository
- Added CRUD operations for `/api/users/{userId}/budgets`
- Each budget linked to a user

### 3. Transactions Module
- Built Transaction entity with amount, type (EXPENSE/INCOME), description, and date
- Connected each transaction to user and category
- Implemented `/api/users/{userId}/transactions` with full CRUD

### 4. Saving Goals
- SavingGoal entity with goal name, target amount, and progress
- CRUD endpoints for `/api/users/{userId}/goals`
- Linked to user and tested with Postman

### 5. Security Configuration
- Setup `SecurityConfig.java` for Spring Security
- CSRF disabled for API testing
- Exposed only `/api/**` endpoints during development

---

## 🧪 API Testing

All endpoints were tested using Postman:
- Register / Login
- Budgets, Transactions, Saving Goals (CRUD)
- Confirmed working with expected response codes (200, 201, 401, 409)

---

## 🧪 API Testing (Postman)

Below is one of the  API test results showing successful backend operations:

###  Transaction & Saving Goal
![POST Transaction](https://github.com/user-attachments/assets/ad63a95f-fb66-42ca-91eb-7ddeb4661389)
![POST Saving Goal](https://github.com/user-attachments/assets/20ee589a-0912-48af-b3bf-c3db5795442f)
OST Saving Goal](https://github.com/user-attachments/assets/20ee589a-0912-48af-b3bf-c3db5795442f)


## 🔗 Frontend Integration

- JavaFX application now connects to backend endpoints:
    - Users can register and log in
    - View and update budgets
    - Add and manage transactions
    - Track saving goals
- All backend responses tested with frontend forms and views

---

## 📁 Structure Overview

├── models/
├── controllers/
├── services/
├── repositories/
├── config/
└── FinTrackApplication.java


---

## ✅ Final Notes

This backend includes:
- All required features (auth, budgets, transactions, goals)
- Full API testing
- Frontend integration
- Proper separation of concerns across layers (model, controller, service, repo)

Thanks to teammates for their work on database setup, ERD design, and UI implementation. This branch brings together everything needed for a complete and working version of FinTrack.
