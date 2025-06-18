# Java Book Management System

This is a simple Java console-based application that allows users to manage a list of books.  
Users can add, list, delete, and search for books. All data can be saved to or loaded from a text file (`bookSystem.txt`).

---

## Features

-  Add new books (title, author, ISBN)
-  List all books
-  Delete a selected book
-  Search for books by title, author, or ISBN
-  Save book list to a file on exit
-  Load saved book list (optional feature)

---

##  How It Works

When the program runs:

1. It opens a simple command-line menu
2. Users can interact by entering menu numbers
3. On exit, the book list is saved to `bookSystem.txt`
4. You can implement `loadData()` at startup to restore previous data

---

##  How to Compile and Run

```bash
javac BookSystem.java
java BookSystem
