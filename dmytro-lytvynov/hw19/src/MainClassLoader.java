import test.Test;

import java.lang.reflect.InvocationTargetException;

public class MainClassLoader {
    public static void main(String[] args)
            throws ClassNotFoundException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("- - - - Homework-19 Class Loader - - - -");
        Test test = new Test();
        test.test();
    }
}
