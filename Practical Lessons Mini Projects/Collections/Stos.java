import java.util.ArrayList;

public class Stos {
    ArrayList<Integer> stos;
    Stos() {
        stos = new ArrayList<>();
    }

    void dodaj(int x) {
        stos.add(x);
    }

    int zdjemij() {
        int ans = 0;
        try {
            ans = stos.get(stos.size() - 1);
            stos.remove(ans);
            return ans;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Nie można zdjąć elementu");
        }
        return ans;
    }

    boolean czyPusty() {
        return stos.size() == 0;
    }
}
