import application.SalaryReportMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reader.writer.CsvReaderWriter;
import reader.writer.ExcelReaderWriter;
import reader.writer.ReaderWriter;

import java.io.File;
import java.util.List;

public class MainTesting {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainTesting.class);

    public static void main(String[] args) {
        LOGGER.info("MainTesting.main() starts\n- - - - - - Homework 26 Testing - - - - - -");
        char separator = File.separatorChar;
        String directory = "." + separator
                + "src" + separator
                + "main" + separator
                + "resources" + separator
                + "report";
        String outputReportFileName = "workers_salary_report.xls";
        String inputFileExtension = ".csv";
        ReaderWriter<List<List<String>>> reader = new CsvReaderWriter();
        ReaderWriter<List<List<String>>> writer = new ExcelReaderWriter();
        SalaryReportMaker salaryReportMaker = new SalaryReportMaker(directory, reader, writer);
        salaryReportMaker.makeReport(inputFileExtension, outputReportFileName);
    }
}
