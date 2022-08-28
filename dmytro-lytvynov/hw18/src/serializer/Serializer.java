package serializer;

import exception.SerializationException;

public interface Serializer<T> {
    byte[] toByteArray(T obj) throws SerializationException;

    T fromByteArray(byte[] inputFromFile) throws SerializationException;
}
