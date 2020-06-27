import java.util.Random;

// klasa liczenia podziału mandatów wyborczych między partiami
public abstract class CountingMethod {
    protected Random r;

    // rozdaję mandaty między partie
    public int[] countMandates(Byteotion byteotion, int district) {
        r = new Random();
        //tablica glosow na dane partie w dystrykcie
        int[] votesNow = new int[byteotion.partiesAmount() + 1];

        // Zliczam sumaryczne głosy na poszczególne partie w danym dystrykcie
        for (int i = 1; i <= byteotion.partiesAmount(); i++) {
            for (int cand = 1; cand <= byteotion.district(district).combinedCandAm(); cand++) {
                votesNow[i] += byteotion.district(district).candidate(i, cand).amountOfVotes();
            }
        }

        //Rozdaję mandaty
        return giveMandates(byteotion, votesNow, district);
    }

    // funkcja rozdaje mandaty w danym dystrykcie, rozszerzona w podklasach
    protected abstract int[] giveMandates(Byteotion byteotion, int[] votesNow, int district);

    // jak partie mają taki sam wynik w przydzielaniu mandatów to rzucam
    // monetą której partii przydzielę mandat
    protected boolean tossACoin(Random r) {
        return r.nextInt(2) == 1;
    }
}
