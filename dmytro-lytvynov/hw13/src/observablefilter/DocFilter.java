package observablefilter;

import java.util.List;

public class DocFilter extends BaseFilter {

    @Override
    public List<String> getListFiles(String directory) {
        filesList.clear();
        super.extensions = new String[]{".doc"};
        super.checkDirectory(directory);
        return filesList;
    }
}
