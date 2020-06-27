package cover;
import java.util.ArrayList;

// wykonuje algorytm zachłanny
public class Zachłanny {
    // tablica którą będę próbował pokryć algorytmem
    private boolean tab[];
    // wynik algorytmu, jest nullem jeżeli wynik nie istnieje
    private ArrayList<Integer> wynik;
    // rodzina zbiorów na której operuję
    private Rodzina rodzina;
    // liczba zbiorów rodziny
    int liczbaZbiorów;

    // konstruktor który jest wywoływany przez main żeby znaleść odpowiedź na zadanie
    public Zachłanny(int doKtórejLiczby, Rodzina rodzina) {
        this.rodzina = rodzina;
        this.wynik = new ArrayList<>();
        this.liczbaZbiorów = rodzina.LiczbaZbiorów();
        tab = new boolean[doKtórejLiczby+2];
        for (int i = 1 ; i <= doKtórejLiczby; i++) {
            tab[i] = true;
        }
        wynik = Zachłanny(doKtórejLiczby);
    }

    // getter który zwraca wynik konstruktora, czyli tablicę zbiorów
    // które musimy zająć żeby pokryć zadany zbiór na wejściu lub zwraca nulla
    public ArrayList<Integer> wynik() {
        return this.wynik;
    }

    // funkcja która znajduje odpowiedź na zadanie lub zwraca nulla
    // gdy taka nie istnieje
    private ArrayList<Integer> Zachłanny(int doKtórejLiczby) {
        // ile już elementów z tablicy tab posiadam
        int posiadanychElementów = 0;

        while (wynik.size() < liczbaZbiorów && posiadanychElementów < doKtórejLiczby) {
            int rekordDodawania = 0;
            int ktoMaRekord = 0;

            for (int i = 0; i < liczbaZbiorów; i++) {

                Zbiór zbiór = rodzina.Zbiór(i);
                int ileDodaje = 0;

                for (int j = 1; j <= doKtórejLiczby; j++) {
                    if (!tab[j]) continue;

                    if (zbiór.czyPosiada(j)) {
                        ileDodaje++;
                    }
                }

                if (ileDodaje > rekordDodawania) {
                    rekordDodawania = ileDodaje;
                    ktoMaRekord = i;
                }
            }

            if (rekordDodawania == 0)
                break;

            wynik.add(ktoMaRekord);
            Zbiór zbiór = rodzina.Zbiór(ktoMaRekord);

            for (int j = 1; j <= doKtórejLiczby; j++) {
                if (!tab[j]) continue;

                if (zbiór.czyPosiada(j)) {
                    tab[j] = false;
                    posiadanychElementów++;
                }
            }
        }

        if (posiadanychElementów == doKtórejLiczby)
            return wynik;
        return null;
    }
}
