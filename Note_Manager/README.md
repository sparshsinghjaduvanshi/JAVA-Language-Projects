# 📝 Note Manager - Java Project

A simple console-based **Note Manager** application in Java that allows users to add, view, and delete notes using file handling (`BufferedWriter`, `BufferedReader`, and `File`).

---

## 📌 Features

- 🖊 **Add a Note:** Input and save notes to a text file (`Notes.txt`).
- 📖 **Read Notes:** Display all saved notes with line numbers.
- ❌ **Delete Notes:** Remove all notes by deleting the file.

---

## 🧠 Concepts Used

- Java I/O (`java.io.*`)
- File handling:
  - `BufferedWriter` / `FileWriter` (append mode)
  - `BufferedReader` / `FileReader`
  - `File` for existence and deletion
- Control Flow (`switch`, `do-while`)
- Exception handling (`try-with-resources` and `IOException`)
- `Scanner` for user input

---

## 📁 File Structure



NoteManager/
├── Note\_Manager.java     # Main source code
├── Notes.txt             # File to store notes (auto-created)
└── README.md             # Documentation

````

---

## 🧪 How to Run

1. **Compile the code:**
```bash
javac Note_Manager.java
````

2. **Run the program:**

```bash
java Note_Manager
```

---

## 🖥️ Sample Output

```
..................WELCOME! to the Note Manager..............
1. Add a Note.
2. Read all Notes.
3. Delete all Notes.
4. Enter your choice
```

---

## 📌 Notes

* Notes are saved in a file called `Notes.txt`.
* Notes are **appended** to the file (not overwritten).
* File is created automatically if it doesn't exist.
* Use option 3 to delete all notes (by deleting the file).

---

## 👨‍💻 Author

**Sparsh Singh**
B.Tech CSE, Central University of Rajasthan

---

```

Let me know if you'd like a GUI version of this using Swing, or want to upgrade this to handle individual note deletion or timestamps.
```
