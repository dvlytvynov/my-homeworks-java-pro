package decorator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseFilter implements Filter<String> {
    protected String[] extensions;
    protected final List<String> filesList = new ArrayList<>();

    @Override
    public abstract List<String> getListFiles(String directory, List<String> inputList);

    protected void checkDirectory(String dir) {
        File file = new File(dir);
        File[] fileListArray = file.listFiles();
        int i = 0;
        while (i < fileListArray.length) {
            if (fileListArray[i].isDirectory()) {
                checkDirectory(fileListArray[i].getAbsolutePath());
            }
            if (fileListArray[i].isFile()) {
                String fileName = fileListArray[i].getName();
                for (int j = 0; j < extensions.length; j++) {
                    if (fileName.toLowerCase().endsWith(extensions[j])) {
                        filesList.add(fileListArray[i].getAbsolutePath());
                    }
                }
            }
            i++;
        }
    }
}
