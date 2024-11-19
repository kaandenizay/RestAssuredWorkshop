package cucumber.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataReader {

    static FileInputStream inputStream;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static Iterator<Row> rows;
    static Row row;
    static Iterator<Cell> cells;
    static Cell cell;

    private static void setUpExcelOperation() {
        try {
            inputStream = new FileInputStream("src/test/resources/MyTestResource.xlsx");
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int sheetCount = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetCount; i++) {
            sheet = workbook.getSheetAt(i);
            System.out.println("sheetName = " + sheet.getSheetName());

        }
    }

    @Test
    public static void getExcelRowData(String rowName) {
        setUpExcelOperation();
        ArrayList<String> rowData = new ArrayList<>();

        rows = sheet.rowIterator();
        while (rows.hasNext()) {
            row = rows.next();
            cells = row.cellIterator();
            while (cells.hasNext()) {
                cell = cells.next();
                if (row.getCell(0).getStringCellValue().equalsIgnoreCase(rowName)) {
                    if (cell.getColumnIndex() != 0) {
                        rowData.add(cell.getStringCellValue());}
                }
                else {
                    break;
                }

            }
        }
        System.out.println(rowData);
        // Purchase -> [purchase1, purchase2, purchase3]
//        return rowData;
    }


    @Test
    public static void getExcelColumnData() {
        setUpExcelOperation();
        ArrayList<String> columnData = new ArrayList<>();
        int indexOfColumn = 0;

        rows = sheet.rowIterator();
        row = rows.next();
        cells = row.cellIterator();
        while (cells.hasNext()) {
            cell = cells.next();
            if (cell.getStringCellValue().equalsIgnoreCase("Data2")) {
                indexOfColumn = cell.getColumnIndex();
                break;
            }
        }

        while (rows.hasNext()) {
            row = rows.next();
            columnData.add(row.getCell(indexOfColumn).getStringCellValue());
        }


        System.out.println(columnData);
        // Data2 -> [login2, purchase2, 28]
//        return columnData;
    }
}
