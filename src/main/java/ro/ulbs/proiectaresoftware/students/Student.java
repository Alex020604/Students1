package ro.ulbs.proiectaresoftware.students;
import java.util.Objects;

public class Student {
    String numarMatricol;
    String prenume;
    String nume;
    String formatieDeStudiu;

    public Student(String numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume= prenume;
        this.nume= nume;
        this.formatieDeStudiu= formatieDeStudiu;
    }

    @Override
    public String toString() {
        /*return "Student{" + "numarMatricol=" + numarMatricol + ", prenume='" + prenume + '\'' + ", nume='" + nume + '\'' + ", formatieDeStudiu='" + formatieDeStudiu + '\'' + '}';*/
        return String.format("%5d %10s %s %10s", numarMatricol, prenume, nume, formatieDeStudiu);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(prenume, student.prenume) && Objects.equals(nume, student.nume) && Objects.equals(formatieDeStudiu, student.formatieDeStudiu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenume, nume, formatieDeStudiu);
    }
}



