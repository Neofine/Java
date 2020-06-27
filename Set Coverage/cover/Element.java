package cover;

// jeden ze składników zbioru, jest to pojedynczy element
public class Element extends Składnik{
    // wartość tego elementu
    private int wartość;
    Element(int wartość) {
        this.wartość = wartość;
    }

    // funkcja która sprawdza czy zadana wartość jest równa wartości elementu
    boolean czyIstnieje(int wartość) {
        return wartość == this.wartość;
    }
}
