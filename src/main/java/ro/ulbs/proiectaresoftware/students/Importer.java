package ro.ulbs.proiectaresoftware.students;
import java.util.List;
import java.util.Map;

public interface Importer {
    List<Student> importStudents(String file);
    Map<Integer, List<Integer>> importNotes(String file);
}
