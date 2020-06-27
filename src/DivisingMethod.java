// klasa w której rozdaję mandaty przez dzielenie liczby głosów
// partii przez pewne liczby naturalne
public abstract class DivisingMethod extends CountingMethod {
    public int[] giveMandates(Byteotion byteotion, int[] votesNow, int district) {
        int mandatesLeft = byteotion.district(district).combinedCandAm();
        int[] mandates = new int[byteotion.partiesAmount() + 1];
        // przez jakie liczby naturalne będę dzielił głosy danej partii
        // będę do tego dodawał 1 żeby już nie przestawiać całej tablicy na jedynki
        int[] divisingBy = new int[byteotion.partiesAmount() + 1];

        // dopóki mam mandaty
        while(mandatesLeft != 0) {
            double maxVotes = -1.0;
            int whatParty = -1;

            // wybieram partię której współczynnik (mandaty / (dzielnik + 1)) jest największy
            for (int i = 1; i <= byteotion.partiesAmount(); i++) {
                if ((double)votesNow[i] / (divisingBy[i] + 1) > maxVotes ||
                   ((double)votesNow[i] / (divisingBy[i] + 1) == maxVotes && tossACoin(r))) {
                    maxVotes = (double)votesNow[i] / (divisingBy[i] + 1);
                    whatParty = i;
                }
            }
            mandatesLeft--;
            mandates[whatParty]++;
            // zwiększam o 1 lub 2 w zależności od wybranej metody
            divisingBy[whatParty] = increase(divisingBy[whatParty]);
        }
        return mandates;
    }

    // funkcja zwracająca wartość what + 1 albo what + 2, w zależności
    // od wybranej metody liczenia głosów
    public abstract int increase(int what);
}
