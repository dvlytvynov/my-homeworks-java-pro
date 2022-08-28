package test;

import classloader.Base64FileClassLoader;
import classloader.BaseClassLoader;
import classloader.EncryptedFileClassLoader;
import classloader.FileClassLoader;
import data.base.creator.Encoder;
import figure.Figure;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public void test() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        char separator = File.separatorChar;
        String directory = "." + separator + "resources" + separator +
                "class.data.base";
        BaseClassLoader classLoader;

        classLoader = new FileClassLoader(directory + separator + "native");
        System.out.println("\n- - Test of class loading from file - -");
        testClassLoader(classLoader);

        classLoader = new Base64FileClassLoader(directory + separator + "base64");
        System.out.println("\n- - Test of Base64 class loading from file - -");
        testClassLoader(classLoader);

        Encoder encoder = new Encoder(36);
        classLoader = new EncryptedFileClassLoader(
                directory + separator + "encrypted", encoder);
        System.out.println("\n- - Test of Encrypted class loading from file - -");
        testClassLoader(classLoader);
    }

    private void testClassLoader(BaseClassLoader classLoader)
            throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> aClass = classLoader.loadClass("figure.Rectangle");
        Figure rectangle = (Figure) aClass
                .getDeclaredConstructor(double.class, double.class)
                .newInstance(20, 8);
        System.out.println(rectangle.calculate());

        Class<?> aClass1 = classLoader.loadClass("figure.RightTriangle");
        Figure triangle = (Figure) aClass1
                .getDeclaredConstructor(double.class, double.class)
                .newInstance(4, 3);
        System.out.println(triangle.calculate());
    }
}
