import java.util.ArrayList;

// klasa okręgu w bajtocji
public class ElectionalDistrict {
    // numer okręgu
    private final int number;
    // ilość wyborców w okręgu
    private final int votersAmount;
    // ilość kandydatów (jednej partii) w okręgu
    private final int candidatesAmount;
    // sumaryczna ilość wyborców w okręgu, może się ona zmienić, gdy 2 okręgi
    // zostaną połączone
    private int combinedCandAm;
    // sumaryczna ilość kandydatów (jednej partii) w okręgu,
    // może się ona zmienić, gdy 2 okręgi zostaną połączone
    private int combinedVotersAm;
    // numer okręgu z którym ten okrąg został połączony
    // domyślnie ustawione na numer jego samego
    private int connectedTo;
    // lista list kandydatów, dla każdej partii jest osobna lista
    private ArrayList<ArrayList<Candidate>> candidate;
    // lista wszystkich wyborców w okręgu
    private ArrayList<Voter> voter;
    // lista wyborców, których wagi się zmieniają przez kampanie wyborcze
    private ArrayList<WagesVoter> weightedVoter;
    // Bajtocja, czyli "kraj" w którym ten okrąg istnieje
    private final Byteotion byteotion;

    // konstruktor okręgu
    ElectionalDistrict(Byteotion byteotion, int number, int votersAmount) {
        this.byteotion = byteotion;
        this.number = number;
        this.votersAmount = votersAmount;
        this.combinedVotersAm = votersAmount;
        this.candidatesAmount = votersAmount/10;
        this.voter = new ArrayList<>();
        this.connectedTo = number;
        this.candidate = new ArrayList<>(byteotion.partiesAmount() + 1);
        this.weightedVoter = new ArrayList<>();
        this.combinedCandAm = candidatesAmount;
        for (int i = 0; i < byteotion.partiesAmount(); i++) {
            this.candidate.add(new ArrayList<>());
            this.candidate.set(i, new ArrayList<>());
        }
    }

    // setter dodający nowego kandydata na odpowiednią listę
    public void addCandidate(Candidate candidate) {
        this.candidate.get(candidate.party() - 1).add(candidate);
    }

    // setter dodający nowego wyborcę na listę wyborców i ewentualnie
    // jeżeli jest takim to na listę ważonych wyborców
    public void addVoter(Voter voter) {
        this.voter.add(voter);
        if (voter.isWeighted())
            weightedVoter.add((WagesVoter) voter);
    }

    // getter zwracający specyficznego kandydata
    public Candidate candidate(int party, int candNumber) {
        return this.candidate.get(party - 1).get(candNumber - 1);
    }

    // getter zwracający listę kandydatów danej partii
    public ArrayList<Candidate> candidatePartyList(int which) {
        return candidate.get(which - 1);
    }

    // getter zwracający listę list kandydatów
    public ArrayList<ArrayList<Candidate>> candidateList() {
        return candidate;
    }

    // getter zwracający do którego okręgu jest ten okrąg połączony
    public int connectedTo() {
        return this.connectedTo;
    }

    // getter zwracający numer tego okręgu
    public int number() {
        return this.number;
    }

    // getter zwracający liczbę kandydatów (jednej partii) w okręgu
    public int candidatesAmount() {
        return this.candidatesAmount;
    }

    // getter zwracający liczbę kandydatów (jednej partii) w okręgu
    // z poprawką na ewentualne połączenie z innym okręgiem
    public int combinedCandAm() {
        return this.combinedCandAm;
    }

    // getter zwracający liczbę wyborców w okręgu
    // z poprawką na ewentualne połączenie z innym okręgiem
    public int combinedVotersAm() {
        return combinedVotersAm;
    }

    // getter zwracający listę wszystkich wyborców tego okręgu
    public ArrayList<Voter> voter() {
        return voter;
    }

    // getter zwracający listę wszystkich ważonych wyborców z tego okręgu
    public ArrayList<WagesVoter> weightedVoter() {
        return weightedVoter;
    }

    // łączy 2 okręgi ze sobą, łączy ich liczbę kandydatów, wyborców i ich listy
    public void connectTo(ElectionalDistrict district) {
        this.connectedTo = district.number;
        district.connectedTo = this.number;

        this.combinedCandAm += district.candidatesAmount;
        district.combinedCandAm = this.combinedCandAm;

        this.combinedVotersAm += district.votersAmount;
        district.combinedVotersAm = this.combinedVotersAm;

        if (this.number < district.number) {
            district.weightedVoter.addAll(this.weightedVoter);
            this.weightedVoter = district.weightedVoter;

            district.voter.addAll(this.voter);
            this.voter = district.voter;
            for (int i = 0; i < byteotion.partiesAmount(); i++) {
                district.candidate.get(i).addAll(this.candidate.get(i));
            }
            this.candidate = district.candidate;
        }
        else {
            this.weightedVoter.addAll(district.weightedVoter);
            district.weightedVoter = this.weightedVoter;

            this.voter.addAll(district.voter);
            district.voter = this.voter;
            for (int i = 0; i < byteotion.partiesAmount(); i++) {
                this.candidate.get(i).addAll(district.candidate.get(i));
            }
            district.candidate = this.candidate;
        }
    }
}
