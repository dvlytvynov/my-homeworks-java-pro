package homework.maven.table.transformer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {
    public List<List<String>> read(String filePath) {
        List<List<String>> rowList = new ArrayList<>();
        FileInputStream inputStream = null;
        CSVParser csvParser = null;
        File file = new File(filePath);
        System.out.println("Reading table data from CSV-file: " + file.getAbsolutePath());
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream != null) {
            InputStreamReader stream = new InputStreamReader(inputStream);
            try {
                csvParser = CSVFormat.DEFAULT.parse(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (csvParser != null) {
            for (CSVRecord record : csvParser) {
                List<String> cellList = new ArrayList<>();
                for (int i = 0; i < record.size(); i++) {
                    cellList.add(record.get(i));
                }
                rowList.add(cellList);
            }
        }
        return rowList;
    }
}
