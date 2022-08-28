package readerandwriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesReader {
    private final String filePath;

    public FilesReader(String filePath) {
        this.filePath = filePath;
    }

    public List<Integer> readFile() throws IOException {
        File file = new File(filePath);
        List<Integer> data = new ArrayList<>();
        FileInputStream stream = null;
        stream = new FileInputStream(filePath);
        while (true) {
            int value = stream.read();
            if (value == -1) break;
            data.add(value);
        }
        stream.close();
        return data;
    }
}
