package classloader;

import data.base.creator.Encoder;

public class EncryptedFileClassLoader extends FileClassLoader {
    private final Encoder encoder;

    public EncryptedFileClassLoader(String classDataBasePath, Encoder encoder) {
        super(classDataBasePath);
        this.encoder = encoder;
    }

    @Override
    protected byte[] getByteClassArr(String className) throws ClassNotFoundException {
        byte[] bytes = getBytesFromFile(className);
        return encoder.decode(bytes);
    }
}
