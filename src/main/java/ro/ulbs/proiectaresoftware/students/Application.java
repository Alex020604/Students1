package ro.ulbs.proiectaresoftware.students;

import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;


public class Application {
    public static void main(String[] args) {
        List<Student> studenti = citesteStudentiDinCSV("studenti.CSV");

        Set<Student> set=new HashSet<>(studenti);

        System.out.println(prezent(new Student(null, "Ioan", "Popa", "TI21/1"), set));
        System.out.println(prezent(new Student(null, "Alex", "Oprea", "TI21/1"), set));

        sortareNume(studenti);
        afisare(studenti);

    }

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
    public static void sortareNume(List<Student> studenti){
        //studenti.sort((s1, s2) -> s1.nume.compareTo(s2.nume));
        Collections.sort(studenti, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.nume.compareTo(s2.nume);
            }
        }
    }
    public static void sortarePrenumeGrupa(List<Student> studenti){
        Collections.sort(studenti, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if(s1.formatieDeStudiu.equals(s2.formatieDeStudiu))
                    return s1.formatieDeStudiu.compareTo(s2.formatieDeStudiu);
                else  if(s1.formatieDeStudiu.equals(s2.formatieDeStudiu))
                    return s1.nume.compareTo(s2.nume);
                else if ( s1.formatieDeStudiu.equals(s2.formatieDeStudiu))
                    return s1.prenume.compareTo(s2.prenume);
            }
        }
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

}
