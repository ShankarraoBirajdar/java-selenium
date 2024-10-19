
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelWriter {
    public static void main(String[] args) {
        // Sample data
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dataList.add("Data" + (i + 1));
        }

        // Write data to Excel
        try {
            writeToExcel(dataList);
            System.out.println("Data has been written to Excel successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to Excel: " + e.getMessage());
        }
    }

    private static void writeToExcel(List<String> dataList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        int rowNum = 0;
        int colNum = 0;

        for (String data : dataList) {
            if (rowNum % 10 == 0 && rowNum != 0) {
                colNum = (colNum == 0) ? 1 : 0; // Alternating columns
                rowNum = 0; // Reset row number after changing column
            }

            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }

            Cell cell = row.createCell(colNum);
            cell.setCellValue(data);

            rowNum++;
        }

        // Write the workbook to a file
        try (FileOutputStream outputStream = new FileOutputStream("data.xlsx")) {
            workbook.write(outputStream);
        }

        workbook.close();
    }
}
