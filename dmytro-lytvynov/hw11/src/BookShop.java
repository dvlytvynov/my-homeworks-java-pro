import java.util.ArrayList;
import java.util.List;

public class BookShop implements Observable {
    private final List<Observer> readersList = new ArrayList<>();
    private final List<Book> bookList = new ArrayList<>();
    private Book newBook;

    @Override
    public void subscribe(Observer observer) {
        readersList.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        readersList.remove(observer);
    }

    @Override
    public void sendNewBook() {
        for (Observer ob : readersList) {
            ob.update(newBook, bookList);
        }
    }

    public void getNewBook(String bookName, String bookPrice, String bookAnnotation) {
        newBook = new Book(bookName, bookPrice, bookAnnotation);
        bookList.add(newBook);
        sendNewBook();
    }
}
