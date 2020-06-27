import java.util.Random;

// klasa wyborcy, który jest "Żelaznym elektoratem partyjnym", czyli
// głosuje na losowego kandydata na liście partii
public class RandomVoter extends Voter {
    // numer partii na której kandydata będzie głosował
    private final int party;

    // konstruktor losowego wyborcy
    RandomVoter(Byteotion byteotion, String name, String surname, int districtNumber, int party) {
        super(byteotion, name, surname, districtNumber);
        this.party = party;
    }

    @Override
    protected Candidate chooseCandidate() {
        ElectionalDistrict district = byteotion.district(districtNumber);
        Random r = new Random();
        // nie muszę dodawać warunków jeżeli dystrykt jest połączony gdyż
        // district.combinedCandAm() to liczba kandydatów w połączonym już dystrykcie
        return district.candidate(party, r.nextInt(district.combinedCandAm()) + 1);
    }

    @Override
    protected boolean isWeighted() {
        return false;
    }
}
