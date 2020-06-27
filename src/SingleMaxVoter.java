// podklasa maksymalizującego wyborcy, który patrzy na jedną partię
public class SingleMaxVoter extends MaxVoter {
    // partia na którą patrzy wyborca
    private final int party;

    // konstruktor wyborcy
    SingleMaxVoter(Byteotion byteotion, String name, String surname, int districtNumber, int attribute, int party) {
        super(byteotion, name, surname, districtNumber, attribute);
        this.party = party;
    }

    @Override
    protected int start() {
        return party;
    }

    @Override
    protected int end() {
        return party;
    }
}
