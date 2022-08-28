package observablefilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BaseFilter implements Filter<String>, Observer<String> {
    protected String[] extensions;
    protected final List<String> filesList = new ArrayList<>();

    @Override
    public List<String> getListFiles(String directory) {
        filesList.clear();
        extensions = new String[]{""};
        checkDirectory(directory);
        return filesList;
    }

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

    @Override
    public void update(String directory) {
        getListFiles(directory);
        String ext = "";
        for (String e : extensions) {
            ext = ext.concat(" ");
            ext = ext.concat(e);
        }
        System.out.printf("%nList files with filter \" %s  \" :%n", ext);
        for (String f : filesList) {
            System.out.println(f);
        }
        System.out.println("Target files: " + filesList.size());
    }
}
