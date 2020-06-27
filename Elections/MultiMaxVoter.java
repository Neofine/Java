// podklasa maksymalizującego wyborcy, który patrzy na wszystkie partie
public class MultiMaxVoter extends MaxVoter {
    // konstruktor wyborcy
    MultiMaxVoter(Byteotion byteotion, String name, String surname, int districtNumber, int attribute) {
        super(byteotion, name, surname, districtNumber, attribute);
    }

    @Override
    protected int start() {
        return 1;
    }

    @Override
    protected int end() {
        return byteotion.partiesAmount();
    }
}
