// klasa zawierająca partię
public class Party {
    // budżet partii, zmienia się w raz z działaniami wyborczymi
    private int budget;
    // początkowy budżet partii
    private final int startBudget;
    // nazwa partii
    private final String name;
    // strategia kampanii wyborczej którą partia przyjmuje
    private final char strategy;

    // konstruktor partii
    Party(int budget, String name, char strategy) {
        this.budget = budget;
        this.startBudget = budget;
        this.name = name;
        this.strategy = strategy;
    }

    // getter zwracający aktualny budżet partii
    public int budget() {
        return budget;
    }

    // getter zwracający nazwę partii
    public String name() {
        return name;
    }

    // getter zwracający strategię partii
    public char strategy() {
        return strategy;
    }

    // setter "wydający" pieniądze partii
    public void spendMoney(int howMuch) {
        budget -= howMuch;
    }

    // setter resetujący budżet partii do początkowego
    public void resetBudget() {
        budget = startBudget;
    }
}
