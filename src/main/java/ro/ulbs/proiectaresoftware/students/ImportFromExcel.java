package ro.ulbs.proiectaresoftware.students;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.*;

public class ImportFromExcel implements Importer {

    @Override
    public List<Student> importStudents(String file) {

        List<Student> studenti = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) continue;

                studenti.add(new Student(
                        row.getCell(0).toString(),
                        row.getCell(1).toString(),
                        row.getCell(2).toString(),
                        row.getCell(3).toString()
                ));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return studenti;
    }

    @Override
    public Map<Integer, List<Integer>> importNotes(String file) {

        Map<Integer, List<Integer>> note = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                int id = (int) row.getCell(0).getNumericCellValue();
                int nota = (int) row.getCell(1).getNumericCellValue();

                note.putIfAbsent(id, new ArrayList<>());
                note.get(id).add(nota);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return note;
    }
}
