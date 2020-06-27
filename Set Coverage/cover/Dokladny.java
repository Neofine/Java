package cover;
import java.util.ArrayList;

// wykonuje algorytm dokładny
public class Dokladny {
    // tablica którą będę próbował pokryć algorytmem
    private static boolean[] tab;
    // do której liczby naturalnej chcemy pokryć ten zbiór
    private int doKtórejLiczby;
    // ile teraz elementów z tablicy pokryłem w mojej rekurencji
    private int posiadanychElementów = 0;
    // wynik algorytmu, jest nullem jeżeli wynik nie istnieje
    private ArrayList<Integer> wynik;
    // ile rodzina ma zbiorów
    private int liczbaZbiorów;
    // rodzina zbiorów na której operuję
    private Rodzina rodzina;
    // Lista list na której przechowuję (dla i-tej listy)
    // elementy które pokrywa i-ty zbiór w danym momencie rekurencji
    private ArrayList<ArrayList<Integer>> dodaneElementy;

    // konstruktor który jest wywoływany przez main żeby znaleść odpowiedź na zadanie
    public Dokladny(int doKtórejLiczby, Rodzina rodzina) {
        this.tab = new boolean[doKtórejLiczby+1];
        this.rodzina = rodzina;
        this.doKtórejLiczby = doKtórejLiczby;
        this.wynik = new ArrayList<>();
        this.liczbaZbiorów = rodzina.LiczbaZbiorów();
        this.dodaneElementy = new ArrayList<ArrayList<Integer>>(liczbaZbiorów+1);
        for (int i = 0; i < liczbaZbiorów; i++) {
            dodaneElementy.add(new ArrayList<>());
            this.dodaneElementy.set(i, new ArrayList<Integer>());
        }
        for (int i = 1 ; i <= doKtórejLiczby; i++) {
            this.tab[i] = true;
        }
        Dokladny();
    }

    // getter który zwraca wynik konstruktora, czyli tablicę zbiorów
    // które musimy zająć żeby pokryć zadany zbiór na wejściu lub zwraca nulla
    public ArrayList<Integer> wynik() {
        return this.wynik;
    }

    // wywołuje rekurencje dla kolejnych ilości zbiorów
    // gdyż rozpatruję rekurencje taką że biorę równo 'i' elementów
    // gdzie 'i' to iterator w pentli
    private void Dokladny() {
        for (int i = 1; i <= liczbaZbiorów; i++) {
            if (czySięDa(0, -1, i))
                return;
        }
        wynik = null;
    }

    // rekurencja, zwraca true jeżeli udało się za pomocą 'ileZbiorówBiorę' elementów
    // skonskruować wynik, i jest on w ArrayList'cie wynik
    private boolean czySięDa(int ileMam, int wKtórymJestem, int ileZbiorówBiorę) {
        if (wKtórymJestem != -1)
            uzupełnijZbiorem(wKtórymJestem, dodaneElementy.get(wKtórymJestem));

        if (ileMam < ileZbiorówBiorę) {
            for (int i = wKtórymJestem + 1; i < liczbaZbiorów; i++) {
                wynik.add(i);
                if (czySięDa(ileMam+1, i, ileZbiorówBiorę))
                    return true;
                wynik.remove(wynik.size() - 1 );
            }
        }
        else if (posiadanychElementów == doKtórejLiczby) {
            return true;
        }

        if (wKtórymJestem != -1) {
            for (Integer i: dodaneElementy.get(wKtórymJestem)) {
                tab[i] = true;
                posiadanychElementów--;
            }
            dodaneElementy.get(wKtórymJestem).clear();
        }
        return false;
    }

    // funkcja uzupełnia listę o elementy które dodaje do zbioru
    // jest ona tworzona, żeby po wyjściu z rekurencji na poziomie tego
    // zbioru móc odjąc te liczby od tablicy zapełnienia
    private void uzupełnijZbiorem (int wKtórymJestem, ArrayList<Integer> dodaneElementy) {
        Zbiór zbiór = rodzina.Zbiór(wKtórymJestem);
        for (int j = 1; j <= doKtórejLiczby; j++) {
            if (!tab[j]) continue;

            if (zbiór.czyPosiada(j)) {
                tab[j] = false;
                dodaneElementy.add(j);
                posiadanychElementów++;
            }
        }
    }
}