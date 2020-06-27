package cover;

// jeden ze składników zbioru, jest to nieograniczony ciąg
public class NieogrCiąg extends Składnik{
    // wartość początkowa ciągu
    private int wartośćPoczątkowa;
    // co ile przyrasta ciąg w kolejnych elementach
    private int przyrost;

    NieogrCiąg(int wartośćPoczątkowa, int przyrost) {
        this.wartośćPoczątkowa = wartośćPoczątkowa;
        this.przyrost = przyrost;
    }

    // funkcja która sprawdza czy zadana wartość istnieje w tym ciągu
    boolean czyIstnieje(int wartość) {
        return (wartość >= wartośćPoczątkowa &&
                (wartość - wartośćPoczątkowa) % przyrost == 0);
    }
}
