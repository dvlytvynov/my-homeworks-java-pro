package decorator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFilterDecorator {
    public static void main(String[] args) throws IOException {
        System.out.println("\n* * * * * File filter decorator * * * * *\n");
        FilesData data = new FilesData();
        FilesCreator creator = new FilesCreator();
        creator.createFiles(data.DIRECTORIES, data.FILES);

        Filter filter;
        List<String> filterResult = new ArrayList<>();
        String directory = data.DIRECTORIES[0];
        filter = new JpgFilter();
        filterResult = filter.getListFiles(directory, filterResult);
        filter = new DocFilter();
        filterResult = filter.getListFiles(directory, filterResult);
        filter = new TxtFilter();
        filterResult = filter.getListFiles(directory, filterResult);

        System.out.printf("List of directory \"%s\" with filter:%n", directory);
        for (int i = 0; i < filterResult.size(); i++) {
            System.out.println(filterResult.get(i));
        }
        System.out.printf("Target files: %d%n", filterResult.size());
    }
}
