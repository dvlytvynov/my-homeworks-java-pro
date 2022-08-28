package input.output;

import exception.SerializationException;

public interface ReaderWriter<T> {
    String saveObject(String directory, T obj) throws SerializationException;

    T loadObject(String directory, String fileName) throws SerializationException;
}
