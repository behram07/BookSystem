import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;
    String isbn;

    Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("-------------------------");
    }
}

public class BookSystem {

    static ArrayList<Book> bookList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Book System ---");
            System.out.println("1. Add New Book");
            System.out.println("2. List Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Search Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                addBook();
            } else if (choice == 2) {
                listBooks();
            } else if (choice == 3) {
                deleteBook();
            } else if (choice == 4) {
                searchBook();
            } else if (choice == 5) {
                saveData();
                System.out.println("Exiting the program...");
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void addBook() {
        System.out.print("Book title: ");
        String title = scanner.nextLine();
        System.out.print("Author name: ");
        String author = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        Book newBook = new Book(title, author, isbn);
        bookList.add(newBook);
        System.out.println("Book successfully added: " + title);
    }

    public static void listBooks() {
        if (bookList.isEmpty()) {
            System.out.println("The book list is empty!");
        } else {
            System.out.println("\nBook List:");
            for (Book book : bookList) {
                book.displayInfo();
            }
        }
    }

    public static void deleteBook() {
        if (bookList.isEmpty()) {
            System.out.println("The book list is empty, no books to delete!");
            return;
        }

        System.out.println("\nSelect the book you want to delete:");
        listBooks();
        System.out.print("Enter the number of the book to delete: ");
        int number = scanner.nextInt();
        scanner.nextLine();

        if (number < 1 || number > bookList.size()) {
            System.out.println("Invalid number! Please try again.");
        } else {
            Book removedBook = bookList.remove(number - 1);
            System.out.println("Book deleted: " + removedBook.title);
        }
    }

    public static void searchBook() {
        if (bookList.isEmpty()) {
            System.out.println("The book list is empty, no books to search!");
            return;
        }

        System.out.print("Enter the keyword to search (title, author, or ISBN): ");
        String keyword = scanner.nextLine().toLowerCase();

        System.out.println("\nSearch Results:");
        boolean found = false;
        for (Book book : bookList) {
            if (book.title.toLowerCase().contains(keyword) ||
                    book.author.toLowerCase().contains(keyword) ||
                    book.isbn.toLowerCase().contains(keyword)) {
                book.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching book found.");
        }
    }

    public static void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bookSystem.txt"))) {
            for (Book book : bookList) {
                writer.write(book.title + "," + book.author + "," + book.isbn);
                writer.newLine();
            }
            System.out.println("Data has been saved to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving data: " + e.getMessage());
        }
    }

    public static void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("bookSystem.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 3) {
                    bookList.add(new Book(details[0], details[1], details[2]));
                }
            }
            System.out.println("Data has been loaded from the file.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. A new list will be created.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading data: " + e.getMessage());
        }
    }
}