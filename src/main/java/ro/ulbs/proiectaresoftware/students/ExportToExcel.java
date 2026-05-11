package ro.ulbs.proiectaresoftware.students;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

public class ExportToExcel implements Exporter {

    @Override
    public void export(List<Student> studenti) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Studenti");

        int rowIndex = 0;

        Row header = sheet.createRow(rowIndex++);
        header.createCell(0).setCellValue("Matricol");
        header.createCell(1).setCellValue("Prenume");
        header.createCell(2).setCellValue("Nume");
        header.createCell(3).setCellValue("Grupa");

        for (Student s : studenti) {

            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(s.getNumarMatricol());
            row.createCell(1).setCellValue(s.prenume);
            row.createCell(2).setCellValue(s.nume);
            row.createCell(3).setCellValue(s.formatieDeStudiu);
        }

        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fos = new FileOutputStream("export.xlsx")) {

            workbook.write(fos);
            workbook.close();

            System.out.println("Export Excel realizat!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
