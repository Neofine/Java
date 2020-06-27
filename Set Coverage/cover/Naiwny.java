package cover;
import java.util.ArrayList;

// wykonuje algorytm naiwny
public class Naiwny {
    // tablica którą będę próbował pokryć algorytmem
    private boolean tab[];
    // wynik algorytmu, jest nullem jeżeli wynik nie istnieje
    private ArrayList<Integer> wynik;
    // ile rodzina ma zbiorów
    private int liczbaZbiorów;
    // rodzina zbiorów na której operuję
    private Rodzina rodzina;

    // konstruktor który jest wywoływany przez main żeby znaleźć odpowiedź na zadanie
    public Naiwny(int doKtórejLiczby, Rodzina rodzina) {
        this.rodzina = rodzina;
        this.liczbaZbiorów = rodzina.LiczbaZbiorów();
        wynik = new ArrayList<>();
        tab = new boolean[doKtórejLiczby+2];
        for (int i = 1 ; i <= doKtórejLiczby; i++) {
            tab[i] = true;
        }
        wynik = Naiwny(doKtórejLiczby);
    }

    // getter który zwraca wynik konstruktora, czyli tablicę zbiorów
    // które musimy zająć żeby pokryć zadany zbiór na wejściu lub zwraca nulla
    public ArrayList<Integer> wynik() {
        return this.wynik;
    }

    // funkcja która znajduje odpowiedź na zadanie lub zwraca nulla
    // gdy taka nie istnieje
    private ArrayList<Integer> Naiwny(int doKtórejLiczby) {
        // ile elementów zbioru pokryłem przez dotychczasowe zbiory
        int posiadanychElementów = 0;

        for (int i = 0; i < liczbaZbiorów; i++) {
            Zbiór zbiór = rodzina.Zbiór(i);
            boolean dodaj = false;
            for (int j = 1; j <= doKtórejLiczby; j++) {
                if (!tab[j]) continue;

                if (zbiór.czyPosiada(j)) {
                    dodaj = true;
                    tab[j] = false;
                    posiadanychElementów++;
                }
            }
            if (dodaj)
                wynik.add(i);
        }

        if (posiadanychElementów == doKtórejLiczby)
            return wynik;
        else
            return null;
    }
}
