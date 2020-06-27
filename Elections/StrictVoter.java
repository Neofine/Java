// klasa wyborcy, który jest "żelaznym elektoratem kandydata"
// czyli głosuje zawsze na specyficznego kandydata
public class StrictVoter extends Voter {
    // partia kandydata na którego zagłosuje
    private final int party;
    // pozycja na liście partii na którego zagłosuje
    private final int position;

    // konstruktor dokładnego wyborcy
    public StrictVoter(Byteotion byteotion, String name, String surname, int districtNumber, int party, int position) {
        super(byteotion, name, surname, districtNumber);
        this.party = party;
        this.position = position;
    }

    @Override
    protected Candidate chooseCandidate() {
        ElectionalDistrict district = byteotion.district(districtNumber);
        // Nie muszę tu uwzględniać ewentualnego połączenia dystryktów gdyż już
        // to zrobiłem w klasie Scanning
        return district.candidate(party,  position);
    }

    @Override
    protected boolean isWeighted() {
        return false;
    }
}
