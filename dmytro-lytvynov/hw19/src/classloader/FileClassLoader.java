package classloader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileClassLoader extends BaseClassLoader {
    private final String classDataBasePath;

    public FileClassLoader(String classDataBasePath) {
        this.classDataBasePath = classDataBasePath;
    }

    @Override
    protected byte[] getByteClassArr(String className) throws ClassNotFoundException {
        return getBytesFromFile(className);
    }

    protected byte[] getBytesFromFile(String className) throws ClassNotFoundException {
        String[] nameArray = className.split("\\.");
        String simpleClassName;
        simpleClassName = nameArray[nameArray.length - 1];
        String classPath = classDataBasePath + File.separatorChar + simpleClassName + ".class";
        Path path = Path.of(classPath);
        byte[] bytes;
        if (Files.exists((path))) {
            try {
                bytes = Files.readAllBytes(path);
            } catch (IOException e) {
                throw new ClassNotFoundException(
                        "an error occurred while reading a file \"" + className + "\"", e);
            }
        } else {
            throw new ClassNotFoundException("file \"" + className + "\" isn't found");
        }
        return bytes;
    }
}
