// podklasa "ważonego" wyborcy, który patrzy tylko na jedną partię
public class SingleWagesVoter extends WagesVoter {
    // partia z której wyborca wybiera
    private final int party;

    // konstruktor ważonego jednopartyjnego wyborcy
    SingleWagesVoter(Byteotion byteotion, String name, String surname, int districtNumber, int[] wages, int party) {
        super(byteotion, name, surname, districtNumber, wages);
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
