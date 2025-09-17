/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.library;
import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class Library {


class Item {
    private String title;
    private String author;
    private boolean isAvailable;

    public Item(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Default: available
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void displayInfo() {
        System.out.println("Title: " + title + ", Author: " + author +
                           ", Available: " + (isAvailable ? "Yes" : "No"));
    }
}

class Book extends Item {
    private String genre;

    public Book(String title, String author, String genre) {
        super(title, author); // Call parent constructor
        this.genre = genre;
    }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Genre: " + genre);
    }
}

public class LibraryApp {
    private static Scanner scanner = new Scanner(System.in);

    // Array to store books
    private static Book[] library = new Book[5];
    private static int bookCount = 0;

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addBook() {
        if (bookCount >= library.length) {
            System.out.println("Library is full. Cannot add more books.");
            return;
        }

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();

        library[bookCount] = new Book(title, author, genre);
        bookCount++;
        System.out.println("Book added successfully!");
    }

    private static void viewBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the library.");
            return;
        }

        System.out.println("\n--- Book List ---");
        for (int i = 0; i < bookCount; i++) {
            System.out.print((i + 1) + ". ");
            library[i].displayInfo();
        }
    }

    private static void borrowBook() {
        viewBooks();
        if (bookCount == 0) return;

        System.out.print("Enter the book number to borrow: ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > bookCount) {
            System.out.println("Invalid book number.");
            return;
        }

        Book selected = library[num - 1];
        if (selected.isAvailable()) {
            selected.setAvailable(false);
            System.out.println("You borrowed: " + selected.getTitle());
        } else {
            System.out.println("Sorry, that book is already borrowed.");
        }
    }

    private static void returnBook() {
        viewBooks();
        if (bookCount == 0) return;

        System.out.print("Enter the book number to return: ");
        int num = scanner.nextInt();
        scanner.nextLine();

        if (num < 1 || num > bookCount) {
            System.out.println("Invalid book number.");
            return;
        }

        Book selected = library[num - 1];
        if (!selected.isAvailable()) {
            selected.setAvailable(true);
            System.out.println("You returned: " + selected.getTitle());
        } else {
            System.out.println("That book was not borrowed.");
        }
    }
}

}
