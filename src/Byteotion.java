// Klasa zawierająca Bajtocję, wszystkie jej okręgi
public class Byteotion {
    // ilość partii w Bajtocji
    private final int partiesAmount;
    // ilość okręgów w Bajtocji
    private final int districtsAmount;
    // tablica okręgów w Bajtocji
    private final ElectionalDistrict[] districts;
    // tablica partii w Bajtocji
    private final Party[] parties;
    // licznik przy pomocy którego dodaję okręgi na tablicę w Bajtocji
    private int distrCnt = 0;
    // licznik przy pomocy którego dodaję partie na tablicę w Bajtocji
    private int ptyCnt = 0;
    // dostępne działania wyborcze partii
    private final ElectionOperations operations;

    // konstruktor Bajtocji
    Byteotion(int districtsAmount, int partiesAmount, int vectorsAmout, int attributeAmount) {
        this.partiesAmount = partiesAmount;
        this.districtsAmount = districtsAmount;
        operations = new ElectionOperations(attributeAmount, vectorsAmout);
        this.districts = new ElectionalDistrict[districtsAmount + 1];
        this.parties = new Party[partiesAmount + 1];
    }

    // settter dodający nowy dystrykt do Bajtocji
    public void addDistrict(ElectionalDistrict district) {
        this.districts[++distrCnt] = district;
    }

    // setter dodający nową partię do Bajtocji
    public void addParty(Party party) {
        this.parties[++ptyCnt] = party;
    }

    // getter zwracający specyficzny dystrykt w Bajtocji
    public ElectionalDistrict district(int which) {
        return districts[which];
    }

    // getter zwracający specyficzną partię w Bajtocji
    public Party party(int which) {
        return parties[which];
    }

    // getter zwracający dostępne operacje wyborcze w bajtocji
    public ElectionOperations operations() {
        return operations;
    }

    // getter zwracający ilość partii w Bajtocji
    public int partiesAmount() {
        return partiesAmount;
    }

    // getter zwracający liczbę okręgów w Bajtocji
    public int districtsAmount() {
        return districtsAmount;
    }

    // sprawdza który numer ma partia która ma podaną nazwę
    public int whichHasThisName(String name) {
        for (int i = 1; i <= partiesAmount; i++) {
            if (name.equals(parties[i].name())) {
                return i;
            }
        }
        return -1;
    }
}
