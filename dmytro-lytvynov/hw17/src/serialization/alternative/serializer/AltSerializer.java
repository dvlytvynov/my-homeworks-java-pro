package serialization.alternative.serializer;

import exception.SerializationException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class AltSerializer<T> {
    private final String START;
    private final String END_SPLITTER;
    private final String MIDDLE_SPLITTER;

    public AltSerializer() {
        START = "!!class-backup!!";
        END_SPLITTER = ";";
        MIDDLE_SPLITTER = ",";
    }

    public String createDataFromReflection(T obj) throws SerializationException {
        Class<T> clazz = (Class<T>) obj.getClass();
        StringBuilder titleHead = new StringBuilder();
        StringBuilder bodyHead = new StringBuilder();
        StringBuilder titleData = new StringBuilder();
        StringBuilder bodyData = new StringBuilder();
        StringBuilder valueData = new StringBuilder();
        titleHead
                .append(START).append(END_SPLITTER)
                .append("modifiers").append(MIDDLE_SPLITTER)
                .append("class").append(END_SPLITTER);
        bodyHead
                .append(clazz.getModifiers()).append(MIDDLE_SPLITTER)
                .append(clazz.getName()).append(END_SPLITTER);
        titleData
                .append("modifiers").append(MIDDLE_SPLITTER)
                .append("type").append(MIDDLE_SPLITTER)
                .append("name").append(MIDDLE_SPLITTER)
                .append("value").append(END_SPLITTER);
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                bodyData
                        .append(f.getModifiers()).append(MIDDLE_SPLITTER)
                        .append(f.getType().getSimpleName()).append(MIDDLE_SPLITTER)
                        .append(f.getName()).append(END_SPLITTER);
                valueData
                        .append(f.getName()).append(MIDDLE_SPLITTER)
                        .append(f.get(obj)).append(END_SPLITTER);
            }
        } catch (IllegalAccessException e) {
            throw new SerializationException("an error occurred while getting data from object", e);
        }
        String result = titleHead
                .append(bodyHead)
                .append(titleData)
                .append(bodyData)
                .toString();
        byte[] arr = result.getBytes(StandardCharsets.UTF_8);
        String uuid = UUID.nameUUIDFromBytes(arr).toString();
        result = result.concat(valueData.toString());
        result = result.concat(uuid);
        return result;
    }

    public void renewObjectFields(T obj, String stateDataFromFile) throws SerializationException {
        String stateDataFromObject = createDataFromReflection(obj);
        String[] arrDataFromFile = stateDataFromFile.split(END_SPLITTER);
        String[] arrDataFromObject = stateDataFromObject.split(END_SPLITTER);
        if (!checkData(arrDataFromFile, arrDataFromObject)) {
            throw new SerializationException("input object from file does not match object \"" +
                    obj.getClass().getSimpleName() + "\"");
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        int minIndex = arrDataFromFile.length - 1 - fields.length;
        int maxIndex = arrDataFromFile.length - 1;
        for (Field f : fields) {
            int modifier = f.getModifiers();
            if (Modifier.isTransient(modifier)) {
                continue;
            }
            for (int i = minIndex; i < maxIndex; i++) {
                String[] values = arrDataFromFile[i].split(MIDDLE_SPLITTER);
                if (f.getName().equals(values[0])) {
                    setFields(obj, f, values);
                }
            }
        }
    }

    private boolean checkData(String[] arrDataFromFile, String[] arrDataFromObject)
            throws SerializationException {
        if (arrDataFromFile.length < 3) {
            throw new SerializationException("file data is not enough");
        }
        String uuidFromFile = arrDataFromFile[arrDataFromFile.length - 1];
        String uuidFromObject = arrDataFromObject[arrDataFromObject.length - 1];
        return uuidFromFile.equals(uuidFromObject);
    }

    private void setFields(T obj, Field f, String[] values) throws SerializationException {
        try {
            f.setAccessible(true);
            switch (f.getType().getSimpleName()) {
                case "String":
                    f.set(obj, values[1]);
                    break;
                case "byte":
                    f.set(obj, Byte.parseByte(values[1]));
                    break;
                case "short":
                    f.set(obj, Short.parseShort(values[1]));
                    break;
                case "char":
                    f.set(obj, values[1].toCharArray()[0]);
                    break;
                case "int":
                    f.set(obj, Integer.parseInt(values[1]));
                    break;
                case "long":
                    f.set(obj, Long.parseLong(values[1]));
                    break;
                case "boolean":
                    f.set(obj, Boolean.parseBoolean(values[1]));
                    break;
                case "float":
                    f.set(obj, Float.parseFloat(values[1]));
                    break;
                case "double":
                    f.set(obj, Double.parseDouble(values[1]));
                    break;
            }
        } catch (IllegalAccessException e) {
            throw new SerializationException(
                    "an error occurred while setting values in object \"" +
                            obj.getClass().getSimpleName() + "\"", e);
        }
    }
}