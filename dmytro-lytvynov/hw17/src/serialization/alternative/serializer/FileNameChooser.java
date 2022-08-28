package serialization.alternative.serializer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileNameChooser<T> {
    public String chooseFileName(String directory, T obj) {
        String extension = ".txt";
        String prefix = obj.getClass().getSimpleName() + "_alt";
        File file = new File(directory);
        String[] fileArr = file.list();
        List<String> list = new ArrayList<>();
        if (fileArr != null) {
            for (String name : fileArr) {
                if (name.startsWith(prefix) && name.endsWith(extension)) {
                    char[] nameArr = name.toCharArray();
                    String number = "";
                    for (int i = prefix.length(); i < nameArr.length - extension.length(); i++) {
                        if (nameArr[i] >= 48 && nameArr[i] <= 59) {
                            String elem = String.valueOf(nameArr[i]);
                            number = number.concat(elem);
                        }
                    }
                    list.add(number);
                }
            }
        }
        list.sort(null);
        double number = 1;
        if (list.size() != 0) {
            number = 1 + Integer.parseInt(list.get(list.size() - 1));
        }
        return String.format("%s%04.0f%s", prefix, number, extension);
    }
}
