```markdown
## 🏦 Bank Management System - Java Project

A console-based **Bank Management System** built in Java using Object-Oriented Programming principles and JSON file handling. This project simulates the core operations of a bank for both users and administrators.

---

## 🚀 Features

### 👤 User Functionalities
- 🔍 View account profile
- 💰 Deposit money
- 🧾 Withdraw money
- 🔄 Transfer funds to another account
- 📊 Check account balance

### 🛠 Admin Functionalities
- 📋 List all user accounts
- 🔎 Search for an account by account number
- ❌ Delete user accounts

---

## 💾 Data Storage

- Uses **JSON file handling** to store all account details persistently.
- Powered by the `org.json` library (`JSONObject`, `JSONArray`, `JSONException`).

---

## 📁 File Structure

```

BankManagementSystem/
├── src/
│   ├── Main.java
│   ├── Account.java
│   ├── Bank.java
│   ├── Admin.java
│   ├── User.java
│   └── JSONUtil.java
├── accounts.json            # JSON file that stores account data
└── README.md

````

---

## 🧠 Technologies Used

- **Java** (OOP Concepts)
- **JSON** for file-based data persistence
- **File I/O**
- (Planned) **Java Swing** for GUI

---

## 📦 Requirements

- Java JDK 8 or above
- `org.json` library

You can add the JSON library using Maven, Gradle, or manually:
```bash
# Manual approach:
Download `json-20210307.jar` and add it to your classpath
````

---

## 🧪 How to Run

1. Compile the program:

```bash
javac -cp .;json-20210307.jar src/*.java
```

2. Run the main class:

```bash
java -cp .;json-20210307.jar src.Main
```

> Replace `;` with `:` on Linux/macOS.

---

## 📝 Planned Improvements

* GUI with **Java Swing**
* Account **login/authentication system**
* Transaction **history tracking**
* Interest and loan calculations
* Migration to **SQL database** for better data handling

---

## 👨‍💻 Author

**Sparsh Singh**
B.Tech CSE, Central University of Rajasthan

Let me know if you want this customized further — e.g., adding screenshots, linking to GitHub, or changing how commands work depending on your IDE (IntelliJ, VS Code, etc.).
```
