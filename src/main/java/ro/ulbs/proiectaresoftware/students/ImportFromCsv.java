package ro.ulbs.proiectaresoftware.students;
import java.io.*;
import java.util.*;
public class ImportFromCsv implements Importer {

    @Override
    public List<Student> importStudents(String file) {

        List<Student> studenti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");

                studenti.add(new Student(
                        p[0].trim(),
                        p[1].trim(),
                        p[2].trim(),
                        p[3].trim()
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

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");

                int id = Integer.parseInt(p[0].trim());
                int nota = Integer.parseInt(p[1].trim());

                note.putIfAbsent(id, new ArrayList<>());
                note.get(id).add(nota);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return note;
    }
}
