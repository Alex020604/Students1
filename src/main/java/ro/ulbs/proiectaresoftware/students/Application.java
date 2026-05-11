package ro.ulbs.proiectaresoftware.students;

import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;

public class Application {
    public static void main(String[] args) {
        List<Student> studenti = citesteStudentiDinCSV("studenti.CSV");

        Set<Student> set=new HashSet<>(studenti);

        System.out.println(prezent(new Student(null, "Ioan", "Popa", "TI21/1"), set));
        System.out.println(prezent(new Student(null, "Alex", "Oprea", "TI21/1"), set));

        sortareNume(studenti);
        afisare(studenti);

        Map<Integer, List<Integer>> note = citireNote("note.csv");

        scrieInExcel("studenti_note.xlsx", studenti, note);
        scrieRaportCSV("raport_final.csv", studenti, note);

        export(studenti, getExporter("studenti.csv"));
        export(studenti, getExporter("export.xlsx"));

        Importer importer = new ImportFromCsv();
        Importer importer2 = new ImportFromExcel();

        Importer importerStudenti = getImporter("studenti.csv");
        importerStudenti.importStudents("studenti.csv");

        Importer importerNote = getImporter("note.csv");
        importerNote.importNotes("note.csv");

        Importer importerStudenti1 = getImporter("export.xlsx");
        importerStudenti.importStudents("export.xlsx");


        /*ImportFromExcel importer1 = new ImportFromExcel();

        importer.importStudents("studenti.xlsx");
        importer.importStudents("note.xlsx");*/


        afisareCuNote(studenti, note);
    }
    Map<Integer, List<Integer>> note=citireNote("note.CSV");

    private static List<Student> createLista() {
        List<Student> studenti=new ArrayList<>();
        Student s1=new Student("112", "Ioan", "Popa", "TI21/1");
        Student s2=new Student("112", "Maria", "Oprea", "TI21/1");
        Student s3=new Student("120", "Alis", "Popa", "TI21/2");
        Student s4=new Student("122", "Mihai", "Vercedea", "TI22/1");
        Student s5=new Student("122", "Eugen", "Arilescu", "TI22/2");


        //System.out.println("Sunt "+studenti.size()+" "+"studenti");

        studenti.add(s1);
        studenti.add(s2);
        studenti.add(s3);
        studenti.add(s4);
        studenti.add(s5);

        return   studenti;

    }

    private static void afisare(List<Student> studenti) {
            for (Student s : studenti) {
            System.out.println(s);
        }
    }

    public static boolean prezent(Student student,Set <Student> studenti){
        return studenti.contains(student);
    }
    public static void sortareNume(List<Student> studenti) {
        Collections.sort(studenti, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.nume.compareTo(s2.nume);
            }
        });
    }

    public static void sortarePrenumeGrupa(List<Student> studenti) {
        Collections.sort(studenti, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {

                int cmp = s1.formatieDeStudiu.compareTo(s2.formatieDeStudiu);
                if (cmp != 0) return cmp;

                cmp = s1.nume.compareTo(s2.nume);
                if (cmp != 0) return cmp;

                return s1.prenume.compareTo(s2.prenume);
            }
        });
    }

    public static List<Student> citesteStudentiDinCSV(String numeFisier) {
        List<Student> studenti = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] valori = linie.split(",");
                if (valori.length >= 4) {
                    studenti.add(new Student(valori[0].trim(), valori[1].trim(), valori[2].trim(), valori[3].trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului: " + e.getMessage());
        }
        return studenti;
    }
    public static Map<Integer, List<Integer>> citireNote(String numeFisier) {
        Map<Integer, List<Integer>> note = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(numeFisier))) {
            String linie;

            while ((linie = br.readLine()) != null) {
                String[] c = linie.split(",");

                int id = Integer.parseInt(c[0]);
                int nota = Integer.parseInt(c[1]);


                note.putIfAbsent(id, new ArrayList<>());


                note.get(id).add(nota);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return note;
    }
    public static Integer getNota(Student student, Map<String, Integer> note) {
        return note.get(student.getNumarMatricol());
    }
    private static Map<Student,Integer>mapareNote(Map<String, Integer> note,List<Student> lista) {
        Map<Student, Integer> rezultat = new HashMap<>();

        for (Student s : lista) {
            String id = s.getNumarMatricol();
            Integer nota = note.get(id);
            rezultat.put(s, nota);
        }
        return rezultat;
    }
    public static void scrieInExcel(String numeFisier,
                                    List<Student> studenti,
                                    Map<Integer, List<Integer>> note) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Studenti");

        int rowIndex = 0;

        // Header
        Row header = sheet.createRow(rowIndex++);

        header.createCell(0).setCellValue("Numar Matricol");
        header.createCell(1).setCellValue("Prenume");
        header.createCell(2).setCellValue("Nume");
        header.createCell(3).setCellValue("Grupa");
        header.createCell(4).setCellValue("Note");

        // Date
        for (Student s : studenti) {

            Row row = sheet.createRow(rowIndex++);

            row.createCell(0).setCellValue(s.getNumarMatricol());
            row.createCell(1).setCellValue(s.prenume);
            row.createCell(2).setCellValue(s.nume);
            row.createCell(3).setCellValue(s.formatieDeStudiu);

            int id = Integer.parseInt(s.getNumarMatricol());

            List<Integer> listaNote = note.get(id);

            StringBuilder sb = new StringBuilder();

            if (listaNote != null) {
                for (Integer n : listaNote) {
                    sb.append(n).append(" ");
                }
            }

            row.createCell(4).setCellValue(sb.toString().trim());
        }

        // Ajusteaza automat coloanele
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fos = new FileOutputStream(numeFisier)) {

            workbook.write(fos);
            workbook.close();

            System.out.println("Fisier Excel creat cu succes!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void scrieRaportCSV(String numeFisierOut,
                                      List<Student> studenti,
                                      Map<Integer, List<Integer>> note) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(numeFisierOut))) {

            bw.write("NumarMatricol,Prenume,Nume,Grupa,Note");
            bw.newLine();

            for (Student s : studenti) {

                int id = Integer.parseInt(s.getNumarMatricol());

                List<Integer> listaNote = note.get(id);

                StringBuilder sb = new StringBuilder();

                if (listaNote != null) {
                    for (int i = 0; i < listaNote.size(); i++) {
                        sb.append(listaNote.get(i));
                        if (i < listaNote.size() - 1) {
                            sb.append(" ");
                        }
                    }
                }

                bw.write(
                        s.getNumarMatricol() + "," +
                                s.prenume + "," +
                                s.nume + "," +
                                s.formatieDeStudiu + "," +
                                sb
                );

                bw.newLine();
            }

            System.out.println("CSV generat cu succes!");

        } catch (IOException e) {
            System.err.println("Eroare la scriere CSV: " + e.getMessage());
        }
    }
    public static Exporter getExporter(String filename) {

        String fileExtension =
                filename.substring(filename.lastIndexOf(".") + 1);

        switch (fileExtension) {

            case "csv":
                return new ExportToCsv("export.csv");

            case "xlsx":
                return new ExportToExcel();

            default:
                throw new IllegalArgumentException(
                        "Unknown file extension: " + fileExtension);
        }
    }

    public static void export(List<Student> list, Exporter exporter) {
        exporter.export(list);
    }
    public static Importer getImporter(String... args) {

        String file = args[0];
        String ext = file.substring(file.lastIndexOf(".") + 1);

        switch (ext) {

            case "csv":
                return new ImportFromCsv();

            case "xlsx":
                return new ImportFromExcel();

            default:
                throw new IllegalArgumentException("Unknown format: " + ext);
        }
    }
    public static void importStudents(Importer importer, String file) {
        importer.importStudents(file);
    }
    public static void importNotes(Importer importer, String file) {
        importer.importNotes(file);
    }
    public static void afisareCuNote(List<Student> studenti,
                                     Map<Integer, List<Integer>> note) {

        for (Student s : studenti) {

            int id = Integer.parseInt(s.getNumarMatricol());
            List<Integer> listaNote = note.get(id);

            System.out.print(s + " -> ");

            if (listaNote != null) {
                for (Integer n : listaNote) {
                    System.out.print(n + " ");
                }
            } else {
                System.out.print("fara note");
            }

            System.out.println();
        }
    }

}
