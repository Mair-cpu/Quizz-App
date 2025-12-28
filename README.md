# 🎓 Multi-Platform Quiz Ecosystem  
## (Admin & User Android Applications)

---

## 📌 Project Overview

The **Multi-Platform Quiz Ecosystem** is a complete Android-based educational framework designed to facilitate digital assessments between **Teachers (Admins)** and **Students (Users)**.

This project enables quiz creation, quiz attempts, automated evaluation, result visualization, quiz history tracking, QR-based quiz sharing, and AI-driven chatbot assistance.  
The system is developed using **Java, Android Studio, SQLite, and Material Design principles**.

> This project is developed as an academic project with a strong focus on logic building, modular architecture, and real-world application design.

---

## 🎯 Project Objectives

- Provide a structured quiz management system
- Allow admins to create and distribute quizzes
- Enable users to attempt quizzes interactively
- Automatically calculate and display results
- Maintain quiz attempt history
- Share quizzes using QR codes
- Assist users using an AI-based chatbot
- Ensure clean UI and smooth UX

---

## 🧰 Technologies & Tools Used

| Technology | Purpose |
|----------|--------|
| Java | Core Android Logic |
| Android Studio | Development IDE |
| SQLite | Local Database Storage |
| RecyclerView | Dynamic Data Display |
| ZXing Library | QR Code Generation |
| Material Design | UI/UX Design |
| Rule-Based AI Logic | Chatbot Assistance |

---

## 📅 Development Timeline (Module-wise History)

> The project was developed step-by-step to ensure clarity, learning depth, and proper implementation of each component.

---

### 📍 Dec 14, 2025 — Project Initialization

**Work Done:**
- Project idea finalized
- Application name selection
- Logo design (`logo2.png`)
- Resource and folder structure planning

**Learning Outcome:**
- Android project setup
- Resource management and branding

---

### 📍 Dec 21, 2025 — Splash Screen & Base Navigation

**Work Done:**
- Splash screen implementation
- App launch delay logic
- Transition to Main Activity

**Logic Explanation:**
The splash screen provides a professional startup experience and allows background initialization.

```java
new Handler().postDelayed(() -> {
    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
    startActivity(intent);
    finish();
}, 3000);
```

### 📍 Dec 23, 2025 — SQLite Database Integration

Work Done:

DbHelper class creation

Quiz table implementation

History table implementation

Insert and fetch operations

Logic Explanation:
SQLite is used for persistent local storage of quizzes and quiz history.

```java
db.execSQL("CREATE TABLE quizzes (id INTEGER PRIMARY KEY, title TEXT, data TEXT)");
db.execSQL("CREATE TABLE history (id INTEGER PRIMARY KEY, quiz_name TEXT, score TEXT, date TEXT)");
```

### 📍 Dec 26, 2025 — Category & Adapter System

Work Done:

RecyclerView adapters

Category-based quiz listing

Dynamic data binding

Logic Explanation:
RecyclerView efficiently handles large datasets and improves UI performance.

### 📍 Dec 28, 2025 (11:46 AM) — Result Calculation Module

Work Done:

Quiz evaluation logic

Percentage calculation

Pass/Fail status

Circular progress visualization

Logic Explanation:
Results are calculated based on correct answers and displayed visually.

```java
int percentage = (correctCount * 100) / totalQuestions;
circularProgressBar.setProgress(percentage);

if (percentage >= 50) {
    resultStatus.setText("Passed");
} else {
    resultStatus.setText("Failed");
}
```

### 📍 Dec 28, 2025 (06:02 PM) — Quiz History Module

Work Done:

Attempt history storage

Date and score tracking

RecyclerView-based history display

Logic Explanation:
Each quiz attempt is stored in the database for future performance review.

```java
Cursor cursor = db.rawQuery("SELECT * FROM history ORDER BY id DESC", null);
while (cursor.moveToNext()) {
    historyList.add(
        new HistoryModel(
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
    );
}
adapter.notifyDataSetChanged();
```

### 📍 Dec 28, 2025 (07:35 PM) — AI Quiz Assistant (Chatbot)

Work Done:

Rule-based chatbot logic

Keyword detection

Automated responses

Logic Explanation:
The chatbot analyzes user input and responds based on predefined rules.

```java
private void botReply(String message) {
    String userMsg = message.toLowerCase();

    if (userMsg.contains("math")) {
        chatList.add(new ChatModel(
            "Math quizzes cover Algebra and Geometry.",
            BOT_TYPE
        ));
    } else if (userMsg.contains("score")) {
        chatList.add(new ChatModel(
            "You earn 1 mark for each correct response.",
            BOT_TYPE
        ));
    }
}
```

### 📍 Dec 28, 2025 (07:36 PM) — QR Code & Bulk Quiz Module

Work Done:

Bulk quiz data compilation

QR code generation

ZXing library integration

Logic Explanation:
Quiz data is encoded into a compact string and converted into a QR code.

```java
StringBuilder sb = new StringBuilder();
sb.append(title).append("!")
  .append(q1).append("|")
  .append(ans1);

MultiFormatWriter writer = new MultiFormatWriter();
BitMatrix matrix = writer.encode(
    sb.toString(),
    BarcodeFormat.QR_CODE,
    500,
    500
);
```

### 🔐 Admin Application Features

Quiz creation and management

Bulk quiz upload

QR code generation

Category management

Database storage

### 👨‍🎓 User Application Features

Quiz attempt system

Real-time result display

Attempt history tracking

AI chatbot assistance

QR-based quiz access

### 📚 Learning Outcomes

Android application architecture

SQLite database management

Logical problem solving

RecyclerView optimization

QR-based data encoding

Basic AI chatbot implementation

Clean UI/UX development

### 🏁 Conclusion

This project demonstrates a strong understanding of Android development, database integration, and logic-driven system design.
