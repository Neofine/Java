// klasa wyborcy minimalizującego po wybranym atrybucie
public abstract class MinVoter extends PickyVoter {
    // numer atrybutu który minimalizuje
    private final int attribute;

    // konstruktor wyborcy
    MinVoter(Byteotion byteotion, String name, String surname, int districtNumber, int attribute) {
        super(byteotion, name, surname, districtNumber);
        this.attribute = attribute;
    }

    @Override
    protected int baseWorstScore() {
        return 101;
    }

    @Override
    protected int countScore(Candidate candidate) {
        return candidate.certainAttribute(attribute);
    }

    @Override
    protected boolean isScoreBetter(int scoreNow, int bestYet) {
        return scoreNow < bestYet;
    }
}
