package input.output;

import exception.SerializationException;
import serializer.Serializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileReaderWriter<T> implements ReaderWriter<T> {
    private final String extension;
    private final Serializer<T> serializer;

    public FileReaderWriter(Serializer<T> serializer, String extension) {
        this.extension = extension;
        this.serializer = serializer;
    }

    @Override
    public String saveObject(String directory, T obj) throws SerializationException {
        File file = new File(directory);
        if (!file.exists()) {
            boolean mkdir = file.mkdirs();
        }
        FileNameChooser<T> nameChooser = new FileNameChooser<>();
        String fileName = nameChooser.chooseFileName(directory, obj, extension);
        file = new File(directory + File.separatorChar + fileName);
        try {
            boolean createFile = file.createNewFile();
        } catch (IOException e) {
            throw new SerializationException("error while creating a file", e);
        }
        byte[] byteArray = serializer.toByteArray(obj);
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file))) {
            stream.write(byteArray);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public T loadObject(String directory, String fileName) throws SerializationException {
        String path = directory + File.separatorChar + fileName;
        File file = new File(path);
        if (!file.exists()) {
            throw new SerializationException("file doesn't exist");
        }
        byte[] byteArray;
        try (BufferedInputStream stream = new BufferedInputStream(new FileInputStream(file))) {
            byteArray = stream.readAllBytes();
        } catch (IOException e) {
            throw new SerializationException("error while reading a file", e);
        }
        return serializer.fromByteArray(byteArray);
    }
}
