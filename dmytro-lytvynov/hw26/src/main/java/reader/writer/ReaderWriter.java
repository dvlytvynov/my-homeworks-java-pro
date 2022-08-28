package reader.writer;

public interface ReaderWriter<T> {
    T read(String path);

    void write(String path, T data);
}
