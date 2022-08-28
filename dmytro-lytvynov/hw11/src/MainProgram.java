public class MainProgram {
    public static void main(String[] args) {
        System.out.println("----- Test pattern \"Observer\" -----");
        BookShop shop1 = new BookShop();
        Reader readerTom = new Reader("Tom");
        Reader readerJulia = new Reader("Julia");
        Reader readerSofia = new Reader("Sofia");
        shop1.subscribe(readerTom);
        shop1.subscribe(readerJulia);
        shop1.subscribe(readerSofia);
        shop1.getNewBook(
                "Treasure Island",
                "200uah",
                "Adventure novel by Scottish author Robert Louis Stevenson");
        shop1.getNewBook(
                "Alice in Wonderland",
                "300uah",
                "English children's novel by Lewis Carroll");
        shop1.getNewBook(
                "The Lord of the Rings",
                "500uah",
                "Epic high-fantasy novel by English author J. R. R. Tolkien");
    }
}
