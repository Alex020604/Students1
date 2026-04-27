package ro.ulbs.proiectaresoftware.students;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static ro.ulbs.proiectaresoftware.students.Application.prezent;

class ApplicationTest {

    @Test
    void prezent_dacaStudentPrezent() {
        Student s=new Student("200","Alex","Olariu","C22/2");
        Set<Student> studenti=new HashSet<>();
        studenti.add(s);
        assertEquals(true,prezent(s,studenti));
    }
    @Test
    void prezent_dacaStudentNuPrezent() {
        Student s=new Student("200","Alex","Olariu","C22/2");
        Set<Student> studenti=new HashSet<>();
        assertEquals(false,prezent(s,studenti));
    }
    @Test
    void prezent_dacaStudentNuNuPrezent1() {

    }

    @Test
    void sortareNume() {
    }

    @Test
    void sortarePrenumeGrupa() {
    }

    @Test
    void getNota_notaAsociataNrMatricol() {
        Student s=new Student("200","Alex","Olariu","C22/2");
        Map<String,Integer> nota=Map.of("200",10);
        Integer nota1=nota.get("200");
        assertEquals(10,nota1);
    }
    @Test
    void getNota_notaNuAsociataNrMatricol() {
        Student s=new Student(null,"Alex","Olariu","C22/2");
        Map<String,Integer> nota=Map.of("200",10);
        Integer nota1=nota.get(null);
        assertEquals(10,nota1);
    }
    @Test
    void getNota_dacaStudentulNuLista() {
        Student s=new Student("200","Alex","Olariu","C22/2");
    }
    @Test
    void getNota_dacaNotaEsteNull() {
        Student s=new Student("200","Alex","Olariu","C22/2");
        Map<String,Integer> nota=Map.of("200",null);
        assertThrows(NullPointerException.class, () -> nota.get(null));
    }
}