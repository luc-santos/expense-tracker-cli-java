# Expense Tracker CLI (Java)

A command-line expense tracker built in Java.

This application allows you to create, manage, and track expenses directly from the terminal, with local JSON persistence.

---

##  Features

- Add expenses (description, amount, category, date)
- Mark expenses as paid
- List all expenses
- List paid and pending expenses
- Filter by category
- Filter by date
- Show total expenses
- Show total by category
- Delete all paid expenses
- Persistent storage using JSON (Gson)

---

##  Tech Stack

- Java
- Gson (JSON serialization)
- CLI (Command Line Interface)

---

##  Project Structure****

```
expense-tracker/
├── src/
│ ├── Main.java
│ ├── Expense.java
│ ├── ExpenseService.java
│ ├── ExpenseRepository.java
│ └── LocalDateAdapter.java
├── lib/
│ └── gson-2.9.1.jar
├── expenses.json
```

##  How to Run

From the project root:

### 1. Compile

javac -cp "lib/gson-2.9.1.jar" src/*.java

### 2. Run

java -cp "src:lib/gson-2.9.1.jar" Main

### Example usage: 

```
=== EXPENSE CLI ===
1 - Add expense
2 - List all expenses
3 - List pending expenses
4 - List paid expenses
5 - Filter by category
6 - Filter by date
7 - Show total
8 - Show total by category
9 - Mark expense as paid
10 - Delete all paid expenses
0 - Exit
```


### JSON Storage Example:

```
[
  {
    "id": 1,
    "description": "Coffee",
    "amount": 12.5,
    "category": "Food",
    "date": "2026-05-02",
    "paid": false
  }
]
```

Learning Highlights

This project demonstrates:
<ol>
<li>Object-oriented design (Entity, Service, Repository)</li>
<li>JSON serialization/deserialization with Gson</li>
<li>Handling Java LocalDate with custom TypeAdapter</li>
<li>CLI interaction using Scanner</li>
<li>Basic error handling and validation</li>
</ol>
