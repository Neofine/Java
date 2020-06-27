// strategia wyborcza polegająca na wyborze najdroższego działana
// wyborczego na które partię stać
public class ExtravagantStrategy extends Strategy {
    public void makeCampaign(Byteotion byteotion, int[][] vectors, int d, int partyNr) {
        Party party = byteotion.party(partyNr);
        int bestCost = 0, bestVector = 0;
        ElectionalDistrict bestDistrict = null;

        // dla każdego wektora
        for (int i = 1; i <= d; i++) {
            // liczę je go koszt
            int vectorCost = vecCost(byteotion, vectors, i);
            // dla kazdego dystryktu
            for (int k = 1; k <= byteotion.districtsAmount(); k++) {
                ElectionalDistrict district = byteotion.district(k);
                // jeżeli mnie stać i koszt jest większy
                if (vectorCost * district.combinedVotersAm() > bestCost &&
                        party.budget() >= vectorCost * district.combinedVotersAm()) {
                    bestCost = vectorCost * district.combinedVotersAm();
                    bestVector = i;
                    bestDistrict = district;
                }
            }
        }

        applyVector(byteotion, vectors, bestDistrict, bestVector, party);
    }
}
