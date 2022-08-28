package homework.multimodule.project.application;

import homework.multimodule.project.Creator;

import java.util.ArrayList;
import java.util.List;

public class TableListCreator implements Creator<List<List<String>>> {

    @Override
    public List<List<String>> create(int columnQuantity, String[] data) {
        List<List<String>> list = new ArrayList<>();
        int i = 0;
        List<String> cellList = new ArrayList<>();
        for (String str : data) {
            cellList.add(str);
            i++;
            if (i >= columnQuantity) {
                i = 0;
                list.add(cellList);
                cellList = new ArrayList<>();
            }
        }
        return list;
    }
}