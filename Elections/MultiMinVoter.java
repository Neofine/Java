// podklasa minimalizującego wyborcy, który patrzy na wszystkie partie
public class MultiMinVoter extends MinVoter {
    // konstruktor wyborcy
    MultiMinVoter(Byteotion byteotion, String name, String surname, int districtNumber, int attribute) {
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
