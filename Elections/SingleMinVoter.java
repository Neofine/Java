// podklasa minimalizującego wyborcy, który patrzy na jedną partię
public class SingleMinVoter extends MinVoter {
    // partia z której wyborca wybiera
    private final int party;

    // konstruktor wyborcy
    SingleMinVoter(Byteotion byteotion, String name, String surname, int districtNumber, int attribute, int party) {
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
