package application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reader.writer.CsvReaderWriter;
import reader.writer.ExcelReaderWriter;
import reader.writer.ReaderWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class SalaryReportMakerTest {
    private final SalaryReportMaker testInstance;
    @Mock
    private ReaderWriter<List<List<String>>> reader;

    @Mock
    private ReaderWriter<List<List<String>>> writer;

    public SalaryReportMakerTest() {

        testInstance = new SalaryReportMaker("", reader, writer);
    }

    @Test
    void makeReportTest() {
        char separator = File.separatorChar;
        String testDirectory = "." + separator
                + "src" + separator
                + "test" + separator
                + "resources" + separator
                + "report";
        String extension = ".csv";
        ReaderWriter<List<List<String>>> reader = new CsvReaderWriter();
        ReaderWriter<List<List<String>>> writer = new ExcelReaderWriter();
        SalaryReportMaker salaryReportMaker = new SalaryReportMaker(testDirectory, reader, writer);
        salaryReportMaker.makeReport(extension, "test_report.xls");
        String inputFilePath1 = testDirectory + separator + "test_salary_1.csv";
        String inputFilePath2 = testDirectory + separator + "test_salary_2.csv";
        String outputFilePath = testDirectory + separator + "test_report.xls";
        List<List<String>> listFromCsv1 = reader.read(inputFilePath1);
        List<List<String>> listFromCsv2 = reader.read(inputFilePath2);
        List<List<String>> listFromXls = writer.read(outputFilePath);
        Map<String, String> mapFromCsv1 = new HashMap<>();
        Map<String, String> mapFromCsv2 = new HashMap<>();
        Map<String, String> sumColumnMapFromXls = new HashMap<>();
        for (List<String> list : listFromCsv1) {
            mapFromCsv1.put(list.get(1), list.get(2));
        }
        for (List<String> list : listFromCsv2) {
            mapFromCsv2.put(list.get(1), list.get(2));
        }
        for (List<String> list : listFromXls) {
            sumColumnMapFromXls.put(list.get(1), list.get(list.size() - 1));
        }
        for (String name : sumColumnMapFromXls.keySet()) {
            String sumValue = sumColumnMapFromXls.get(name);
            String value1 = mapFromCsv1.get(name);
            String value2 = mapFromCsv2.get(name);
            double sum = Double.parseDouble(sumValue);
            double v1 = Double.parseDouble(value1);
            double v2 = Double.parseDouble(value2);
            assertEquals(sum, v1 + v2,
                    "sum value is incorrect: " +
                            "sum=" + sum + "  value1= " + v1 + "  value2= " + v2);
        }
    }

    @Test
    void findFilesTest() {
        char separator = File.separatorChar;
        String testDirectory = "." + separator
                + "src" + separator
                + "test" + separator
                + "resources";
        String extension = ".csv";
        String[] fileNames = new String[]{
                "test01.csv",
                "test02.csv",
                "test03.csv",
                "test04.jpg",
                "test05.txt"};
        for (String str : fileNames) {
            File file = new File(testDirectory + separator + str);
            try {
                boolean isCreated = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<File> list = testInstance.findFiles(testDirectory, extension);
        for (String str : fileNames) {
            boolean isFound = false;
            for (File file : list) {
                if (file.getName().equals(str)) {
                    isFound = true;
                }
            }
            boolean isRightExtension = str.endsWith(extension);
            if (isRightExtension) {
                assertTrue(isFound, "file \"" + str + "\" should be found");
            } else {
                assertFalse(isFound, "file \"" + str + "\" shouldn't be found");
            }
        }
    }

    @Test
    void transformListToMapTest() {
        List<String> valueList1 = List.of("number1", "name1", "12.56");
        List<String> valueList2 = List.of("number2", "name2", "5");
        List<String> valueList3 = List.of("number3", "name3", "0.23");
        List<List<String>> list = new ArrayList<>();
        list.add(valueList1);
        list.add(valueList2);
        list.add(valueList3);
        Map<String, Double> map = testInstance.transformListToMap(list);
        for (List<String> raw : list) {
            boolean isKeyFound = false;
            String value = "";
            String name = raw.get(1);
            for (String key : map.keySet()) {
                isKeyFound = isKeyFound || name.equals(key);
                if (isKeyFound) {
                    value = raw.get(2);
                }
            }
            assertTrue(isKeyFound, "key " + name + " should be found");
            double mapValue = map.get(name);
            double listValue = Double.parseDouble(value);
            assertEquals(mapValue, listValue, "value from map should be " + listValue);
        }
    }

    @Test
    void isNumberTest() {
        String[] rightNumbers = new String[]{
                "123456789.05",
                "1234",
                "0.25",
                "0"};
        String[] wrongNumbers = new String[]{
                ".",
                "2.25.5",
                "2.25.5.858",
                "number255858",
                "digit"};
        for (String str : rightNumbers) {
            boolean resultIsNumber = testInstance.isNumber(str);
            assertTrue(resultIsNumber, "isNumber of \"" + str + "\" should be true");
        }
        for (String str : wrongNumbers) {
            boolean resultIsNumber = testInstance.isNumber(str);
            assertFalse(resultIsNumber, "isNumber of \"" + str + "\" should be false");
        }
    }

    @Test
    void calculateSalaryTest() {
        String[] names = {"name1", "name2", "name3", "name4", "name5"};
        double[] sums = new double[names.length];
        Map<String, Double> map1 = Map.of(
                names[0], 2.15, names[1], 3.10,
                names[2], 4.89, names[3], 5.50);
        Map<String, Double> map2 = Map.of(
                names[0], 3.85, names[1], 4.25,
                names[2], 5.60, names[4], 8.33);
        Map<String, Double> map3 = Map.of(
                names[0], 1.0, names[1], 0.0,
                names[2], 10.0, names[4], 6.0);
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            if (map1.containsKey(name)) {
                sums[i] = sums[i] + map1.get(name);
            }
            if (map2.containsKey(name)) {
                sums[i] = sums[i] + map2.get(name);
            }
            if (map3.containsKey(name)) {
                sums[i] = sums[i] + map3.get(name);
            }
        }
        List<Map<String, Double>> list = List.of(map1, map2, map3);
        Map<String, Double> sumMap = testInstance.calculateSalary(list);
        for (String name : names) {
            assertTrue(sumMap.containsKey(name), "sum map doesn't contain key " + name);
        }
        for (int i = 0; i < names.length; i++) {
            double sum = sums[i];
            String key = names[i];
            double value = sumMap.get(key);
            assertEquals(value, sum,
                    "this map value is incorrect: " +
                            "key=" + key + "  value= " + value + "  should be" + sum);
        }
    }

    @Test
    void prepareOutputDataTest() {
        String[] numbers = {"1", "2", "3", "4", "5"};
        String[] names = {"name1", "name2", "name3", "name4", "name5"};
        String[] salaries1 = {"12.88", "14.56", "55.16", "10.0", "28.25"};
        String[] salaries2 = {"40.0", "50.0", "20.0", "8.0", "30.0"};
        List<List<String>> outputListSample = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            List<String> list = List.of(numbers[i], names[i], salaries1[i], salaries2[i]);
            outputListSample.add(list);
        }
        Map<String, Double> map1 = new HashMap<>();
        Map<String, Double> map2 = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map1.put(names[i], Double.parseDouble(salaries1[i]));
            map2.put(names[i], Double.parseDouble(salaries2[i]));
        }
        List<Map<String, Double>> inputList = List.of(map1, map2);
        List<List<String>> outputList = testInstance.prepareOutputData(inputList);
        assertEquals(outputListSample.size(), outputList.size(),
                "output list doesn't match to sample list");
        for (int i = 0; i < outputListSample.size(); i++) {
            List<String> valueListSample = outputListSample.get(i);
            List<String> valueList = outputList.get(i);
            assertEquals(valueListSample.size(), valueList.size(),
                    "output list doesn't match to sample list");
            for (int j = 0; j < valueListSample.size(); j++) {
                String valueSample = valueListSample.get(j);
                String value = valueList.get(j);
                assertEquals(valueSample, value,
                        "value doesn't match sample: " +
                                "expected " + valueSample + "  have " + value);
            }
        }
    }
}
