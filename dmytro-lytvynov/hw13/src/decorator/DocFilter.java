package decorator;

import java.util.List;

public class DocFilter extends BaseFilter {

    @Override
    public List<String> getListFiles(String directory, List<String> inputList) {
        super.extensions = new String[]{".doc"};
        super.checkDirectory(directory);
        inputList.addAll(filesList);
        return inputList;
    }
}
