package classloader;

public abstract class BaseClassLoader extends ClassLoader {
    public BaseClassLoader() {
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            byte[] classByteArr = getByteClassArr(className);
            return defineClass(className, classByteArr, 0, classByteArr.length);
        } catch (ClassFormatError | IndexOutOfBoundsException | SecurityException ex) {
            return super.findClass(className);
        }
    }

    protected abstract byte[] getByteClassArr(String className) throws ClassNotFoundException;
}
