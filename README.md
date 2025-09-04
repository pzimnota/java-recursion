# File Cabinet — Composite + Internal Iterator

A tiny, clean Java 17+ project that models a file cabinet using the Composite pattern and a simple internal iterator to traverse the structure without duplicating traversal logic.
---

## ✅ Features

- **Composite hierarchy**
  - `Folder` (interface)
  - `SimpleFolder` (leaf, Java record)
  - `CompositeFolder` (node, Java record with a list of `Folder`)
  - `MultiFolder` (interface exposing `getFolders()`)
- **Cabinet API**
  - `findFolderByName(String name): Optional<Folder>`
  - `findFoldersBySize(String size): List<Folder>` (trimmed, case-insensitive)
  - `count(): int`
- **Internal iterator**
  - One private traversal method powers search, filter, and count with short-circuiting where applicable.
- **Defensive copies & immutability**
  - Records + `List.copyOf(...)` to prevent external mutation.


## 🚀 Technologies Used

- Java 23 
- JUnit 5

## ⚙️ Setup

### Clone the repository

```bash
git clone https://github.com/pzimnota/java-recursion.git
cd java-recursion
```


## 🧪 Running Tests

To run tests:

```bash
mvn test
```

## 📌 Notes

Consider replacing String getSize with an enum:
```java
enum Size { SMALL, MEDIUM, LARGE }
```
---

Feel free to submit issues or suggestions via GitHub.
