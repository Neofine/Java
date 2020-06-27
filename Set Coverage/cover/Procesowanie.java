package cover;

import java.util.ArrayList;
import java.util.Scanner;

// klasa odpowiedzialna za rozszyfrowanie wejścia
public class Procesowanie{
    // skaner którego używam do wczytywania wejścia
    private Scanner scan = new Scanner(System.in);
    // wejście które jest odczytane przez klasę Czytanie
    private ArrayList<Integer> wejście = new ArrayList<>();
    // iterator którego używam do iterowania się po ArrayLiście wejścia
    private int iterator = 0;
    // rozmiar ArrayListy
    private int rozmiar;
    // rodzina na której operuję
    private Rodzina rodzina;

    Procesowanie(Rodzina rodzina) {
        this.rodzina = rodzina;
        Czytanie.czytaj(scan, wejście);
        this.rozmiar = wejście.size();
        while(iterator < rozmiar) {
            iteracja();
        }
    }

    // od tego rozpoczynam kolejną iterację czytania
    void iteracja() {
        int pierwsza = wejście.get(iterator++);
        if (pierwsza < 0) {
            int druga = wejście.get(iterator++);
            Main.zapytanie(-pierwsza, druga, rodzina);
        }
        else if (pierwsza == 0) {
            rodzina.zakonczZbior();
        }
        else {
            pierwszaDodatnia(pierwsza);
        }
    }

    // funkcja która jest użyta gdy napotkam dodatnią liczbę na wejściu
    void pierwszaDodatnia(int pierwsza) {
        int druga = wejście.get(iterator++);
        if (druga > 0) {
            rodzina.OstatniZbiór().dodajElement(pierwsza);
            pierwszaDodatnia(druga);
        }
        else if (druga == 0) {
            rodzina.OstatniZbiór().dodajElement(pierwsza);
            rodzina.zakonczZbior();
        }
        else {
            int trzecia = wejście.get(iterator++);
            if (trzecia > 0) {
                rodzina.OstatniZbiór().dodajNieskonczonyCiag(pierwsza, -druga);
                pierwszaDodatnia(trzecia);
            }
            else if (trzecia == 0) {
                rodzina.OstatniZbiór().dodajNieskonczonyCiag(pierwsza, -druga);
                rodzina.zakonczZbior();
            }
            else {
                rodzina.OstatniZbiór().dodajOgraniczonyCiag(pierwsza, -druga, -trzecia);
            }
        }
    }
}
