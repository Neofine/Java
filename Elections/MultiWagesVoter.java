// podklasa "ważonego" wyborcy, który patrzy na wszystkie partie
public class MultiWagesVoter extends WagesVoter {
    // konstruktor ważonego wielopartyjnego wyborcy
    MultiWagesVoter(Byteotion byteotion, String name, String surname, int districtNumber, int[] wages) {
        super(byteotion, name, surname, districtNumber, wages);
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
