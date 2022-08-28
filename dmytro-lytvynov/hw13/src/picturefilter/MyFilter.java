package picturefilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyFilter {
    private String[] extensions;
    private final List<String> filesList = new ArrayList<>();
    private int filesAmount = 0;
    private int picturesAmount = 0;
    private int foldersAmount = 0;

    public int getFilesAmount() {
        return filesAmount;
    }

    public int getPicturesAmount() {
        return picturesAmount;
    }

    public int getFoldersAmount() {
        return foldersAmount;
    }

    public Object[] getListFiles(String directory, String[] extensions) {
        this.extensions = extensions;
        checkDirectory(directory);
        if (filesList.size() > 0) {
            return filesList.toArray();
        } else {
            return new String[]{"\nThis directory doesn't contain pictures"};
        }
    }

    private void checkDirectory(String dir) {
        File file = new File(dir);
        File[] fileListArray = file.listFiles();
        int i = 0;
        while (i < fileListArray.length) {
            if (fileListArray[i].isDirectory()) {
                foldersAmount++;
                checkDirectory(fileListArray[i].getAbsolutePath());
            }
            if (fileListArray[i].isFile()) {
                filesAmount++;
                String fileName = fileListArray[i].getName();
                for (int j = 0; j < extensions.length; j++) {
                    if (fileName.toLowerCase().endsWith(extensions[j])) {
                        picturesAmount++;
                        filesList.add(fileListArray[i].getAbsolutePath());
                    }
                }
            }
            i++;
        }
    }
}
