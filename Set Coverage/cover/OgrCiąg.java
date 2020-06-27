package cover;

// jeden ze składników zbioru, jest to ciąg ograniczony przez liczbę
public class OgrCiąg extends Składnik{
    // wartość początkowa ciągu
    private int wartośćPoczątkowa;
    // wartość której ciąg nie może przekroczyć
    private int ograniczenie;
    // co ile przyrasta ciąg w kolejnych elementach
    private int przyrost;

    OgrCiąg(int wartośćPoczątkowa, int przyrost, int ograniczenie) {
        this.wartośćPoczątkowa = wartośćPoczątkowa;
        this.przyrost = przyrost;
        this.ograniczenie = ograniczenie;
    }

    // funkcja która sprawdza czy zadana wartość istnieje w tym ciągu
    boolean czyIstnieje(int wartość) {
        return (wartość >=  wartośćPoczątkowa &&
                wartość <= ograniczenie &&
                (wartość - wartośćPoczątkowa) % przyrost == 0);
    }
}
