// Klasa procesująca 2 zapytania o dodanie rzeczy do Bajtocji
public class Processing {
    // dodaje kandydata do Bajtocji
    public void addCandidate(Byteotion byteotion, String[] candidateLine) {
        String name = candidateLine[0];
        String surname = candidateLine[1];
        int districtNr = Integer.parseInt(candidateLine[2]);
        String partyName = candidateLine[3];
        int listNr = Integer.parseInt(candidateLine[4]);

        int partyNr = byteotion.whichHasThisName(partyName);
        Candidate candidate = new Candidate(byteotion, name, surname, districtNr, partyNr, listNr);

        for (int i = 1; i <= byteotion.operations().attributeAmount(); i++) {
            candidate.addAttribute(Integer.parseInt(candidateLine[4 + i]));
        }

        ElectionalDistrict district = byteotion.district(districtNr);

        district.addCandidate(candidate);
    }

    // dodaje wyborcę do bajtocji
    public void addVoter(Byteotion byteotion, String[] voterLine) {
        String name = voterLine[0];
        String surname = voterLine[1];
        int districtNr = Integer.parseInt(voterLine[2]);
        int voterType = Integer.parseInt(voterLine[3]);

        ElectionalDistrict district = byteotion.district(districtNr);

        if (voterType == 1 || voterType == 2) {
            String partyName = voterLine[4];
            int partyNr = byteotion.whichHasThisName(partyName);
            if (voterType == 1) {
                district.addVoter(new RandomVoter(byteotion, name, surname, districtNr, partyNr));
            }
            else {
                int candidateNr = Integer.parseInt(voterLine[5]);

                //jeżeli chcę zagłosować na kandydata mniejszego dystryktu ale on jest połączony z większym
                //to zmieniam nr kandydata żeby pasował do tablicy
                if (byteotion.district(districtNr).connectedTo() > districtNr)
                    candidateNr += byteotion.district(byteotion.district(districtNr).connectedTo()).candidatesAmount();

                district.addVoter(new StrictVoter(byteotion, name, surname, districtNr, partyNr, candidateNr));
            }
        }
        else if (voterType == 3 || voterType == 4 || voterType == 6 || voterType == 7) {
            int whatAttribute = Integer.parseInt(voterLine[4]);
            if (voterType == 3) {
                district.addVoter(new MultiMinVoter(byteotion, name, surname, districtNr, whatAttribute));
            }
            else if (voterType == 4) {
                district.addVoter(new MultiMaxVoter(byteotion, name, surname, districtNr, whatAttribute));
            }
            else {
                String partyName = voterLine[5];
                int partyNr = byteotion.whichHasThisName(partyName);
                if (voterType == 6) {
                    district.addVoter(new SingleMinVoter(byteotion, name, surname, districtNr, whatAttribute, partyNr));
                }
                else {
                    district.addVoter(new SingleMaxVoter(byteotion, name, surname, districtNr, whatAttribute, partyNr));
                }
            }
        }
        else {
            int[] wages = new int[byteotion.operations().attributeAmount() + 1];
            for (int i = 1; i <= byteotion.operations().attributeAmount(); i++) {
                wages[i] = Integer.parseInt(voterLine[3 + i]);
            }
            if (voterType == 5) {
                district.addVoter(new MultiWagesVoter(byteotion, name, surname, districtNr, wages));
            }
            else {
                String partyName = voterLine[4 + byteotion.operations().attributeAmount()];
                int partyNr = byteotion.whichHasThisName(partyName);
                district.addVoter(new SingleWagesVoter(byteotion, name, surname, districtNr, wages, partyNr));
            }
        }
    }
}
