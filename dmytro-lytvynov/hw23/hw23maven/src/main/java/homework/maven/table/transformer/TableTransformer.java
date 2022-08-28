package homework.maven.table.transformer;

import java.io.File;
import java.util.List;

public class TableTransformer {
    public void transform() {
        char separator = File.separatorChar;
        String directory = "." + separator
                + "src" + separator
                + "main" + separator
                + "resources";
        String inputFileName = "input_data.csv";
        String outputFileName = "output_table.xls";
        CsvFileReader csvFileReader = new CsvFileReader();
        List<List<String>> table = csvFileReader.read(directory + separator + inputFileName);
        ExcelTableWriter excelTableWriter = new ExcelTableWriter();
        excelTableWriter.write(directory + separator + outputFileName, table);
    }
}
