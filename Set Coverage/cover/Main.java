package cover;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        // Rodzina zbiorów do której dodajemy wczytywane zbiory
        Rodzina rodzina = new Rodzina();
        // W klasie procesowanie odbywa się całe wczytywanie i odpowiadanie
        // na zapytania
        new Procesowanie(rodzina);
    }

    // realizuje zadane zapytanie
    public static void zapytanie(int doKtórejLiczby, int nrTechniki, Rodzina rodzina) {
        if (nrTechniki == 1) {
            wypisz(new Dokladny(doKtórejLiczby, rodzina).wynik());
        }
        else if (nrTechniki == 2) {
            wypisz(new Zachłanny(doKtórejLiczby, rodzina).wynik());
        }
        else {
            wypisz(new Naiwny(doKtórejLiczby, rodzina).wynik());
        }
    }

    // wypisuje odpowiedź na zapytanie na out, lub jeżeli nie istnieje
    // to wypisuje 0
    private static void wypisz(ArrayList <Integer> wynik) {
        if (wynik == null)
            System.out.println(0);
        else {
            Collections.sort(wynik);
            boolean licznik = false;
            for (Integer i: wynik) {
                if (licznik)
                    System.out.print(" ");
                licznik = true;
                System.out.print((i + 1));
            }
            System.out.println();
        }
    }
}
