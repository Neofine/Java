// strategia wyborcza polegająca na wyborze najtańszego działania wyborczego
public class HumbleStrategy extends Strategy {
    public void makeCampaign(Byteotion byteotion, int[][] vectors, int d, int partyNr) {
        Party party = byteotion.party(partyNr);
        int bestCost = (int) 10e4, bestVector = 0;

        // dla każdego wektora
        for (int i = 1; i <= d; i++) {
            // liczę koszt wektora
            int vectorCost = vecCost(byteotion, vectors, i);
            // wybieram najtańszy wektor
            if (vectorCost < bestCost) {
                bestCost = vectorCost;
                bestVector = i;
            }
        }

        int bestAmountOfPeople = 1001;
        ElectionalDistrict bestDistrict = null;
        for (int i = 1; i <= byteotion.partiesAmount(); i++) {
            ElectionalDistrict district = byteotion.district(i);
            // wybieram najmniej zaludniony dystrykt
            if (district.combinedVotersAm() < bestAmountOfPeople) {
                bestAmountOfPeople = district.combinedVotersAm();
                bestDistrict = district;
            }
        }

        applyVector(byteotion, vectors, bestDistrict, bestVector, party);
    }
}
