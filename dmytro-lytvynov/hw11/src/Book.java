public class Book {
    private String bookName;
    private String bookPrice;
    private String bookAnnotation;

    public Book(String bookName, String bookPrice, String bookAnnotation) {
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookAnnotation = bookAnnotation;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public String getBookAnnotation() {
        return bookAnnotation;
    }
}
