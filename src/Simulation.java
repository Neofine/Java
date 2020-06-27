import java.util.ArrayList;

// klasa odpowiedzialna za symulację wyborów oraz "reset" Bajtocji
public class Simulation {
    // symuluje wybory dla danej metody
    public void simulateElection(Byteotion byteotion, CountingMethod method) {
        // wykonuje działania wyborcze
        for (int par = 1; par <= byteotion.partiesAmount(); par++) {
            Party party = byteotion.party(par);
            Strategy strategy;
            if (party.strategy() == 'Z') {
                strategy = new GreedyStrategy();
            }
            else if (party.strategy() == 'R') {
                strategy = new ExtravagantStrategy();
            }
            else if (party.strategy() == 'S') {
                strategy = new HumbleStrategy();
            }
            else {
                strategy = new ChillyStrategy();
            }
            int prevBudget = -1;
            // dopóki budżet partii się zmienia to wykonuje działania wyborcze
            while (prevBudget != party.budget()) {
                prevBudget = party.budget();
                strategy.makeCampaign(byteotion, byteotion.operations().vectors(),
                                      byteotion.operations().vectorsAmount(), par);
            }
        }

        // sumaryczna ilość mandatów jakie partie podostawały w Bajtocji
        int[] sumOfMandates = new int[byteotion.partiesAmount() + 1];

        for (int i = 1; i <= byteotion.districtsAmount(); i++) {
            ElectionalDistrict district = byteotion.district(i);
            System.out.print("OKRĄG WYBORCZY NR: ");
            String districtNr;
            if (district.connectedTo() > i) {
                districtNr = "(" + i + ", " + (i+1) + ")";
                System.out.println(districtNr);
                i++;
            }
            else {
                districtNr = Integer.toString(i);
                System.out.println(districtNr);
            }
            ArrayList<Voter> voters = district.voter();
            System.out.println("GŁOSUJĄCY:");
            for (int j = 1; j <= voters.size(); j++) {
                Voter voter = voters.get(j - 1);
                Candidate candidate = voter.chooseCandidate();
                candidate.addVote();
                System.out.println(voter.nameSurname() + " zagłosował(a) na " + candidate.nameSurname());
            }
            System.out.println("KANDYDACI:");
            ArrayList<ArrayList<Candidate>> candidate = district.candidateList();
            for (ArrayList<Candidate> candidates : candidate) {
                for (Candidate cand : candidates) {
                    System.out.println(cand.nameSurname() + " z partii " + cand.party() + " nr." +
                            cand.connectedListNumber() + " - " + cand.amountOfVotes() + " głosów");
                }
            }
            System.out.println("MANDATY OKRĘGU WYBORCZEGO NR " + districtNr + ":");
            int[] mandates = method.countMandates(byteotion, i);
            for (int par = 1; par <= byteotion.partiesAmount(); par++) {
                Party party = byteotion.party(par);
                System.out.println(party.name() + " dostała " + mandates[par] + " mandatów");
                sumOfMandates[par] += mandates[par];
            }
        }
        System.out.println("SUMARYCZNE MANDATY W BAJTOCJI");
        for (int par = 1; par <= byteotion.partiesAmount(); par++) {
            Party party = byteotion.party(par);
            System.out.println(party.name() + " dostała " + sumOfMandates[par] + " mandatów");
            sumOfMandates[par] += sumOfMandates[par];
        }
        resetByteotion(byteotion);
    }

    // przywraca Bajtocje do stanu początkowego
    public void resetByteotion(Byteotion byteotion) {
        for (int par = 1; par <= byteotion.partiesAmount(); par++) {
            Party party = byteotion.party(par);
            party.resetBudget();
        }

        for (int i = 1; i <= byteotion.districtsAmount(); i++) {
            ElectionalDistrict district = byteotion.district(i);
            ArrayList<ArrayList<Candidate>> candidate = district.candidateList();
            for (ArrayList<Candidate> candidates : candidate) {
                for (Candidate cand : candidates) {
                    cand.resetVotes();
                }
            }
            ArrayList<WagesVoter> weightedVoters = district.weightedVoter();
            for (WagesVoter voter: weightedVoters) {
                voter.resetWages();
            }
        }
    }
}
