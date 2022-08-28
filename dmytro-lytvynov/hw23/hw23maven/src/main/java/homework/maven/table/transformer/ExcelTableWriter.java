package homework.maven.table.transformer;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelTableWriter {
    public void write(String filePath, List<List<String>> rowList) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet 1");
        int rowStartIndex = 3;
        int columnStartIndex = 1;
        Row row;
        Cell cell;
        for (int i = 0; i < rowList.size(); i++) {
            row = sheet.createRow(rowStartIndex + i);
            List<String> cellList = rowList.get(i);
            for (int j = 0; j < cellList.size(); j++) {
                cell = row.createCell(columnStartIndex + j, CellType.STRING);
                cell.setCellValue(cellList.get(j));
            }
        }
        File file = new File(filePath);
        try {
            boolean isCreated = file.createNewFile();
            if (isCreated) {
                System.out.println(
                        "Created new Excel file for writing table: " + file.getAbsolutePath());
            } else {
                System.out.println("Used Excel file for writing table: " + file.getAbsolutePath());
            }
            FileOutputStream stream = new FileOutputStream(file);
            workbook.write(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
