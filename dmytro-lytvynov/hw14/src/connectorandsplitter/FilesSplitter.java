package connectorandsplitter;

import readerandwriter.FilesReader;
import readerandwriter.FilesWriter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FilesSplitter {

    public void split(String directory, String outputFileName, String outputFolder) throws IOException {
        boolean name = false;
        boolean content = false;
        int i;
        final List<Integer> listName = new ArrayList<>();
        final List<Integer> listContent = new ArrayList<>();
        final List<Integer> data;

        File file = new File(directory + File.separatorChar + outputFolder);
        file.mkdir();
        FilesReader reader = new FilesReader(directory + File.separatorChar + outputFileName);
        data = reader.readFile();
        for (i = 0; i < data.size(); i++) {
            if (isNameStart(data, i)) {
                i += 2;
                name = true;
                continue;
            }
            if (isContentStart(data, i)) {
                i += 2;
                name = false;
                content = true;
                continue;
            }
            if (isContentEnd(data, i)) {
                i += 2;
                content = false;
                Object[] arrayName = listName.toArray();
                byte[] arrayNameByte = new byte[arrayName.length];
                for (int j = 0; j < arrayName.length; j++) {
                    arrayNameByte[j] = (byte) (int) arrayName[j];
                }
                String fileName = new String(arrayNameByte, StandardCharsets.UTF_8);
                String pathFile = directory + File.separatorChar +
                        outputFolder + File.separatorChar + fileName;
                FilesWriter writer = new FilesWriter(pathFile);
                File file1 = new File(pathFile);
                file1.createNewFile();
                writer.write(listContent);
                listName.clear();
                listContent.clear();
                continue;
            }
            if (name) {
                listName.add(data.get(i));
            }
            if (content) {
                listContent.add(data.get(i));
            }
        }
    }

    private boolean isNameStart(List<Integer> data, int i) {
        if (data.get(i) == 1) {
            if (data.get(i + 1) == 13) {
                if (data.get(i + 2) == 10) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isContentStart(List<Integer> data, int i) {
        if (data.get(i) == 2) {
            if (data.get(i + 1) == 13) {
                if (data.get(i + 2) == 10) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isContentEnd(List<Integer> data, int i) {
        if (data.get(i) == 3) {
            if (data.get(i + 1) == 13) {
                if (data.get(i + 2) == 10) {
                    return true;
                }
            }
        }
        return false;
    }
}
