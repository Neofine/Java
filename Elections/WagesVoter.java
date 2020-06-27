import java.util.Arrays;

// klasa "ważonego" wyborcy, czyli tego, który przy wyborze patrzy
// na sumę ważoną wyborcy
public abstract class WagesVoter extends PickyVoter {
    // początkowa waga wyborcy, sprzed kampanii
    private final int[] wagesStart;
    // wagi wyborcy które mogły być już poddane modyfikacji dzięki kampaniom
    private int[] wages;

    // konstruktor ważonego wyborcy
    WagesVoter(Byteotion byteotion, String name, String surname, int districtNumber, int[] wages) {
        super(byteotion, name, surname, districtNumber);
        this.wages = Arrays.copyOf(wages, wages.length + 1);
        this.wagesStart = Arrays.copyOf(wages, wages.length + 1);
    }

    @Override
    protected int baseWorstScore() {
        return -10000001;
    }

    @Override
    protected int countScore(Candidate candidate) {
        int score = 0;

        for (int k = 1; k <= byteotion.operations().attributeAmount(); k++) {
            score += candidate.certainAttribute(k) * wages[k];
        }

        return score;
    }

    @Override
    protected boolean isScoreBetter(int scoreNow, int bestYet) {
        return scoreNow > bestYet;
    }

    @Override
    public boolean isWeighted() {
        return true;
    }

    // getter zwracający tablicę wag wyborcy
    protected int[] wages() {
        return wages;
    }

    // setter modyfikujący wagę specyficznej cechy
    protected  void modifyWeight(int what, int howMuch) {
        wages[what] += howMuch;
        if (wages[what] > 100)
            wages[what] = 100;
        else if (wages[what] < -100)
            wages[what] = -100;
    }

    // resetuje tablicę wag@
    public void resetWages() {
        this.wages = Arrays.copyOf(wagesStart, wagesStart.length + 1);
    }
}
