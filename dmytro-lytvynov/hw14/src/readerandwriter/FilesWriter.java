package readerandwriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FilesWriter {
    private final String filePath;

    public FilesWriter(String filePath) {
        this.filePath = filePath;
    }

    public void write(List<Integer> data) throws IOException {
        FileOutputStream stream = new FileOutputStream(filePath);
            for (int d : data) {
                stream.write(d);
            }
        stream.close();
    }
}
