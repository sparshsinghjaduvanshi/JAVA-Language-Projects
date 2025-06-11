# 📄 File Copy Program - Java Project

A simple Java console application to copy the contents of one file into another.  
It supports copying to a **new file** or an **already existing file**, with validation and user-friendly prompts.

---

## 🛠️ Features

- Copy content from a **source file** to:
  - A **new file** (creates a new file with a unique name)
  - An **existing file** (overwrites the content)
- Validates if source and destination files exist.
- Handles file creation and IO exceptions gracefully.
- Interactive menu-based user interface.

---

## 🧠 Concepts Used

- Java File I/O (`BufferedReader`, `BufferedWriter`, `FileReader`, `FileWriter`)
- File existence checks with `File.exists()`
- User input handling with `Scanner`
- Modular code using multiple classes (`Menu`, `File_Creating_Writing`, `File_Validation`)
- Loop and conditional control for input validation

---

## 📁 File Structure


FileCopyProgram/
├── Copy\_Programe.java           # Main class to start the program
├── Menu.java                   # Handles user menu and input validation
├── File\_Creating\_Writing.java  # Handles file creation and copying logic
├── File\_Validation.java        # Utility class for file existence checks
└── README.md                   # This documentation file



---

## 🚀 How to Run

1. **Compile all Java files:**

```bash
javac *.java
````

2. **Run the program:**

```bash
java Copy_Programe
```

---

## 🖥️ Sample Interaction

```
What do you wish to do :-
enter 1 for copying into a new file
enter 2 for copying into already existed file.
1
Enter the source File name :-
source.txt
Enter the new File Name : -
newfile.txt
New file created successfully.........
Copying The File ..............
Copying The File Complete....................
```

---

## 📌 Notes

* The program requires the **source file** to exist.
* When copying to a **new file**, it will prompt until a unique filename is provided.
* When copying to an **existing file**, it will overwrite the content.
* Errors during file operations are printed as stack traces for debugging.

---

## 👨‍💻 Author

**Sparsh Singh**
B.Tech CSE, Central University of Rajasthan

---

```

If you want, I can also help you prepare a JAR or instructions for running this in IDEs like IntelliJ or Eclipse.
```
