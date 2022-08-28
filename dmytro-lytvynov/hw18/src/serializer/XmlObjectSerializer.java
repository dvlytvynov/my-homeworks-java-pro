package serializer;

import exception.SerializationException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class XmlObjectSerializer<T> implements Serializer<T> {
    private final Map<String, Function<String, ?>> mapFieldType;

    public XmlObjectSerializer() {
        mapFieldType = new HashMap<>();
        mapFieldType.putAll(Map.of(
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

    @Override
    public byte[] toByteArray(T obj) throws SerializationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new SerializationException(e);
        }
        DOMImplementation impl = builder.getDOMImplementation();
        Document doc = impl.createDocument(null, null, null);
        createDocument(obj, doc);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        } catch (TransformerConfigurationException e) {
            throw new SerializationException("error while transformation to xml", e);
        }
        DOMSource source = new DOMSource(doc);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(stream);
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            throw new SerializationException("error while transformation to xml", e);
        }
        return stream.toByteArray();
    }

    @Override
    public T fromByteArray(byte[] inputFromFile) throws SerializationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new SerializationException(e);
        }
        Document doc;
        ByteArrayInputStream stream = new ByteArrayInputStream(inputFromFile);
        try {
            doc = builder.parse(stream);
        } catch (SAXException | IOException e) {
            throw new SerializationException(e);
        }
        List<String> fileCheckList = new ArrayList<>();
        Map<String, String> fieldMap = new HashMap<>();
        getDataFromDocument(doc, fieldMap, fileCheckList);
        List<String> objectCheckList = new ArrayList<>();
        T obj = createObject(fieldMap, objectCheckList);
        if (!isFileDataCorrect(fileCheckList, objectCheckList)) {
            throw new SerializationException("data from file doesn't correspond to object");
        }
        return obj;
    }

    private void getDataFromDocument
            (Document doc, Map<String, String> fieldMap, List<String> fileCheckList) {
        String[] checkString = new String[3];
        NodeList elementList = doc.getElementsByTagName("*");
        for (int i = 0; i < elementList.getLength(); i++) {
            String fieldName = elementList.item(i).getNodeName();
            NamedNodeMap map = elementList.item(i).getAttributes();
            for (int j = 0; j < map.getLength(); j++) {
                String nodeName = map.item(j).getNodeName();
                String nodeValue = map.item(j).getNodeValue();
                switch (nodeName) {
                    case "name":
                        if ("class".equals(fieldName)) {
                            fieldMap.put(fieldName, nodeValue);
                            checkString[0] = fieldName;
                            checkString[2] = nodeValue;
                        }
                        break;
                    case "modifiers":
                        checkString[1] = nodeValue;
                        break;
                    case "type":
                        checkString[2] = nodeValue;
                        break;
                    case "value":
                        fieldMap.put(fieldName, nodeValue);
                        checkString[0] = fieldName;
                        break;
                }
            }
            String str = "";
            for (int k = 0; k < checkString.length; k++) {
                str = str.concat(checkString[k]);
                checkString[k] = "";
            }
            fileCheckList.add(str);
        }
    }

    private void createDocument(T obj, Document doc) throws SerializationException {
        Element root = doc.createElement("class");
        Class<T> clazz = (Class<T>) obj.getClass();
        root.setAttribute("name", clazz.getName());
        root.setAttribute("modifiers", String.valueOf(clazz.getModifiers()));
        doc.appendChild(root);
        Field[] fields = clazz.getDeclaredFields();
        Element[] elements = new Element[fields.length];
        try {
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                elements[i] = doc.createElement(fields[i].getName());
                elements[i].setAttribute("modifiers", String.valueOf(fields[i].getModifiers()));
                elements[i].setAttribute("type", fields[i].getType().getSimpleName());
                String value;
                if (fields[i].get(obj) == null) {
                    value = "null";
                } else {
                    value = fields[i].get(obj).toString();
                }
                elements[i].setAttribute("value", value);
                root.appendChild(elements[i]);
            }
        } catch (IllegalAccessException e) {
            throw new SerializationException("error while getting data from object", e);
        }
    }

    private T createObject(Map<String, String> fieldMap, List<String> objectCheckList)
            throws SerializationException {
        String className = fieldMap.get("class");
        if (className == null) {
            throw new SerializationException("class name doesn't exist in file");
        }
        Class<T> clazz;
        T obj;
        try {
            clazz = (Class<T>) Class.forName(className);
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException |
                InstantiationException | IllegalAccessException |
                InvocationTargetException e) {
            throw new SerializationException("error while creating an object", e);
        }
        objectCheckList.add("class" + obj.getClass().getModifiers() + obj.getClass().getName());
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            int modifier = field.getModifiers();
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();
            String fieldValue = fieldMap.get(fieldName);
            objectCheckList.add(fieldName + modifier + fieldType);
            if (Modifier.isTransient(modifier)) {
                continue;
            }
            field.setAccessible(true);
            try {
                field.set(obj, mapFieldType.get(fieldType).apply(fieldValue));
            } catch (IllegalAccessException e) {
                throw new SerializationException(
                        "error while setting values in object \"" +
                                obj.getClass().getSimpleName() + "\"", e);
            }
        }
        return obj;
    }

    private boolean isFileDataCorrect(List<String> fileCheckList, List<String> objectCheckList) {
        if (fileCheckList.size() != objectCheckList.size()) {
            return false;
        }
        for (int i = 0; i < fileCheckList.size(); i++) {
            String str1 = fileCheckList.get(i);
            String str2 = objectCheckList.get(i);
            if (!Objects.equals(str1, str2)) {
                return false;
            }
        }
        return true;
    }
}