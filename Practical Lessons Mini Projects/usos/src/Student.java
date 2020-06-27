import java.util.ArrayList;

public class Student extends Osoba{
    private String imię, nazwisko;
    private int nrIndeksu, rok;
    private ArrayList<String> przedmioty;
    public static void main(String[] args) {
        //Student pierwszy = new Student("Jan", "Kowalski", 123456);
        //Student drugi = new Student();
        //Student trzeci = new Student(pierwszy);
    }
    /*

    Po tym jak zrobiłem super klasę osoba to przestała działać taka deklaracja konstruktora

    Student(Student student) {
        assert(student.imię == null);
        assert(student.nazwisko == null);
        this.imię = student.imię + "_kopia";
        this.nazwisko = student.nazwisko + "_kopia";
        this.rok = student.rok;
        this.nrIndeksu = student.nrIndeksu;
        this.przedmioty = new ArrayList<>();
        System.out.println("Jestem w konstruktorze z argumentem student w klasie Student.");
    }

    To też przestało działać

    Student() {
        this.rok = 1;
        System.out.println("Jestem w konstruktorze bezargumentowym w klasie Student.");
    }
    */

    Student(String imię, String nazwisko, int nrIndeksu) {
        //this();
        super(imię, nazwisko);
        assert(imię == null);
        assert(nazwisko == null);
        this.imię = imię;
        this.rok = 1;
        this.nazwisko = nazwisko;
        this.nrIndeksu = nrIndeksu;
        this.przedmioty = new ArrayList<>();
        System.out.println("Jestem w konstruktorze zwyczajnym klasie Student.");
    }
    /*

    Poprzedni toString, gdy nie było klasy Osoba

    public String toString() {
        String temporary;
        temporary = imięNazwisko() + ", " + nrIndeksu() + ", " + rok();
        for (int i = 0; i < przedmioty.size(); i++) {
            temporary += ", " + przedmioty.get(i);
        }
        return temporary;
    }
    */

    public String toString() {
        String temporary;
        temporary = super.toString() + ", " + nrIndeksu() + ", " + rok();
        for (int i = 0; i < przedmioty.size(); i++) {
            temporary += ", " + przedmioty.get(i);
        }
        return temporary;
    }

    void wypisywanie (String wypisz) {
        System.out.println(wypisz);
    }
    void zwiekszRok() {
        if (this.rok < 3) this.rok++;
    }
    void dodajPrzedmiot(String przedmiot) {
        przedmioty.add(przedmiot);
    }
    void usunPrzedmiot(String przedmiot) {
        przedmioty.remove(przedmiot);
    }
    int nrIndeksu() {
        return this.nrIndeksu;
    }
    int rok() {
        return this.rok;
    }
    String imięNazwisko() {
        return (this.imię + " " + this.nazwisko);
    }

}
