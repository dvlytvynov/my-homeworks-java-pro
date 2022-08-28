package data.base.creator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class EncodedClassFileCreator {
    public static void main(String[] args) throws ClassNotFoundException {
        char separator = File.separatorChar;
        String nativeClassDirectory = "." + separator + "resources" +
                separator + "class.data.base" + separator + "native";
        String base64ClassDirectory = "." + separator + "resources" +
                separator + "class.data.base" + separator + "base64";
        String encryptedClassDirectory = "." + separator + "resources" +
                separator + "class.data.base" + separator + "encrypted";
        String fileName1 = "Rectangle.class";
        String fileName2 = "RightTriangle.class";

        Encoder encoder1 = new Encoder(36);
        byte[] bytes1 = readFile(nativeClassDirectory, fileName1);
        byte[] encodedBytes1 = encoder1.encode(bytes1);
        writeFile(encryptedClassDirectory, fileName1, encodedBytes1);
        byte[] base64Bytes1 = Base64.getEncoder().encode(bytes1);
        writeFile(base64ClassDirectory, fileName1, base64Bytes1);

        Encoder encoder2 = new Encoder(36);
        byte[] bytes2 = readFile(nativeClassDirectory, fileName2);
        byte[] encodedBytes2 = encoder2.encode(bytes2);
        writeFile(encryptedClassDirectory, fileName2, encodedBytes2);
        byte[] base64Bytes2 = Base64.getEncoder().encode(bytes2);
        writeFile(base64ClassDirectory, fileName2, base64Bytes2);
    }

    private static byte[] readFile(String directory, String fileName) throws ClassNotFoundException {
        String classPath = directory + File.separatorChar + fileName;
        Path path = Path.of(classPath);
        byte[] bytes;
        if (Files.exists((path))) {
            try {
                bytes = Files.readAllBytes(path);
            } catch (IOException e) {
                throw new ClassNotFoundException(
                        "an error occurred while reading a file \"" + fileName + "\"", e);
            }
        } else {
            throw new ClassNotFoundException("file \"" + fileName + "\" isn't found");
        }
        return bytes;
    }

    private static void writeFile(String directory, String fileName, byte[] bytes) {
        String classPath = directory + File.separatorChar + fileName;
        Path path = Path.of(classPath);
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
