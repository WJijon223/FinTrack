# FinTrack – Personal Finance Tracker

FinTrack is a JavaFX desktop application for managing personal finances. It helps users visualize expenses, manage budgets, and track financial activity with an intuitive and modern interface. This repository focuses on frontend development particularly UI/UX improvements and integration-readiness for backend services.

## Overview

This branch (`jason_merged`) is maintained by Jason and focuses on the following:
- Enhancing user experience through responsive layouts and theme support.
- Creating reusable components with clean scene transitions.
- Preparing the frontend for future backend integration.

---

## Features

- **Scene Navigation System** – Seamless screen transitions across the app.
- **Dark and Light Themes** –
- **Sidebar Navigation** – Used across all views for consistent app flow.
- – Subtle visual effects for a clean, elegant look.
- – Reusable controllers and FXML for maintainable UI design.

---

## Tech Stack

| Technology | Description |
|------------|-------------|
| JavaFX     | Main framework for building the UI |
| FXML       | XML-based layout definition |
| CSS        | For custom styling (dark/light modes) |
| SceneBuilder + IntelliJ | Visual & code-based development tools |

---

## Components Worked On

Developed and maintained the following key components:

### Controllers & Views
- `DashboardController` / `Dashboard.fxml`
- `BudgetController` / `Budget.fxml`
- `GraphController` / `Graph.fxml`
- `TransactionsController` / `Transactions.fxml`
- `SplashScreenController` / `SplashScreen.fxml`
- `SceneNavigatorController` – handles all screen switching logic
- `SidebarController` / `Sidebar.fxml` – used across all pages for navigation

### Styling
- `style.css` (Light Theme)
- `style_dark.css` (Dark Theme)
- Uses glassmorphism effects, hover interactions, and accessibility-friendly color schemes

### Report Generation Logic
- `com.fintrack.service.Report`
- `com.fintrack.service.Report_Dialog`
- `com.fintrack.service.ReportService`

---

## Assets

All image assets are organized in the `/images` folder. Key contributions include:

### Sidebar Icons
- `sidebar_home.png`
- `sidebar_budget.png`
- `sidebar_graph.png`
- `sidebar_profile.png`
- `sidebar_calendar.png`
- `sidebar_transactions.png`
- `sidebar_darkmode.png`
- `sidebar_lightmode.png`

### Category Icons
- `house_icon.png`
- `entertainment_icon.png`
- `restaurant_icon.png`
- `healthcare_icon.png`
- `transportation_icon.png`
- `utilities_icon.png`


## Installation & Run

> This is currently a frontend-only implementation.
