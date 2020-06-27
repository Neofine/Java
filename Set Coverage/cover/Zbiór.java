package cover;
import java.util.ArrayList;

// klasa zbiór która posiada składniki
public class Zbiór{
    // ArrayLista składników które posiadam w zbiorze
    private ArrayList<Składnik> składniki = new ArrayList<>();

    // dodawanie elementu do ArrayListy
    public void dodajElement(int element) {
        składniki.add(new Element(element));
    }

    // dodawanie nieskończonego ciągu do ArrayListy
    public void dodajNieskonczonyCiag(int początkowa, int różnica) {
        składniki.add(new NieogrCiąg(początkowa, różnica));
    }

    // dodawanie ograniczonego ciągu do array listy
    public void dodajOgraniczonyCiag(int początkowa, int różnica, int ograniczenie) {
        składniki.add(new OgrCiąg(początkowa, różnica, ograniczenie));
    }

    // sprawdza czy w jakimkolwiek składniku zbioru istnieje podana wartość
    public boolean czyPosiada(int wartość) {
        for (Składnik element: składniki) {
            if (element.czyIstnieje(wartość)) return true;
        }
        return false;
    }
}
