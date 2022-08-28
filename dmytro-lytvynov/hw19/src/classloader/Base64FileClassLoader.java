package classloader;

import java.util.Base64;

public class Base64FileClassLoader extends FileClassLoader {

    public Base64FileClassLoader(String classDataBasePath) {
        super(classDataBasePath);
    }

    @Override
    protected byte[] getByteClassArr(String className) throws ClassNotFoundException {
        byte[] bytes = getBytesFromFile(className);
        return Base64.getDecoder().decode(bytes);
    }
}
