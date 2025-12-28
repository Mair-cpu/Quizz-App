# 🎓 Multi-Platform Quiz Ecosystem (Admin & User)

[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![Backend](https://img.shields.io/badge/Backend-SQLite%20%2F%20MySQL-blue.svg)](https://www.mysql.com/)

A comprehensive dual-application system designed for real-time quiz generation, cloud synchronization, and AI-driven student assistance.

---

## 🛠 Project Evolution & Technical Struggles

### 1. Firebase Integration (The Initial Attempt)
Originally, the project was built using **Google Firebase**. 
* **The Issue:** During testing, we encountered significant **Storage & Pricing constraints**. Managing complex relational data (like multiple options and correct answers for hundreds of quizzes) lead to high document reads and storage overhead.
* **The Decision:** We decided to pivot to a custom SQL architecture for better control over data structure and costs.

### 2. InfinityFree SQL Transition
We shifted the backend to **MySQL (hosted on InfinityFree)**.
* **The Challenge:** Since it is a web-based shared hosting, direct mobile-to-database connectivity was frequently blocked by firewalls or resulted in "Connection Timeout" errors.
* **The Solution:** We implemented a **PHP API Bridge** and a fallback **Offline-First SQLite** system.

---

## 📅 Development Timeline (Module Completion)

| Date | Milestone / Module Developed | Description |
| :--- | :--- | :--- |
| **Oct 15, 2025** | **Initial Research** | Project scope and Firebase integration setup. |
| **Oct 30, 2025** | **Admin UI & Logic** | Quiz creation, Title entry, and StringBuilder logic. |
| **Nov 10, 2025** | **Database Pivot** | Transition from Firebase to SQLite & MySQL (InfinityFree). |
| **Nov 25, 2025** | **QR Engine** | Implementation of ZXing library for offline data sharing. |
| **Dec 05, 2025** | **User Dashboard** | User app interface, Sidebar (Navigation Drawer) implementation. |
| **Dec 15, 2025** | **AI Chatbot** | Integration of AI assistant for student support. |
| **Dec 25, 2025** | **Final Polish** | Delete functionality, Error handling, and Final Testing. |

---

## 🚀 Key Features

### 🛠 Admin Application
- **Dynamic Quiz Creation:** StringBuilder-based question compiling.
- **SQLite Persistence:** Full CRUD (Create, Read, Update, Delete) locally.
- **QR Generation:** High-density QR for 100% offline data transfer.

### 📱 User Application
- **AI Chatbot Integration:** Real-time help for quiz queries.
- **Sidebar Navigation:** Professional UI for modular access.
- **QR & Cloud Sync:** Hybrid fetching from both local scans and Cloud APIs.



---

## ⚙️ How It Works (Architecture)
1.  **Admin** creates a quiz $\rightarrow$ Data is saved in **Local SQLite**.
2.  **Admin** generates a **QR Code** or attempts a **Cloud Push**.
3.  **User** scans the QR or fetches from **InfinityFree API**.
4.  **User App** parses the string and launches the **Interactive Quiz Engine**.

---

## 📄 License
This project is licensed under the MIT License - see the LICENSE file for details.
