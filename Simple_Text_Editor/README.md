# Text Editor Project

## Overview
This project is a simple text editor implemented in Java. It supports basic text editing operations such as displaying, appending, replacing, deleting lines, searching words, and replacing words within text files. The project consists of:

- A **console-based text editor** (`TextEditor` with `Editor` and `FileManager` classes)
- A **GUI-based text editor** (`TextEditorGUI`) implemented using Java Swing

---

## Features

### Console Version
- Load a text file into memory line-by-line.
- Display the contents of the file with line numbers.
- Append a new line at the end of the file content.
- Replace a specific line with new content.
- Delete a specific line from the file.
- Search for a word in the file, returning the line number if found.
- Replace all occurrences of a word in the file.
- Save the edited content back to the file.
- Exit the editor.

### GUI Version (Swing)
- Open and load a text file via a file chooser.
- Display and edit file content in a JTextArea.
- Save changes to a file.
- Append, replace, and delete lines via menu options with input dialogs.
- Search for a word and highlight its first occurrence in the text area.
- Replace all occurrences of a word.
- Exit the application gracefully.

---

## How to Run

### Console Version
1. Compile all `.java` files:
    \`\`\`bash
    javac Editor.java FileManager.java TextEditor.java
    \`\`\`
2. Run the text editor:
    \`\`\`bash
    java TextEditor
    \`\`\`
3. Follow the on-screen menu to open files and perform edits.

### GUI Version
1. Compile all `.java` files including `TextEditorGUI.java`:
    \`\`\`bash
    javac Editor.java FileManager.java TextEditorGUI.java
    \`\`\`
2. Run the GUI application:
    \`\`\`bash
    java TextEditorGUI
    \`\`\`
3. Use the menu bar to open, save, edit, search, and replace text.

---

## Project Structure

- **Editor.java**  
  Contains static methods for editing operations on the text content represented as a \`List<String>\`.

- **FileManager.java**  
  Handles file reading and writing operations.

- **TextEditor.java**  
  Console-based user interface to interact with files and perform editing commands.

- **TextEditorGUI.java**  
  Swing-based graphical user interface for the text editor with menus and dialogs.

---

## Dependencies
- Java SE Development Kit (JDK) 8 or higher
- No external libraries required

---

## Notes

- The editor reads and writes plain text files.
- Lines are indexed starting from 1 for user interaction.
- GUI highlights the searched word in the text area.
- Input validation is included for line numbers.
- Error handling for file operations is implemented.
- Saving files in GUI uses a file chooser dialog.

---

## Future Enhancements
- Add undo/redo functionality.
- Support for multiple files (tabs).
- More advanced search options (regex, case sensitivity).
- Syntax highlighting for programming languages.
- Find and replace with preview.
- Auto-save feature.

---

If you have any questions or want to contribute, feel free to reach out.
