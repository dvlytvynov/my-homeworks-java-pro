package homework.multimodule.project;

public interface Creator<T> {
    T create(int number, String[] data);
}
