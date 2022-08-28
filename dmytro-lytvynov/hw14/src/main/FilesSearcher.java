package main;

import java.io.File;

public class FilesSearcher {
    public String[] find(String directory, String filesType) {
        File dir = new File(directory);
        String[] allFiles = dir.list();
        if (allFiles != null) {
            String[] targetFiles = new String[allFiles.length];
            int i = 0;
            for (String fileName : allFiles) {
                if (fileName.toLowerCase().endsWith(filesType)) {
                    targetFiles[i] = fileName;
                    i++;
                }
            }
            return targetFiles;
        } else return null;
    }
}
