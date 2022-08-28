package serialization;

import exception.SerializationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Serializer<T> {
    private final String DIRECTORY;

    public Serializer(String directory) {
        DIRECTORY = directory;
        File file = new File(directory);
        boolean mkDir = file.mkdirs();
    }

    public String writeObject(T obj) throws SerializationException {
        String newFileName = chooseFileName(obj);
        String path = DIRECTORY + File.separatorChar + newFileName;
        File file = new File(path);
        try {
            boolean crFile = file.createNewFile();
        } catch (IOException e) {
            throw new SerializationException("an I/O error occurred while creating a file", e);
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException e) {
            throw new SerializationException("an I/O error occurred while saving a file", e);
        }
        return newFileName;
    }

    public T readObject(String fileName) throws SerializationException {
        String path = DIRECTORY + File.separatorChar + fileName;
        File file = new File(path);
        T savedObject = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            savedObject = (T) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new SerializationException("an I/O error occurred while reading a file", e);
        }
        return savedObject;
    }

    public String chooseFileName(T obj) {
        String extension = ".txt";
        String objectName = obj.getClass().getSimpleName();
        File file = new File(DIRECTORY);
        String[] fileArr = file.list();
        List<String> list = new ArrayList<>();
        if (fileArr != null) {
            for (String name : fileArr) {
                if (name.startsWith(objectName) && name.endsWith(extension)) {
                    char[] nameArr = name.toCharArray();
                    String number = "";
                    for (int i = objectName.length(); i < nameArr.length - extension.length(); i++) {
                        if (nameArr[i] >= 48 && nameArr[i] <= 59) {
                            String elem = String.valueOf(nameArr[i]);
                            number = number.concat(elem);
                        }
                    }
                    list.add(number);
                }
            }
        }
        list.sort(null);
        double number = 1;
        if (list.size() != 0) {
            number = 1 + Integer.parseInt(list.get(list.size() - 1));
        }
        return String.format("%s%04.0f%s", objectName, number, extension);
    }
}
