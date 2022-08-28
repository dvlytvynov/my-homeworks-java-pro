package test;

import cars.Car;
import metadata.Action;
import metadata.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class TestAnnotation {

    private TestAnnotation() {
    }

    public static void testClassCar() {
        Car whiteVolvo = new Car("White Volvo", "XC90 Recharge", 85.5);
        System.out.println(whiteVolvo.getCarInfo(8));
        whiteVolvo.startJourney(4, 250.5, 8.5);
        System.out.println(whiteVolvo.getCarInfo(10.5));
        System.out.println(whiteVolvo.finishJourney());
    }

    public static void testAnnotations() {
        Car whiteVolvo = new Car("White Volvo", "XC90 Recharge", 85.5);
        System.out.println("\nInfo about Annotations in class Car:");
        Class<? extends Car> clazz = whiteVolvo.getClass();
        testFieldAnnotations(clazz);
        testMethodAnnotations(clazz);
    }

    private static void testFieldAnnotations(Class<?> clazz) {
        System.out.println("\nFields:");
        Field[] fields = clazz.getDeclaredFields();
        int fieldLength = 18;
        for (Field field : fields) {
            Class<Data> dataAnn = Data.class;
            StringBuilder annInfo = new StringBuilder("field " + field.getName());
            annInfo.append(" ".repeat(fieldLength - field.getName().length()));
            if (field.isAnnotationPresent(dataAnn)) {
                annInfo.append("  has \"")
                        .append(dataAnn.getSimpleName())
                        .append("\" annotation: ")
                        .append("value = ")
                        .append(field.getDeclaredAnnotation(dataAnn).value());
            } else {
                annInfo.append("  doesn't have \"")
                        .append(dataAnn.getSimpleName())
                        .append("\" annotation");
            }
            System.out.println(annInfo);
        }
    }

    private static void testMethodAnnotations(Class<?> clazz) {
        System.out.println("\nMethods:");
        Method[] methods = clazz.getDeclaredMethods();
        int methodLength = 13;
        for (Method method : methods) {
            Class<Action> actionAnn = Action.class;
            StringBuilder annInfo = new StringBuilder("field " + method.getName());
            annInfo.append(" ".repeat(methodLength - method.getName().length()));
            if (method.isAnnotationPresent(actionAnn)) {
                annInfo.append("  has \"")
                        .append(actionAnn.getSimpleName())
                        .append("\" annotation: ")
                        .append("number = ")
                        .append(method.getDeclaredAnnotation(actionAnn).number())
                        .append(", value = ")
                        .append(method.getDeclaredAnnotation(actionAnn).value())
                        .append(",\n")
                        .append("  ".repeat(23))
                        .append("purpose = ")
                        .append(method.getDeclaredAnnotation(actionAnn).purpose());
            } else {
                annInfo.append("  doesn't have \"")
                        .append(actionAnn.getSimpleName())
                        .append("\" annotation");
            }
            System.out.println(annInfo);
        }
    }
}
