package reflection.apps;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

public class HashMapOutputFile {
    public <K, V> void output(Map<K, V> map, String filePath) throws ReflectionExceptions {
        if (map == null || ReflectionMapUtils.isEmpty(map)) {
            throw new ReflectionExceptions("HashMap is empty or undefined");
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
            final File file = new File(filePath);
            file.createNewFile();
            Set<K> hashMapSet = ReflectionMapUtils.keySet(map);
            for (K key : hashMapSet) {
                String entryData = key + " = " + ReflectionMapUtils.get(map, key) + ";\n";
                byte[] data = entryData.getBytes(StandardCharsets.UTF_8);
                outputStream.write(data);
            }
            outputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            throw new ReflectionExceptions("File is not found", e);
        } catch (IOException e) {
            throw new ReflectionExceptions(e);
        }
    }
}
