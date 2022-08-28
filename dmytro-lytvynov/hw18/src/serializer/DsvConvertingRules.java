package serializer;

import exception.SerializationException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DsvConvertingRules {
    private final String delimiter;
    private final String endOfLine;

    public DsvConvertingRules(String delimiter) throws SerializationException {
        String[] delimiters = {",", "\t", ":", ";"};
        boolean isDelimiterExist = false;
        for (String str : delimiters) {
            if (str.equals(delimiter)) {
                isDelimiterExist = true;
                break;
            }
        }
        if (!isDelimiterExist) {
            throw new SerializationException("delimiter can be only \",\" or \":\" or \";\" or TAB");
        }
        this.delimiter = delimiter;
        this.endOfLine = "\r\n";
    }

    public String getEndOfLine() {
        return endOfLine;
    }

    public Map<String, Function<String, ?>> getMapFieldType() {
        return new HashMap<>(Map.of(
                "String", (v) -> v,
                "byte", Byte::parseByte,
                "short", Short::parseShort,
                "char", (v) -> v.toCharArray()[0],
                "int", Integer::parseInt,
                "long", Long::parseLong,
                "boolean", Boolean::parseBoolean,
                "float", Float::parseFloat,
                "double", Double::parseDouble));
    }

    public Map<String, String> getMapSymbolsToDsv() {
        final Map<String, String> mapSymbolsToDsv = new HashMap<>(Map.of(
                "\"", "\"\"",
                ",", "\",\"",
                ";", "\";\"",
                endOfLine, "\"" + endOfLine + "\""));
        if (!mapSymbolsToDsv.containsKey(delimiter)) {
            mapSymbolsToDsv.put(delimiter, "\"" + delimiter + "\"");
        }
        return mapSymbolsToDsv;
    }

    public Map<String, String> getMapSymbolsFromDsv() {
        final Map<String, String> mapSymbolsFromDsv = new HashMap<>(Map.of(
                "\"\"", "\"",
                "\",\"", ",",
                "\";\"", ";",
                "\"" + endOfLine + "\"", endOfLine));
        if (!mapSymbolsFromDsv.containsKey("\"" + delimiter + "\"")) {
            mapSymbolsFromDsv.put("\"" + delimiter + "\"", delimiter);
        }
        return mapSymbolsFromDsv;
    }
}
