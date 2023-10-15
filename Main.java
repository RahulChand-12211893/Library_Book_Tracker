import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Book {
    //Private so that Other classes cannot directly access or modify it.
    private String title;
    private String author;
    private boolean available;
    private int stockCount;
    public Book(String title, String author, int stockCount) {
        this.title = title;
        this.author = author;
        this.available = true;
        this.stockCount = stockCount;
        //true indicates that the book is available for checkout by default.
    }
    //can use private also but other class will not be able to access them
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    public int getstockCount(){
        return stockCount;
    }
    public void checkout(){
        if (stockCount>0){
            stockCount--;
        }
    }

    public boolean isAvailable() {
        return available;
    }

    public void returnBook() {
        available = true;
        stockCount++;
    }
    // to-string is used to creates a string representation of the Book object.
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Available: " + (available ? "Yes" : "No")+ ", stockCount:" + stockCount;
    }
}

class Library {
    private ArrayList<Book> books;
    private HashMap<String, Book> bookMap;

    public Library() {
        books = new ArrayList<>();
        bookMap = new HashMap<>();
    }

    public void addBook(String title, String author, int stockCount) {
        Book newBook = new Book(title, author, stockCount);
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
    public void removeBook(String title) {
        Book book = bookMap.get(title.toLowerCase());
        if (book != null) {
            books.remove(book);
            bookMap.remove(title.toLowerCase());
            System.out.println("Book removed: " + title);
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
        library.addBook("Mein Kemf", "Adolf Hitler",15);
        library.addBook("Utopia", " Sir Thomas Moor",50);
        library.addBook("Origin of Species", "Charles Darwin",10);
        library.addBook("Harry Potter and the Sorcerer's Stone", "J.K. Rowling",6);


        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. To Display all books");
            System.out.println("2. To Search for a book");
            System.out.println("3. To add some book");
            System.out.println("4. Checkout a book");
            System.out.println("5. Return a book");
            System.out.println("6. Exit");
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
                    System.out.print("Enter title of the book: ");
                    String addTitle = scanner.nextLine();
                    System.out.print("Enter author of the book: ");
                    String addAuthor = scanner.nextLine();
                    System.out.print("Enter stock count: ");
                    int addStockCount = scanner.nextInt();
                    library.addBook(addTitle, addAuthor, addStockCount);
                    System.out.println("Book added successfully.");
                    break;
                case 4:
                    System.out.print("Enter title of the book to checkout: ");
                    String checkoutTitle = scanner.nextLine();
                    library.checkoutBook(checkoutTitle);
                    break;
                case 5:
                    System.out.print("Enter title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;


                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
