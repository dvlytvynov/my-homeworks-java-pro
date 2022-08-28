package file;

import metadata.Action;
import metadata.Data;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

public class ObjectSaver {
    public <T> void saveToFile(T object, String outputFilePath) throws IOException {
        String data = "ClassName=" + object.getClass().getCanonicalName() + "\n;\n" +
                collectFieldInfo(object) + "\n" +
                collectMethodInfo(object);
        Files.writeString(Path.of(outputFilePath), data);
    }

    private <T> StringBuilder collectFieldInfo(T object) {
        Class<?> clazz = object.getClass();
        StringBuilder data;
        data = new StringBuilder("Fields:\n");
        Field[] fields = clazz.getDeclaredFields();
        Class<Data> dataAnn = Data.class;
        data.append("AnnotationName=")
                .append(dataAnn.getSimpleName())
                .append(":\n");
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(dataAnn)) {
                data.append("value=")
                        .append(field.getDeclaredAnnotation(dataAnn).value())
                        .append(",")
                        .append("fieldName=")
                        .append(field.getName())
                        .append(",");
                try {
                    data.append("fieldValue=")
                            .append(field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                data.append("null");
            }
            data.append("\n");
        }
        data.append(";");
        return data;
    }

    private <T> StringBuilder collectMethodInfo(T object) {
        Class<?> clazz = object.getClass();
        StringBuilder data;
        data = new StringBuilder("Methods:\n");
        Method[] methods = clazz.getDeclaredMethods();
        Class<Action> actionAnn = Action.class;
        data.append("AnnotationName=")
                .append(actionAnn.getSimpleName())
                .append(":\n");
        for (Method method : methods) {
            if (method.isAnnotationPresent(actionAnn)) {
                data.append("number=")
                        .append(method.getDeclaredAnnotation(actionAnn).number())
                        .append(",value=")
                        .append(method.getDeclaredAnnotation(actionAnn).value())
                        .append(",purpose=")
                        .append(method.getDeclaredAnnotation(actionAnn).purpose())
                        .append(",methodName=")
                        .append(method.getName());
            } else {
                data.append("null");
            }
            data.append("\n");
        }
        data.append(";");
        return data;
    }
}
