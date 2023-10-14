import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void checkout() {
        available = false;
    }

    public void returnBook() {
        available = true;
    }

    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Available: " + (available ? "Yes" : "No");
    }
}

class Library {
    private ArrayList<Book> books;
    private HashMap<String, Book> bookMap;

    public Library() {
        books = new ArrayList<>();
        bookMap = new HashMap<>();
    }

    public void addBook(String title, String author) {
        Book newBook = new Book(title, author);
        books.add(newBook);
        bookMap.put(title.toLowerCase(), newBook);
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void searchBooks(String query) {
        if (bookMap.containsKey(query.toLowerCase())) {
            System.out.println(bookMap.get(query.toLowerCase()));
        } else {
            System.out.println("Book not found.");
        }
    }

    public void checkoutBook(String title) {
        Book book = bookMap.get(title.toLowerCase());
        if (book != null && book.isAvailable()) {
            book.checkout();
            System.out.println("You have checked out the book: " + book.getTitle());
        } else {
            System.out.println("Book not found or already checked out.");
        }
    }

    public void returnBook(String title) {
        Book book = bookMap.get(title.toLowerCase());
        if (book != null && !book.isAvailable()) {
            book.returnBook();
            System.out.println("You have returned the book: " + book.getTitle());
        } else {
            System.out.println("Book not found or already returned.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook("Book 1", "Author 1");
        library.addBook("Book 2", "Author 2");
        library.addBook("Book 3", "Author 3");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Display all books");
            System.out.println("2. Search for a book");
            System.out.println("3. Checkout a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter search query: ");
                    String query = scanner.nextLine();
                    library.searchBooks(query);
                    break;
                case 3:
                    System.out.print("Enter title of the book to checkout: ");
                    String checkoutTitle = scanner.nextLine();
                    library.checkoutBook(checkoutTitle);
                    break;
                case 4:
                    System.out.print("Enter title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
