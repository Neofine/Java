// klasa wyborcy
public abstract class Voter {
    // imię i nazwisko wyborcy
    protected final String name, surname;
    // numer dystryktu wyborcy
    protected final int districtNumber;
    // Bajtocja, czyli "kraj" wyborcy
    protected final Byteotion byteotion;

    // konstruktor wyborcy
    Voter(Byteotion byteotion, String name, String surname, int districtNumber) {
        this.byteotion = byteotion;
        this.name = name;
        this.surname = surname;
        this.districtNumber = districtNumber;
    }

    // getter zwracający imię i nazwisko wyborcy
    protected String nameSurname() {
        return name + " " + surname;
    }

    // getter zwracający czy wyborca jest ważonym czy nie
    protected abstract boolean isWeighted();

    // zwraca na którego kandydata wyborca zagłosuje
    protected abstract Candidate chooseCandidate();
}
