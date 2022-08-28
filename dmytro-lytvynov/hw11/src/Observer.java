import java.util.List;

public interface Observer {
    void update(Book newBook, List bookList);
}
