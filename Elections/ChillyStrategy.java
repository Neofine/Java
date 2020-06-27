// Strategia polega na wyborze jak najbliższego indeksowo wektora
// i nałożenie go na najbliższy indeksowo okrąg
// Zdecydowanie strateg partii jest leniwy w tym przypadku
public class ChillyStrategy extends Strategy {
    public void makeCampaign(Byteotion byteotion, int[][] vectors, int d, int partyNr) {
        Party party = byteotion.party(partyNr);
        // dla każdego wektora
        for (int i = 1; i <= d; i++) {
            int vectorCost = vecCost(byteotion, vectors, i);
            // dla kazdego dystryktu
            for (int k = 1; k <= byteotion.districtsAmount(); k++) {
                ElectionalDistrict district = byteotion.district(k);
                if (party.budget() >= vectorCost * district.combinedVotersAm()) {
                    // biorę pierwszy działający wektor na pierwszym działającym okręgu
                    applyVector(byteotion, vectors, district, i, party);
                }
            }
        }
    }
}
