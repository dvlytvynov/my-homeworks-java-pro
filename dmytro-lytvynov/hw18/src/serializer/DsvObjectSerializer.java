package serializer;

import exception.SerializationException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class DsvObjectSerializer<T> implements Serializer<T> {
    private final String delimiter;
    private final String endOfLine;
    private final Map<String, Function<String, ?>> mapFieldType;
    private final Map<String, String> mapSymbolsToDsv;
    private final Map<String, String> mapSymbolsFromDsv;

    public DsvObjectSerializer(String delimiter) throws SerializationException {
        DsvConvertingRules rules = new DsvConvertingRules(delimiter);
        this.delimiter = delimiter;
        endOfLine = rules.getEndOfLine();
        mapFieldType = rules.getMapFieldType();
        mapSymbolsToDsv = rules.getMapSymbolsToDsv();
        mapSymbolsFromDsv = rules.getMapSymbolsFromDsv();
    }

    @Override
    public byte[] toByteArray(T obj) throws SerializationException {
        Class<T> clazz = (Class<T>) obj.getClass();
        StringBuilder table = new StringBuilder();
        table
                .append("class_modifiers").append(delimiter)
                .append("class_name").append(endOfLine)
                .append(clazz.getModifiers()).append(delimiter)
                .append(clazz.getName()).append(endOfLine)
                .append("field_modifiers").append(delimiter)
                .append("field_type").append(delimiter)
                .append("field_name").append(delimiter)
                .append("field_value").append(endOfLine);
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                table
                        .append(f.getModifiers()).append(delimiter)
                        .append(f.getType().getSimpleName()).append(delimiter)
                        .append(f.getName()).append(delimiter);
                String value;
                if (f.get(obj) == null) {
                    value = "null";
                } else {
                    value = f.get(obj).toString();
                }
                String convertedValue = convertValueToDsv(value);
                table.append(convertedValue).append(endOfLine);
            }
        } catch (IllegalAccessException e) {
            throw new SerializationException("error while getting data from object", e);
        }
        String result = table.toString();
        return result.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public T fromByteArray(byte[] inputFromFile) throws SerializationException {
        String stringFromFile = new String(inputFromFile, StandardCharsets.UTF_8);
        List<List<String>> lineList = getValuesFromDsvString(stringFromFile);
        int lineIndex = -1;
        List<String> tableHeadList = lineList.get(++lineIndex);
        List<String> tableBodyList = lineList.get(++lineIndex);
        T obj = constructNewObject(tableHeadList, tableBodyList);
        if (!isDataFromFileCorrect(obj, lineList)) {
            throw new SerializationException("data from file doesn't correspond to object");
        }
        tableHeadList = lineList.get(++lineIndex);
        int nameIndex = 0;
        int valueIndex = 0;
        for (int i = 0; i < tableHeadList.size(); i++) {
            String columnName = tableHeadList.get(i);
            if ("field_name".equals(columnName)) {
                nameIndex = i;
            }
            if ("field_value".equals(columnName)) {
                valueIndex = i;
            }
        }
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            int modifier = f.getModifiers();
            if (Modifier.isTransient(modifier)) {
                continue;
            }
            for (int i = ++lineIndex; i < lineList.size(); i++) {
                tableBodyList = lineList.get(i);
                String fieldNameFromObject = f.getName();
                String fieldNameFromFile = tableBodyList.get(nameIndex);
                if (fieldNameFromObject.equals(fieldNameFromFile)) {
                    setFields(obj, f, tableBodyList.get(valueIndex));
                }
            }
        }
        return obj;
    }

    private T constructNewObject(List<String> tableHeadList, List<String> tableBodyList)
            throws SerializationException {
        String className = "";
        for (int i = 0; i < tableBodyList.size(); i++) {
            if ("class_name".equals(tableHeadList.get(i))) {
                className = tableBodyList.get(i);
            }
        }
        Class<?> clazz;
        T obj;
        try {
            clazz = Class.forName(className);
            obj = (T) clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException |
                InstantiationException | IllegalAccessException |
                InvocationTargetException e) {
            throw new SerializationException(e);
        }
        return obj;
    }

    private String convertValueToDsv(String value) {
        for (String key : mapSymbolsToDsv.keySet()) {
            value = value.replace(key, mapSymbolsToDsv.get(key));
        }
        return value;
    }

    private List<List<String>> getValuesFromDsvString(String value) {
        List<List<String>> lineList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        String endOfFile = "    ";
        StringBuilder stringBuilder = new StringBuilder(value);
        stringBuilder.append(endOfFile);
        int index = 0;
        int savedIndex = 0;
        String subString;
        String symbol;
        String symbols;
        while (index < stringBuilder.length() - endOfFile.length()) {
            symbol = stringBuilder.substring(index, index + 1);
            symbols = stringBuilder.substring(index, index + 2);
            if (symbol.equals(delimiter)) {
                valueList.add(stringBuilder.substring(savedIndex, index));
                savedIndex = index + 1;
            }
            if (symbols.equals(endOfLine)) {
                valueList.add(stringBuilder.substring(savedIndex, index));
                lineList.add(valueList);
                valueList = new ArrayList<>();
                savedIndex = index + endOfLine.length();
                index = index - 1 + endOfLine.length();
            }
            if (symbol.equals("\"")) {
                subString = stringBuilder.substring(index, index + 4);
                for (String key : mapSymbolsFromDsv.keySet()) {
                    if (subString.startsWith(key)) {
                        stringBuilder.delete(index, index + key.length());
                        stringBuilder.insert(index, mapSymbolsFromDsv.get(key));
                        index = index - 1 + mapSymbolsFromDsv.get(key).length();
                    }
                }
            }
            index++;
        }
        return lineList;
    }

    private boolean isDataFromFileCorrect(T obj, List<List<String>> fileLineList)
            throws SerializationException {
        byte[] byteArr = toByteArray(obj);
        String string = new String(byteArr, StandardCharsets.UTF_8);
        List<List<String>> objectLineList = getValuesFromDsvString(string);
        if (fileLineList.size() != objectLineList.size()) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            List<String> objectValueList = objectLineList.get(i);
            List<String> fileValueList = fileLineList.get(i);
            for (int j = 0; j < objectValueList.size(); j++) {
                if (!objectValueList.get(j).equals(fileValueList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setFields(T obj, Field f, String value) throws SerializationException {
        f.setAccessible(true);
        String fieldType = f.getType().getSimpleName();
        try {
            f.set(obj, mapFieldType.get(fieldType).apply(value));
        } catch (IllegalAccessException e) {
            throw new SerializationException(
                    "error while setting values in object \"" +
                            obj.getClass().getSimpleName() + "\"", e);
        }
    }
}