import java.util.Random;

// klasa wyborców którzy jakkolwiek patrzą na wagi przy doborze kandydata
public abstract class PickyVoter extends Voter {

    // konstruktor "wybierającego" wyborcy
    PickyVoter(Byteotion byteotion, String name, String surname, int districtNumber) {
        super(byteotion, name, surname, districtNumber);
    }

    @Override
    protected Candidate chooseCandidate() {
        ElectionalDistrict district = byteotion.district(districtNumber);
        int maxSeen = baseWorstScore();
        Candidate candidate = null;
        Random r = new Random();
        // start i end to jak wyborca wybiera z jednej lub wszystkich partii
        for (int i = start(); i <= end(); i++) {
            for (int j = 1; j <= district.combinedCandAm(); j++) {
                Candidate tmpCandidate = district.candidate(i, j);
                int scoreNow = countScore(tmpCandidate);
                if (isScoreBetter(scoreNow, maxSeen) || (scoreNow == maxSeen && tossACoin(r))) {
                    maxSeen = scoreNow;
                    candidate = tmpCandidate;
                }
            }
        }
        return candidate;
    }

    // jak kandydaci mają tak samo dobry wynik w oczach wyborcy
    // to rzucam monetą którego wziąć
    private boolean tossACoin(Random r) {
        return r.nextInt(2) == 1;
    }

    // domyślny najgorszy wynik, żeby móc do jakiegoś porównywać wyniki kandydatów
    protected abstract int baseWorstScore();

    // start i end oznaczają od której partii zaczynam i w której kończę
    // są ustawione albo na
    // (1, wszystkie_partie) albo (specyficzna_partia, specyficzna_partia)
    // zależy to od preferencji wyborcy
    protected abstract int start();
    protected abstract int end();

    // liczy wynik kandydata w oczach wyborcy
    protected abstract int countScore(Candidate candidate);

    // sprawdza czy dany wynik jest lepszy od najlepszego który wyborca widział
    protected abstract boolean isScoreBetter(int scoreNow, int bestYet);

    // sprawdza czy wyborca jest "ważony" czy nie
    public boolean isWeighted() {
        return false;
    }
}
