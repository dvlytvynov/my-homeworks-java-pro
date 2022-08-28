package observablefilter;

import java.io.IOException;

public class MainObservableFilter {
    public static void main(String[] args) throws IOException {
        System.out.println("\n* * * * * Observable file filter * * * * *\n");
        String directory = "C:/TestFilter";
        FilesData data = new FilesData(directory);
        FilesCreator creator = new FilesCreator();
        creator.createFiles(data.DIRECTORIES, data.FILES);
        Folder folder = new Folder(directory);
        JpgFilter filterJpg = new JpgFilter();
        DocFilter filterDoc = new DocFilter();
        TxtFilter filterTxt = new TxtFilter();
        folder.subscribe(filterJpg);
        folder.subscribe(filterDoc);
        folder.subscribe(filterTxt);
        folder.addFile(directory, "photo1.jpg");
        folder.unsubscribe(filterTxt);
        folder.addFile(directory, "guide1.doc");
        folder.addFile(directory, "readme1.txt");
        folder.deleteFile(directory, "picture1.jpg");
        folder.deleteFile(directory, "document1.doc");
        folder.deleteFile(directory, "text2.txt");
    }
}
