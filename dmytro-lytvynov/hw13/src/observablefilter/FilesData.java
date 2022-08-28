package observablefilter;

public class FilesData {
    public final String[] DIRECTORIES;
    public final String[] FILES = new String[]{
            "picture1.jpg", "picture2.jpeg", "picture3.bmp", "picture4.dib",
            "picture5.tif", "picture6.tiff", "picture7.png", "picture8.gif",
            "picture9.raw", "picture10.psd", "picture11.jp2", "text1.txt",
            "text2.txt", "document1.doc", "document2.doc", "table1.xls",
            "table2.xls", "table3.xlsx"
    };

    public FilesData(String directory) {
        DIRECTORIES = new String[]{
                directory, directory + "/1000", directory + "/2000"
        };
    }
}
