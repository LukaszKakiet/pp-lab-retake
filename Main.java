import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static BookManager bookManager = new BookManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    removeBook(scanner);
                    break;
                case 3:
                    updateBook(scanner);
                    break;
                case 4:
                    listBooks();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }
    private static void showMenu() {
        System.out.println("Menu:");
        System.out.println("[1] Add book");
        System.out.println("[2] Remove book");
        System.out.println("[3] Update book");
        System.out.println("[4] List books");
        System.out.println("[5] Exit");
        System.out.print("Wybierz opcję: ");
    }
    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Wybierz opcję: ");
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    private static void addBook(Scanner scanner) {
        System.out.print("Podaj tytuł: ");
        String title = scanner.nextLine();
        System.out.print("Podaj autora: ");
        String author = scanner.nextLine();
        System.out.print("Podaj ISBN: ");
        String isbn = scanner.nextLine();

        int year = 0;
        boolean validYear = false;
        while (!validYear) {
            System.out.print("Podaj rok wydania: ");
            try {
                year = scanner.nextInt();
                validYear = true;
            } catch (InputMismatchException e) {
                System.out.println("Nieprawidłowy rok. Spróbuj ponownie.");
                scanner.next();
            }
        }
        scanner.nextLine();

        Book newBook = new Book(title, author, isbn, year);
        bookManager.addBook(newBook);
        System.out.println("Książka dodana.");
    }
    private static void removeBook(Scanner scanner) {
        System.out.print("Podaj tytuł książki do usunięcia: ");
        String title = scanner.nextLine();

        Book bookToRemove = findBookByTitle(title);
        if (bookToRemove != null) {
            bookManager.removeBook(bookToRemove);
            System.out.println("Książka usunięta.");
        } else {
            System.out.println("Nie znaleziono książki o podanym tytule.");
        }
    }
    private static void updateBook(Scanner scanner) {
        System.out.print("Podaj tytuł książki do aktualizacji: ");
        String oldTitle = scanner.nextLine();
        Book oldBook = findBookByTitle(oldTitle);
        if (oldBook != null) {
            System.out.print("Podaj nowy tytuł: ");
            String newTitle = scanner.nextLine();
            System.out.print("Podaj nowego autora: ");
            String newAuthor = scanner.nextLine();
            System.out.print("Podaj nowe ISBN: ");
            String newIsbn = scanner.nextLine();

            int newYear = 0;
            boolean validYear = false;
            while (!validYear) {
                System.out.print("Podaj nowy rok wydania: ");
                try {
                    newYear = scanner.nextInt();
                    validYear = true;
                } catch (InputMismatchException e) {
                    System.out.println("Nieprawidłowy rok. Spróbuj ponownie.");
                    scanner.next();
                }
            }
            scanner.nextLine();
            Book newBook = new Book(newTitle, newAuthor, newIsbn, newYear);
            bookManager.updateBook(oldBook, newBook);
            System.out.println("Książka zaktualizowana.");
        } else {
            System.out.println("Nie znaleziono książki o podanym tytule.");
        }
    }
    private static void listBooks() {
        List<Book> books = bookManager.getBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }
    private static Book findBookByTitle(String title) {
        List<Book> books = bookManager.getBooks();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}
