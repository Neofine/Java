// klasa kandydata w Bajtocji
public class Candidate {
    // imię i nazwisko kandydata
    private final String name, surname;
    // numer partii kandydata
    private final int party;
    // numer dystryktu kandydata
    private final int districtNumber;
    // numer na liście partii w dystrykcie z którego kandyduje
    private final int listNumber;
    // tablica atrybutów/cech kandydata
    private final int[] attribute;
    // Bajtocja, czyli "kraj" kandydata
    private final Byteotion byteotion;
    // ilość głosów które kandydat otrzymał
    private int amountOfVotes = 0;
    // licznik przy pomocy którego dodaję atrybuty kandydata
    private int attributeCounter = 0;

    // konstruktor kandydata
    Candidate(Byteotion byteotion, String name, String surname, int districtNumber, int party, int listNumber) {
        this.byteotion = byteotion;
        this.name = name;
        this.surname = surname;
        this.party = party;
        this.districtNumber = districtNumber;
        this.listNumber = listNumber;
        attribute = new int[byteotion.operations().attributeAmount() + 1];
    }

    // setter dodający głos kandydatowi
    public void addVote() {
        amountOfVotes++;
    }

    // setter dodaje atrybut do ich tablicy u kandydata
    public void addAttribute(int wage) {
        attribute[++attributeCounter] = wage;
    }

    // resetuje liczbę głosów które kandydat otrzymał
    public void resetVotes() {
        amountOfVotes = 0;
    }

    // getter zwracający wartość pewnego atrybutu kandydata
    public int certainAttribute(int which) {
        return attribute[which];
    }

    // getter zwracający imię i nazwisko kandydata
    public String nameSurname() {
        return name + " " + surname;
    }

    // getter zwracający nr partii do której kandydat należy
    public int party() {
        return this.party;
    }

    // getter zwracający "prawdziwy" numer kandydata na liście, może on
    // być zmodyfikowany gdy 2 okręgi zostają połączone w jeden
    public int connectedListNumber() {
        ElectionalDistrict district = byteotion.district(this.districtNumber);
        if (district.connectedTo() < district.number())
            return listNumber;
        return listNumber + byteotion.district(district.connectedTo()).candidatesAmount();
    }

    // getter zwracający ilość głosów kandydata
    public int amountOfVotes() {
        return amountOfVotes;
    }
}
