package ro.ulbs.proiectaresoftware.students;
import java.io.*;
import java.util.List;

public class ExportToCsv implements Exporter {

    private String filename;

    public ExportToCsv(String filename) {
        this.filename = filename;
    }

    @Override
    public void export(List<Student> studenti) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            bw.write("Matricol,Prenume,Nume,Grupa");
            bw.newLine();

            for (Student s : studenti) {

                bw.write(
                        s.getNumarMatricol() + "," +
                                s.prenume + "," +
                                s.nume + "," +
                                s.formatieDeStudiu
                );

                bw.newLine();
            }

            System.out.println("Export CSV realizat!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
