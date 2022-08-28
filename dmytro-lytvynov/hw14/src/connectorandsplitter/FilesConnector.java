package connectorandsplitter;

import readerandwriter.FilesReader;
import readerandwriter.FilesWriter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FilesConnector {
    public void connect(String directory, String[] fileNames, String outputFileName)
            throws IOException {
        List<Integer> listData = new ArrayList<>();
        for (int i = 0; i < fileNames.length; i++) {
            if (fileNames[i] != null) {
                listData.add(1);
                listData.add(13);
                listData.add(10);
                byte[] arrByte = fileNames[i].getBytes(StandardCharsets.UTF_8);
                for (int j = 0; j < arrByte.length; j++) {
                    listData.add((int) arrByte[j]);
                }
                FilesReader reader = new FilesReader(directory + File.separatorChar + fileNames[i]);
                listData.add(2);
                listData.add(13);
                listData.add(10);
                listData.addAll(reader.readFile());
                listData.add(3);
                listData.add(13);
                listData.add(10);
            }
        }
        String filePath = directory + File.separatorChar + outputFileName;
        File file = new File(filePath);
        file.createNewFile();
        FilesWriter writer = new FilesWriter(filePath);
        writer.write(listData);
    }
}
