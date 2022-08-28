package decorator;

import java.util.List;

public interface Filter<T> {
    List<T> getListFiles(T directory, List<T> inputList);
}