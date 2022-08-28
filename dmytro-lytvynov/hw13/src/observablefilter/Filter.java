package observablefilter;

import java.util.List;

public interface Filter<T> {
    List<T> getListFiles(T directory);
}