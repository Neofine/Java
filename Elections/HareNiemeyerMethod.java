// klasa zawierająca metodę Hare'a - Niemeyera
public class HareNiemeyerMethod extends CountingMethod {
    public int[] giveMandates(Byteotion byteotion, int[] votesNow, int district) {
        int mandatesLeft = byteotion.district(district).combinedCandAm();
        int[] mandates = new int[byteotion.partiesAmount() + 1];
        double[] quotient = new double[byteotion.partiesAmount() + 1];

        for (int i = 1; i <= byteotion.partiesAmount(); i++) {
            // kieruję się takim sposobem podziału "współczynnika":
            // quotient(współczynnik)[i] = glosy_na_partie * liczba_mandatow_do_wydania / liczba_glosujacych_ludzi;
            // a wiemy, że liczba mandatów jest równa liczbie kandydatów z danej partii a ta jest równa
            // liczba_glosujacych_ludzi / 10.0 więc skróciłem tak ten wzór
            quotient[i] = votesNow[i] / 10.0;
            mandates[i] = (int) quotient[i];

            quotient[i] -= mandates[i];
            mandatesLeft -= mandates[i];
        }

        // nie chcę ciągle tej samej partii pozostałe mandaty wręczać
        // a pozostała ilość mandatów do wydania nie będzie większa
        // od liczby partii
        boolean[] alreadyChosen = new boolean[byteotion.partiesAmount() + 1];

        // dopóki zostały mandaty do wręczenia
        while(mandatesLeft != 0) {
            double maxRest = -1.0;
            int whatParty = -1;

            for (int i = 1; i <= byteotion.partiesAmount(); i++) {
                if (!alreadyChosen[i] && quotient[i] > maxRest ||
                   (!alreadyChosen[i] && quotient[i] == maxRest && tossACoin(r))) {
                    maxRest = quotient[i];
                    whatParty = i;
                }
            }

            mandatesLeft--;
            mandates[whatParty]++;
            alreadyChosen[whatParty] = true;
        }
        return mandates;
    }
}
