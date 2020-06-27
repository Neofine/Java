public class Osoba {
    public String imię, nazwisko;
    public String rokUrodzenia;
    Osoba(String imię, String nazwisko) {
        this.imię = imię;
        this.nazwisko = nazwisko;
    }
    String imięNazwisko() {
        return (this.imię + " " + this.nazwisko);
    }
    String imięNazwisko(String tytuł) {
        return (tytuł + this.imię + " " + this.nazwisko);
    }
    // Kompilator się połapał, naprawdę niesamowite
    public String toString() {
        return imięNazwisko("Pani") + "_" + rokUrodzenia;
    }
}
