package picturefilter;

import java.io.IOException;

public class MainTestFilter {
    public static void main(String[] args) throws IOException {
        System.out.println("\n* * * * * File filter of pictures * * * * *\n");
        FilesData data = new FilesData();
        FilesCreator creator = new FilesCreator();
        creator.createFiles(data.DIRECTORIES, data.FILES);

        String[] extensions = new String[]{
                ".jpg", ".jpeg", ".bmp", ".dib",
                ".tif", ".tiff", ".png", ".gif",
                ".raw", ".psd", ".jp2"
        };
        MyFilter filter = new MyFilter();
        String directory = data.DIRECTORIES[0];
        Object[] filterResult = filter.getListFiles(directory, extensions);
        System.out.printf("List of directory \"%s\" with filter:%n", directory);
        for (int i = 0; i < filterResult.length; i++) {
            System.out.println(filterResult[i]);
        }
        System.out.printf("Folders: %d  Files: %d  Pictures: %d%n",
                filter.getFoldersAmount(),
                filter.getFilesAmount(),
                filter.getPicturesAmount());
    }
}
