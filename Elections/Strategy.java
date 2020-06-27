// klasa strategii wyborczych
public abstract class Strategy {
    //  wykonuje raz działanie wyborcze w pewnym dystrykcie
    abstract void makeCampaign(Byteotion byteotion, int[][] vectors, int d, int partyNr);

    // nakłada wybrany wektor na dystrykt, jeżeli partię na to stać
    // i jeżeli dystrykt istnieje (nie jest nullem)
    protected void applyVector(Byteotion byteotion, int[][] vectors, ElectionalDistrict district, int vector, Party party) {
        if (district == null || vector == 0)
            return;

        int cost = vecCost(byteotion, vectors, vector) * district.combinedVotersAm();
        if (party.budget() < cost)
            return;

        for (WagesVoter voter: district.weightedVoter()) {
            for (int j = 1; j <= byteotion.operations().attributeAmount(); j++) {
                voter.modifyWeight(j, vectors[vector][j]);
            }
        }
        party.spendMoney(cost);
    }

    // liczy koszt wektora
    protected int vecCost(Byteotion byteotion, int[][] vectors, int vector) {
        int cost = 0;
        for (int i = 1; i <= byteotion.operations().attributeAmount(); i++) {
            cost += Math.abs(vectors[vector][i]);
        }
        return cost;
    }
}
