package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelReader {
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;

    public ExcelReader(String excelPath, String sheetName) throws IOException {
        workbook = new XSSFWorkbook(excelPath);
        sheet = workbook.getSheet(sheetName);
    }

    public String getCellData(int rowNum, int columnNum) {
        return sheet.getRow(rowNum).getCell(columnNum).getStringCellValue();
    }

    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }
}