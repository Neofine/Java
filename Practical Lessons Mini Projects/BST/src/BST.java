public class BST<W extends Comparable<W>> {
    Wierzchołek poczatek = null;

    void dodaj(W element) {
        if (poczatek == null) {
            poczatek = new Wierzchołek(element);
            return;
        }
        Wierzchołek teraz = poczatek;
        while (teraz != null) {
            if (element.compareTo((W) teraz.wartość) < 0) {
                if (teraz.lewy == null) {
                    teraz.lewy = new Wierzchołek(element);
                }
                teraz = teraz.lewy;
            }
            else if (element.compareTo((W) teraz.wartość) > 0) {
                if (teraz.prawy == null) {
                    teraz.prawy = new Wierzchołek(element);
                }
                teraz = teraz.prawy;
            }
            else {
                return;
            }
        }
    }
    boolean szukaj(W element) {
        Wierzchołek teraz = poczatek;
        while (teraz != null) {
            if (element.compareTo((W) teraz.wartość) < 0) {
                teraz = teraz.lewy;
            }
            else if (element.compareTo((W) teraz.wartość) > 0) {
                teraz = teraz.prawy;
            }
            else {
                return true;
            }
        }
        return false;
    }
}
