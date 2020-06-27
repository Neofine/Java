package cover;

import java.util.ArrayList;

// klasa rodziny, posiada ArrayListę zbiorów
public class Rodzina {
    // na którym teraz zbiorze operuję,
    // czyli do którego zbioru ewentualnie dodaję składniki
    private int nrZbioruAktualnego = 0;

    // ArrayLista zbiorów które posiadam w rodzinie
    private ArrayList<Zbiór> zbiory = new ArrayList<>();

    // konstruktor rodziny
    public Rodzina() {
        zbiory.add(new Zbiór());
    }

    // getter zwracający specyficzny zbiór
    public Zbiór Zbiór(int nrZbioru) {
        return this.zbiory.get(nrZbioru);
    }

    // getter zwracający zbiór na którym teraz klasa Procesowanie operuje
    public Zbiór OstatniZbiór(){
        return this.zbiory.get(nrZbioruAktualnego);
    }

    // getter zwracający ilość zbiorów które posiadamy w rodzinie
    public int LiczbaZbiorów() {
        return this.nrZbioruAktualnego;
    }

    // kończy modyfikację danego zbioru, tworzy nowy zbiór w rodzinie
    public void zakonczZbior() {
        this.nrZbioruAktualnego++;
        this.zbiory.add(new Zbiór());
    }
}
