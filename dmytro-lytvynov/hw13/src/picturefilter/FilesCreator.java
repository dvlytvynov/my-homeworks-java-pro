package picturefilter;

import java.io.File;
import java.io.IOException;

public class FilesCreator {
    public void createFiles(String[] directories, String[] files) throws IOException {
        for (int j = 0; j < directories.length; j++){
            File file = new File(directories[j]);
            file.mkdir();
            String fileName;
            for (int i = 0; i < files.length; i++) {
                fileName = directories[j] + "/" + files[i];
                file = new File(fileName);
                file.createNewFile();
            }
        }
    }
}
