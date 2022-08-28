package reader.writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderWriter implements ReaderWriter<List<List<String>>> {
    @Override
    public List<List<String>> read(String path) {
        List<List<String>> rowList = new ArrayList<>();
        FileInputStream inputStream = null;
        CSVParser csvParser = null;
        File file = new File(path);
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

    @Override
    public void write(String path, List<List<String>> data) {
        File file = new File(path);
        FileWriter writer = null;
        try {
            boolean isCreated = file.createNewFile();
            writer = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (writer != null) {
            try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
                for (List<String> cellList : data) {
                    csvPrinter.printRecord(cellList);
                }
                csvPrinter.printRecord();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}