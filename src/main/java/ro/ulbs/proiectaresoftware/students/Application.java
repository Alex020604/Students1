package ro.ulbs.proiectaresoftware.students;

import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        Student s1=new Student(null, "Ioan", "Popa", "TI21/1");
        Student s2=new Student(null, "Maria", "Oprea", "TI21/1");
        Student s3=new Student(null, "Alis", "Popa", "TI21/2");
        Student s4=new Student(null, "Mihai", "Vercedea", "TI22/1");
        Student s5=new Student(null, "Eugen", "Urilescu", "TI22/2");

        List<Student> studenti=new ArrayList<>();

        System.out.println("Sunt "+studenti.size()+" "+"studenti");

        studenti.add(s1);
        studenti.add(s2);
        studenti.add(s3);
        studenti.add(s4);
        studenti.add(s5);

        Set<Student> set=new HashSet<>(studenti);

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);

        System.out.println("Sunt "+studenti.size()+" "+"studenti");

        for (int j=0;j<studenti.size();j++){
            System.out.println(studenti.get(j));
        }
        System.out.println();

        metoda(studenti);

        System.out.println(prezent(new Student(null, "Ioan", "Popa", "TI21/1"), set));
        System.out.println(prezent(new Student(null, "Alex", "Oprea", "TI21/1"), set));

    }
    public static boolean prezent(Student student,Set <Student> studenti){
        return studenti.contains(student);
    }

    private static void metoda(List<Student> studenti) {
        for(Student s: studenti){
            System.out.println(s);
        }
    }
}
