# Note Manager

---

## Overview

Note Manager is a simple console-based Java application for managing personal notes. It allows users to add notes, read all saved notes, and delete all notes. Notes are stored in a plain text file (`Notes.txt`) in the local directory.

---

## Features

- **Add a Note:** Append a new note to the existing notes file.
- **Read All Notes:** Display all saved notes with numbering.
- **Delete All Notes:** Delete the entire notes file to clear all notes.
- **Simple console interaction** through a menu-driven interface.

---

## How It Works

- Notes are saved persistently in `Notes.txt` file located in the same directory as the program.
- When adding a note, the note is appended to the file.
- Reading notes lists all lines from the file with an index.
- Deleting notes removes the `Notes.txt` file entirely.
- Input is taken from the user via the console menu.

---

## How to Run

1. **Compile the program:**
   ```bash
   javac Note_Manager.java
2. **Run the Programe:**
    java Note_Manager

3. Follow the menu prompts to add, read, or delete notes.
