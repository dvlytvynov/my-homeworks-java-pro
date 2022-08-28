package decorator;

import java.util.List;

public class TxtFilter extends BaseFilter {

    @Override
    public List<String> getListFiles(String directory, List<String> inputList) {
        super.extensions = new String[]{".txt"};
        super.checkDirectory(directory);
        inputList.addAll(filesList);
        return inputList;
    }
}
