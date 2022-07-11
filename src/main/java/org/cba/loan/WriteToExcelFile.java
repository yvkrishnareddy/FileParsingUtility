package org.cba.loan;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WriteToExcelFile extends KeyValuesFromFile{
    public void writeToExcelFile(){
        KeyValuesFromFile keyValuesFromFile = new KeyValuesFromFile();
        try
        {
            ArrayList< ListOfPairs > map = keyValuesFromFile.addToListOfPairs();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("KeyValues");// creating a blank sheet
            XSSFCellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);

            Row headerRow = sheet.createRow(0);
            Cell cell0 = headerRow.createCell(0);
            headerRow.createCell(0).setCellValue("Key");
            cell0.setCellStyle(style);
            Cell cell1 = headerRow.createCell(1);
            headerRow.createCell(1).setCellValue("Value");
            cell1.setCellStyle(style);
            int rowNum = 1;
            for (ListOfPairs lop : map)
            {
                Row dataRows = sheet.createRow(rowNum++);
                createList(lop, dataRows);
            }

            String outPutFilePath = "OutPut";
            String fileNameSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            FileOutputStream out = new FileOutputStream(outPutFilePath+"/"+"KayValues_"+fileNameSuffix+".xlsx");
            workbook.write(out);
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    // creating cells for each row
    private static void createList(ListOfPairs lop, Row row)
    {
        Cell cell = row.createCell(0);
        cell.setCellValue(lop.getL());

        cell = row.createCell(1);
        cell.setCellValue(lop.getR());
    }
}
