package observablefilter;

import java.util.List;

public class JpgFilter extends BaseFilter {

    @Override
    public List<String> getListFiles(String directory) {
        filesList.clear();
        super.extensions = new String[]{".jpg", ".jpeg"};
        super.checkDirectory(directory);
        return filesList;
    }
}
