package homework.multimodule.project;

public interface ReaderWriter<T> {
    T read(String path);

    void write(String path, T data);
}
