package reader.writer;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderWriter implements ReaderWriter<List<List<String>>> {
    @Override
    public List<List<String>> read(String path) {
        List<List<String>> list = new ArrayList<>();
        File file = new File(path);
        FileInputStream stream;
        HSSFWorkbook workbook = null;
        try {
            stream = new FileInputStream(file);
            workbook = new HSSFWorkbook(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (workbook != null) {
            HSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                List<String> cellList = new ArrayList<>();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellType();
                    if ("STRING".equals(cellType.toString())) {
                        cellList.add(cell.getStringCellValue());
                    }
                }
                list.add(cellList);
            }
        }
        return list;
    }

    @Override
    public void write(String path, List<List<String>> data) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet 1");
        int rowStartIndex = 3;
        int columnStartIndex = 1;
        Row row;
        Cell cell;
        for (int i = 0; i < data.size(); i++) {
            row = sheet.createRow(rowStartIndex + i);
            List<String> cellList = data.get(i);
            for (int j = 0; j < cellList.size(); j++) {
                cell = row.createCell(columnStartIndex + j, CellType.STRING);
                cell.setCellValue(cellList.get(j));
            }
        }
        File file = new File(path);
        try {
            boolean isCreated = file.createNewFile();
            FileOutputStream stream = new FileOutputStream(file);
            workbook.write(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}