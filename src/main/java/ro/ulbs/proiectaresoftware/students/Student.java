package ro.ulbs.proiectaresoftware.students;

public class Student {
    int numarMatricol;
    String prenume;
    String nume;
    String formatieDeStudiu;

    public Student(int numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.prenume= prenume;
        this.nume= nume;
        this.formatieDeStudiu= formatieDeStudiu;
    }

    @Override
    public String toString() {
        /*return "Student{" + "numarMatricol=" + numarMatricol + ", prenume='" + prenume + '\'' + ", nume='" + nume + '\'' + ", formatieDeStudiu='" + formatieDeStudiu + '\'' + '}';*/
        return String.format("%5d %10s %s %s", numarMatricol, prenume, nume, formatieDeStudiu);
    }

}

