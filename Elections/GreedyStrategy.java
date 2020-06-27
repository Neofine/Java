import java.util.ArrayList;
// strategia wyborcza polegająca na wyborze działania wyborczego
// które w największym stopniu zwiększy sumę sum ważonych u
// swoich kandydatów
public class GreedyStrategy extends Strategy {
    public void makeCampaign(Byteotion byteotion, int[][] vectors, int d, int partyNr) {
        Party party = byteotion.party(partyNr);
        int profitCoef = -(int) 10e8, bestVector = 0;
        ElectionalDistrict bestDistrict = null;
        // tablica sum wag danych cech u kandydatów
        int[][] tab = new int[byteotion.districtsAmount() + 1][byteotion.operations().attributeAmount() + 1];

        // preprocessuję sumę wag danej cechy u kandydatów
        for (int dis = 1; dis <= byteotion.districtsAmount(); dis++) {
            ElectionalDistrict district = byteotion.district(dis);
            ArrayList<Candidate> candidates = district.candidatePartyList(partyNr);
            for (Candidate candidate: candidates) {
                for (int att = 1; att <= byteotion.operations().attributeAmount(); att++)
                    tab[dis][att] += candidate.certainAttribute(att);
            }
        }

        // dla każdego dystryktu
        for (int dis = 1; dis <= byteotion.districtsAmount(); dis++) {
            ElectionalDistrict district = byteotion.district(dis);
            // dla każdego wektora
            for (int vec = 1; vec <= d; vec++) {
                int vecCost = vecCost(byteotion, vectors, vec);
                // jeżeli mnie stać
                if (vecCost * district.combinedVotersAm() <= party.budget()) {
                    int nowProfit = 0;
                    // liczę sumę sum ważonych u wyborców z nałożonym wektorem
                    for (WagesVoter wagesVoter: district.weightedVoter()) {
                        int[] wages = wagesVoter.wages();
                        for (int att = 1; att <= byteotion.operations().attributeAmount(); att++)
                            nowProfit += regulateWage(wages[att], vectors[vec][att]) * tab[dis][att];
                    }
                    // jeżeli suma sum ważonych jest większa od poprzedniej to ją biorę
                    if (profitCoef < nowProfit) {
                        profitCoef = nowProfit;
                        bestVector = vec;
                        bestDistrict = district;
                    }
                }
            }
        }

        applyVector(byteotion, vectors, bestDistrict, bestVector, party);
    }

    // reguluję zmianę wag u wyborców, żeby nie przekroczyła zakresu [-100, 100]
    private int regulateWage(int wage, int howMuchAdd) {
        if (wage + howMuchAdd > 100)
            return 100;
        else if (wage + howMuchAdd < -100)
            return -100;
        return wage + howMuchAdd;
    }
}
