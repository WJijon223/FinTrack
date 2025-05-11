# FinTrack – Personal Finance Management App

**FinTrack** is a full-stack Java-based application that helps users manage their personal finances. It includes secure backend services, a modern JavaFX user interface, and integration with a MySQL database hosted on Azure.

---

## 🧩 Project Overview

This collaborative app combines backend functionality with a visually dynamic frontend:
- 🔐 Spring Boot-based APIs for authentication, budgets, transactions, and savings (Muhammad, William)
- 🎨 JavaFX desktop UI with custom themes and scene navigation (Dieunie, Jason)
- 🌐 Seamless frontend-backend integration using REST APIs
- 🧪 Thoroughly tested via Postman, GUI flows, and Azure-hosted MySQL

---

## 💡 Features

- **Secure User Registration & Login** (BCrypt password hashing)
- **Budget Management** (CRUD support)
- **Transaction Tracking** (income, expenses, categories)
- **Savings Goals** (target & progress tracking)
- **Light/Dark Themes** (toggle via JavaFX CSS)
- **Animated Navigation** (transitions, splash screen)
- **Azure MySQL Integration** (cloud-stable)

---

## 🛠️ Tech Stack

| Layer     | Technologies                         |
|-----------|--------------------------------------|
| Language  | Java 17 / Java 23                    |
| Backend   | Spring Boot 3.x, JPA (Hibernate)     |
| Frontend  | JavaFX, FXML                         |
| Database  | MySQL (Azure-hosted)                 |
| Security  | Spring Security + BCrypt             |
| Build     | Maven                                |
| IDE       | IntelliJ IDEA + Scene Builder        |
| Testing   | Postman, GUI forms                   |

---

## 📦 Project Structure

FinTrack/
├── backend/
│ ├── config/
│ ├── controllers/
│ ├── models/
│ ├── repositories/
│ ├── services/
│ └── FinTrackApplication.java
├── frontend/
│ ├── controllers/
│ ├── fxml/
│ ├── css/
│ ├── images/
│ └── Main.java


---

## 🚀 Setup Instructions

### 🧱 Backend Setup (Spring Boot)
1. Navigate to:  
   `backend/src/main/resources/application.properties`
2. Replace credentials with your MySQL database settings.
3. Run the backend:


```bash
mvn spring-boot:run
```
### 🎨 Frontend Setup (JavaFX)
1. Open `/frontend` as a module in IntelliJ.

2. Run Main.java in the com.fintrack package.

3. Ensure JavaFX SDK and VM options are configured.

## 🔐 API Endpoints

### 🧾 Authentication
- `POST /api/users/register` – Register a user  
- `POST /api/users/login` – Login

---

### 👤 Users
- `GET /api/users` – Get all users  
- `PUT /api/users/{id}` – Update a user  
- `DELETE /api/users/{id}` – Delete a user

---

### 💰 Budgets, Transactions, Saving Goals
- Full CRUD operations supported  
- See `/backend/controllers` for detailed route logic

## 🌈 Frontend Highlights
- FXML Screens: Login, Signup, Dashboard, Profile, Transactions, Forgot, Reset

- Controllers: Scene switching, input validation, animations

- Themes: Toggle light/dark mode with custom CSS

- Assets: Sidebar icons, charts, and styled fields

## 👥 Contributors & Roles

### 🔧 Muhammad A. Imran — Full Backend Development & Integration
- Built complete Spring Boot backend: **authentication**, **budgets**, **transactions**, and **saving goals**
- Connected and tested full-stack with JavaFX frontend
- Configured Spring Security and API error handling
- Finalized backend integration into `merge_frontend` branch
- ✅ Ensured full backend stability across all modules  


---

### 🎨 Dieunie Gousse — Lead Frontend Developer (JavaFX)
- Designed and implemented all **FXML UI screens**
- Created polished **dark/light themes**, splash screen, and branding
- Handled all **GUI controller logic**, scene transitions, and user navigation
- Focused on **UX, accessibility, and visual consistency** across app  


---

### 🧱 William Jijon — Backend Data & Security
- Created and hosted **MySQL schema** on Microsoft Azure
- Developed and tested `BudgetController` API endpoints
- Set up secure API routes and collaborated on backend data models  


---

### 🧩 Jason M. Maldonado — UI Enhancements & Scene Navigation
- Developed **scene switching system** and sidebar navigation
- Implemented **dashboard charts** and reusable UI components
- Worked on `jason_merged` to align frontend with backend-ready design  


## 📜 License
This project is licensed under the MIT License. You’re free to use and modify with credit.

📧 Contact
- William Jijon – williamjijon223@gmail.com
- Muhammad A. Imran – imrama@farmingdale.edu
- Jason M. Maldonado – martj16@farmingdale.edu
- Dieunie Gousse - gousdm@farmingdale.edu 

Project Repo – GitHub - FinTrack

Thank you for using FinTrack — the modern way to take control of your finances.
