import test.TestAnnotation;
import test.TestObjectSaver;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("\n- - - - - Homework 16 - Annotations - - - - -\n");
        System.out.println("________________________________________");
        System.out.println("Class \"Car\" test:\n");
        TestAnnotation.testClassCar();
        System.out.println("________________________________________");
        System.out.println("Annotations test:\n");
        TestAnnotation.testAnnotations();
        System.out.println("________________________________________");
        System.out.println("Save object and read object test:\n");
        TestObjectSaver.testFileSaver();
    }
}
