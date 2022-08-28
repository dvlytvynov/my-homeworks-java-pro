package input.output;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileNameChooser<T> {
    public String chooseFileName(String directory, T obj, String extension) {
        String prefix = obj.getClass().getSimpleName();
        File file = new File(directory);
        String[] fileArrayFromDirectory = file.list();
        List<String> list;
        if (fileArrayFromDirectory != null) {
            list = checkAndGetFileNameList(fileArrayFromDirectory, prefix, extension);
        } else {
            list = new ArrayList<>();
        }
        double number = 1;
        if (list.size() != 0) {
            number = 1 + Integer.parseInt(list.get(list.size() - 1));
        }
        return String.format("%s%04.0f%s", prefix, number, extension);
    }

    private List<String> checkAndGetFileNameList(
            String[] fileArrayFromDirectory, String prefix, String extension) {
        List<String> list = new ArrayList<>();
        for (String name : fileArrayFromDirectory) {
            if (name.startsWith(prefix) && name.endsWith(extension)) {
                char[] nameArray = name.toCharArray();
                String number = "";
                for (int i = prefix.length(); i < nameArray.length - extension.length(); i++) {
                    if (nameArray[i] >= 48 && nameArray[i] <= 59) {
                        String numberSymbol = String.valueOf(nameArray[i]);
                        number = number.concat(numberSymbol);
                    }
                }
                list.add(number);
            }
        }
        Collections.sort(list);
        return list;
    }

}
