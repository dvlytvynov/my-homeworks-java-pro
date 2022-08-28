package application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reader.writer.ReaderWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SalaryReportMaker {
    private static final Logger LOGGER = LoggerFactory.getLogger(SalaryReportMaker.class);
    private final String directory;
    private final ReaderWriter<List<List<String>>> reader;
    private final ReaderWriter<List<List<String>>> writer;

    public SalaryReportMaker(String directory,
                             ReaderWriter<List<List<String>>> reader,
                             ReaderWriter<List<List<String>>> writer) {
        this.directory = directory;
        this.reader = reader;
        this.writer = writer;
    }

    public void makeReport(String inputFileExtension, String outputReportFileName) {
        char separator = File.separatorChar;
        List<File> inputFileList = findFiles(directory, inputFileExtension);
        List<Map<String, Double>> totalSalaryList = new ArrayList<>();
        for (File file : inputFileList) {
            List<List<String>> monthSalaryList = reader.read(file.getAbsolutePath());
            LOGGER.info("Reading workers salary data from file: " + file.getAbsolutePath());
            Map<String, Double> monthSalaryMap = transformListToMap(monthSalaryList);
            totalSalaryList.add(monthSalaryMap);
        }
        Map<String, Double> sumSalaryMap = calculateSalary(totalSalaryList);
        totalSalaryList.add(sumSalaryMap);
        List<List<String>> reportSalaryTable = prepareOutputData(totalSalaryList);
        String path = directory + separator + outputReportFileName;
        writer.write(path, reportSalaryTable);
        LOGGER.info("Writing total workers salary data to file: " + path);
    }

    protected List<File> findFiles(String directory, String extension) {
        List<File> fileList = new ArrayList<>();
        File file = new File(directory);
        File[] files = file.listFiles();
        if (files == null) {
            return fileList;
        }
        for (File f : files) {
            String name = f.getName();
            LOGGER.debug("Found file: " + name);
            if (name.endsWith(extension)) {
                fileList.add(f);
            }
        }
        return fileList;
    }

    protected Map<String, Double> transformListToMap(List<List<String>> salaryList) {
        Map<String, Double> map = new HashMap<>();
        for (List<String> list : salaryList) {
            LOGGER.debug("Input data: " + list);
            String name = list.get(1);
            String salaryString = list.get(2);
            if (isNumber(salaryString)) {
                Double salary = Double.parseDouble(salaryString);
                map.put(name, salary);
            }
        }
        return map;
    }

    protected boolean isNumber(String inputString) {
        char[] charArray = inputString.toCharArray();
        int amountComma = 0;
        for (char c : charArray) {
            boolean isDigit = (c >= 0x30 && c <= 0x39);
            boolean isComma = (c == 0x2e);
            if (isComma) {
                amountComma++;
            }
            if (!isDigit && !isComma) {
                return false;
            }
        }
        if (amountComma == charArray.length) {
            return false;
        }
        return amountComma <= 1;
    }

    protected Map<String, Double> calculateSalary(List<Map<String, Double>> salaryTable) {
        Map<String, Double> sumSalaryMap = new HashMap<>();
        for (Map<String, Double> monthSalaryMap : salaryTable) {
            Set<String> keySet = monthSalaryMap.keySet();
            for (String key : keySet) {
                if (sumSalaryMap.containsKey(key)) {
                    Double savedValue = sumSalaryMap.get(key);
                    Double currentValue = monthSalaryMap.get(key);
                    sumSalaryMap.put(key, savedValue + currentValue);
                } else {
                    sumSalaryMap.put(key, monthSalaryMap.get(key));
                }
            }
        }
        LOGGER.debug("Sum of workers salaries: \n" + sumSalaryMap);
        return sumSalaryMap;
    }

    protected List<List<String>> prepareOutputData(List<Map<String, Double>> inputSalaryList) {
        List<List<String>> outputSalaryList = new ArrayList<>();
        Map<String, Double> sumSalaryMap = inputSalaryList.get(inputSalaryList.size() - 1);
        Set<String> keySet = sumSalaryMap.keySet();
        List<String> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            List<String> rawList = new ArrayList<>();
            rawList.add(String.valueOf(i + 1));
            rawList.add(key);
            for (Map<String, Double> map : inputSalaryList) {
                Double salary = map.get(key);
                rawList.add(String.valueOf(salary));
            }
            outputSalaryList.add(rawList);
        }
        return outputSalaryList;
    }
}
