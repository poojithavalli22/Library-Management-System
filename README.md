# 📚 Library Management System

A comprehensive Java-based solution designed to automate library operations, tracking book movements, and managing student transactions with precision.

## 🚀 Key Features

* **🔐 Secure Authentication** – Role-based access using unique Student Roll Numbers and encrypted-style password verification.
* **📖 Real-time Inventory** – Instant visibility into available titles ready for circulation.
* **📥 Smart Borrowing** – Automated checkout system with a built-in limit of 3 books per student.
* **📤 Streamlined Returns** – Simplified return processing to update book status and availability.
* **💰 Automated Fine Calculation** – Integrated penalty logic applying a **2 TK per day** fee for overdue items.
* **📅 Deadline Monitoring** – A dedicated "Upcoming Returns" dashboard for books due within the next 7 days.
* **🚪 Session Control** – Secure logout functionality to ensure data integrity.

---

## 📂 Project Architecture



The system is built using an Object-Oriented approach for modularity and scalability:

```text
Library_Management/
├── 📜 LibraryManagementSystem.java   # Main entry point and CLI controller
├── 📜 Library.java                   # Business logic and record management
├── 📜 Book.java                      # Book entity and status attributes
├── 📜 Student.java                   # User profile and borrowing history
├── 📜 OutPut.txt                     # Sample console output and logs
└── 📜 README.md                      # Project documentation


##
👨‍🎓 Student Class
Represents the library user and their current holdings.

Attributes: name, rollNumber, password, borrowedBooks.

Logic: Validates credentials and manages the student's personal loan list.

🏛 Library Class
The core engine that connects students with the inventory.

Methods: signUpStudent(), loginStudent(), displayAvailableBooks(), viewUpcomingBooks(), and transaction handlers for borrowing/returning.

🎯 Project Conclusion
The Library Management System serves as a robust framework for digitalizing campus library workflows. By automating late fee tracking and borrowing constraints, it reduces administrative overhead and improves the user experience.
