package decorator;

import java.util.List;

public class JpgFilter extends BaseFilter {

    @Override
    public List<String> getListFiles(String directory, List<String> inputList) {
        super.extensions = new String[]{".jpg", ".jpeg"};
        super.checkDirectory(directory);
        inputList.addAll(filesList);
        return inputList;
    }
}
