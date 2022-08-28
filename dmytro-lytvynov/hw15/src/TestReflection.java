import reflection.apps.HashMapOutputFile;
import reflection.apps.ReflectionExceptions;
import reflection.apps.ReflectionMapUtils;

import java.io.File;
import java.util.Map;

public final class TestReflection {
    private TestReflection() {
    }

    public static <K, V> void putTest(Map<K, V> map, K[] keys, V[] values) throws ReflectionExceptions {
        if (keys.length <= values.length) {
            for (int i = 0; i < keys.length; i++) {
                ReflectionMapUtils.put(map, keys[i], values[i]);
            }
        } else {
            for (int i = 0; i < values.length; i++) {
                ReflectionMapUtils.put(map, keys[i], values[i]);
            }
        }
    }

    public static <K, V> String removeTest(Map<K, V> map, K[] keys) throws ReflectionExceptions {
        StringBuilder report = new StringBuilder("\n");
        for (K key : keys) {
            V oldValue = ReflectionMapUtils.remove(map, key);
            if (oldValue != null) {
                report.append(key)
                        .append(" was removed; old value = ")
                        .append(oldValue)
                        .append(";\n");
            } else {
                report.append(key).append(" wasn't removed;\n");
            }
        }
        return report.toString();
    }

    public static <K, V> void outputStreamTest(Map<K, V> map, String directory, String fileName)
            throws ReflectionExceptions {
        HashMapOutputFile mapToFile = new HashMapOutputFile();
        char sep = File.separatorChar;
        File file = new File("." + sep);
        String dirPath = file.getAbsolutePath() + sep + directory;
        String filePath = file.getAbsolutePath() + sep + directory + sep + fileName;
        (new File(dirPath)).mkdir();
        mapToFile.output(map, filePath);
    }

}
