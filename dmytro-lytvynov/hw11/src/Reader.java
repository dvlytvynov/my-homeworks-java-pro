import java.util.List;

public class Reader implements Observer {
    private String readerName;
    private List bookList;
    private Book newBook;

    public Reader(String readerName) {
        this.readerName = readerName;
    }

    @Override
    public void update(Book newBook, List bookList) {
        this.newBook = newBook;
        this.bookList = bookList;
        System.out.println(
                "Reader " + readerName + " has got information about new book \"" +
                        newBook.getBookName() + "\", " +
                        newBook.getBookAnnotation() +
                        ", it costs " + newBook.getBookPrice() + ".");
        System.out.print("Also reader " + readerName + " knows about other books: ");
        for (int i = 0; i < bookList.size(); i++) {
            System.out.print("\"" + ((Book) bookList.get(i)).getBookName() + "\"");
            if (i < bookList.size() - 1) {
                System.out.print(", ");
            } else {
                System.out.println(". ");
            }
        }
        System.out.println();
    }
}
