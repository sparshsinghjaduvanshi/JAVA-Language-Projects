# File Copy Program

---

## Overview

This Java console application allows users to copy content from one file to another. It supports:

- Copying the contents of a source file into a **newly created file**.
- Copying the contents of a source file into an **already existing file**.
- Validation of file existence before operations to prevent errors.

The program provides a simple interactive menu for users to select their desired operation.

---

## Features

- User-friendly menu-driven interface.
- File existence validation before reading or writing.
- Safe file creation with checks to avoid overwriting existing files.
- Buffered file reading and writing for efficient copying.
- Continuous prompts until valid inputs are received.

---

## Project Structure

- **Copy_Programe**: Contains the `main` method that starts the program.
- **Menu**: Handles user interactions and decision-making.
- **File_Validation**: Validates if a file exists on the disk.
- **File_Creating_Writing**: Handles creating new files and writing content from source to destination.

---

## How to Run

1. Compile all `.java` files.
2. Run the `Copy_Programe` class.
3. Follow the on-screen menu prompts to copy files.

---

## Usage Example

```bash
Welcome to the File Copy Program!

What do you wish to do:
1. Copy into a new file
2. Copy into an existing file

Enter your choice: 1
Enter the source file name: source.txt
Enter the new file name: newfile.txt

Copying the file...
Copy completed successfully.
