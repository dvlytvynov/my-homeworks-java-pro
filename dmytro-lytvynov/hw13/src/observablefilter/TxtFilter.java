package observablefilter;

import java.util.List;

public class TxtFilter extends BaseFilter {

    @Override
    public List<String> getListFiles(String directory) {
        filesList.clear();
        super.extensions = new String[]{".txt"};
        super.checkDirectory(directory);
        return filesList;
    }
}
