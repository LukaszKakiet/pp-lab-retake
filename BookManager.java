import java.util.ArrayList;
import java.util.List;

public class BookManager implements BookOperations {
    private List<Book> books;
    public BookManager() {
        books = new ArrayList<>();
        initBooks();
    }
    private void initBooks() {
        books.add(new Book("Tytuł A", "Autor A", "ISBN-A", 2010));
        books.add(new Book("Tytuł B", "Autor B", "ISBN-B", 2011));
        books.add(new Book("Tytuł C", "Autor C", "ISBN-C", 2012));
        books.add(new Book("Tytuł D", "Autor D", "ISBN-D", 2013));
        books.add(new Book("Tytuł E", "Autor E", "ISBN-E", 2014));
    }
    @Override
    public void addBook(Book book) {
        books.add(book);
    }
    @Override
    public void removeBook(Book book) {
        books.remove(book);
    }
    @Override
    public void updateBook(Book oldBook, Book newBook) {
        int index = books.indexOf(oldBook);
        if (index != -1) {
            books.set(index, newBook);
        }
    }
    @Override
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
}
