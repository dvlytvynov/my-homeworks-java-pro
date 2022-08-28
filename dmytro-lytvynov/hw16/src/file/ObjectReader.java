package file;

import metadata.Data;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ObjectReader {
    public <T> T readFromFile(String filePath) throws IOException {
        List<String> list = Files.readAllLines(Path.of(filePath), StandardCharsets.UTF_8);
        T savedClass = constructSavedClass(list);
        boolean fieldFlag = false;
        String annotationValue = "";
        String fieldValue = "";
        for (int i = 0; i < list.size(); i++) {
            String[] blocks = list.get(i).split(",");
            for (int k = 0; k < blocks.length; k++) {
                String[] equality = blocks[k].split("=");
                if (equality[0].equals(";")) {
                    fieldFlag = false;
                }
                if (equality[0].equals("Fields:")) {
                    fieldFlag = true;
                }
                if (fieldFlag) {
                    if (equality[0].equals("value")) {
                        annotationValue = equality[1];
                    }
                    if (equality[0].equals("fieldValue")) {
                        fieldValue = equality[1];
                    }
                }
            }
            savedClass = setField(savedClass, annotationValue, fieldValue);
        }
        return savedClass;
    }

    private <T> T constructSavedClass(List<String> list) {
        String[] line = list.get(0).split("=");
        String className;
        if (line[0].equals("ClassName")) {
            className = line[1];
        } else {
            throw new IllegalArgumentException("Incorrect file format");
        }
        T savedClass = null;
        Class<T> clazz = null;
        try {
            clazz = (Class<T>) Class.forName(className);
            savedClass = clazz.getConstructor(String.class, String.class, double.class)
                    .newInstance("", "", 0);
        } catch (ClassNotFoundException | NoSuchMethodException |
                IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return savedClass;
    }

    private <T> T setField(T savedClass, String annotationValue, String fieldValue) {
        Field[] fields = savedClass.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Data.class)) {
                if (field.getDeclaredAnnotation(Data.class).value().equals(annotationValue)) {
                    String type = field.getType().getSimpleName();
                    try {
                        if (type.equals("double")) {
                            double value = Double.parseDouble(fieldValue);
                            field.set(savedClass, value);
                        }
                        if (type.equals("int")) {
                            int value = Integer.parseInt(fieldValue);
                            field.set(savedClass, value);
                        }
                        if (type.equals("String")) {
                            field.set(savedClass, fieldValue);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return savedClass;
    }
}
