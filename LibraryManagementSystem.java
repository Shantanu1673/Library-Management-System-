package librarymanagementsystem;

import java.util.*;

class Book {
    int id;
    String name, author, category;

    Book(int id, String name, String author, String category) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Author: " + author + ", Category: " + category;
    }
}

public class LibraryManagementSystem {
    static String password = "admin123"; // default password
    static ArrayList<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int bookIdCounter = 1;

    public static void main(String[] args) throws InterruptedException {
        System.out.print("Enter Password to access system: ");
        String inputPass = sc.nextLine();

        if (!inputPass.equals(password)) {
            System.out.println("Incorrect Password. Exiting...");
            return;
        }

        while (true) {
            System.out.println("\n==== Library Management Menu ====");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Search Book");
            System.out.println("4. View All Books");
            System.out.println("5. Change Password");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) 
            {
                case 1: addBook(); 
                break;
                case 2: deleteBook(); 
                break;
                case 3: searchBook(); break;
                case 4: viewBooks();
                break;
                case 5: changePassword(); 
                break;
                case 6:
                    System.out.println("Exiting... Please wait.");
                    Thread.sleep(3000);
                    System.exit(0);
                default: 
                	System.out.println("Invalid choice!");
            }
        }
    }

    static void addBook() 
    {
        System.out.println("Choose Category: 1. Fiction 2. Non-Fiction 3. Comics");
        int catChoice = sc.nextInt(); sc.nextLine();
        String category = switch (catChoice) {
            case 1 -> "Fiction";
            case 2 -> "Non-Fiction";
            case 3 -> "Comics";
            default -> "General";
        };

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        books.add(new Book(bookIdCounter++, name, author, category));
        System.out.println("Book added successfully.");
    }

    static void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        int id = sc.nextInt(); sc.nextLine();
        boolean removed = books.removeIf(b -> b.id == id);
        if (removed) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    static void searchBook() {
        System.out.print("Enter keyword to search (name/author): ");
        String keyword = sc.nextLine().toLowerCase();
        HashSet<Book> results = new HashSet<>();

        for (Book b : books) {
            if (b.name.toLowerCase().contains(keyword) || b.author.toLowerCase().contains(keyword)) {
                results.add(b);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No matching books found.");
        } else {
            System.out.println("Search Results:");
            for (Book b : results) {
                System.out.println(b);
            }
        }
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("All Books:");
            for (Book b : books) {
                System.out.println(b);
            }
        }
    }

    static void changePassword() {
        System.out.print("Enter old password: ");
        String oldPass = sc.nextLine();
        if (!oldPass.equals(password)) {
            System.out.println("Incorrect old password.");
            return;
        }
        System.out.print("Enter new password: ");
        password = sc.nextLine();
        System.out.println("Password changed successfully.");
    }
}
