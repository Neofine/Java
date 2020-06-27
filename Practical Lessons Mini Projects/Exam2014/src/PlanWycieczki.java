import java.util.ArrayList;

public class PlanWycieczki {
    private ArrayList<Miasto> miasta;
    private int sumaKosztów;
    PlanWycieczki(ArrayList<Miasto> miasta, int sumaKosztów) {
        this.miasta = miasta;
        this.sumaKosztów = sumaKosztów;
    }
    public boolean czyIstnieje(Miasto miasto) {
        for (Miasto element : miasta) {
            if (miasto == element) {
                return true;
            }
        }
        return false;
    }
    public void usuń(Miasto miasto) {
        miasta.remove(miasto);
    }
    public void dodaj(Miasto miasto) {
        miasta.add(miasto);
    }
    public void podmień(Miasto miastoPierwsze, Miasto miastoDrugie) {
        usuń(miastoPierwsze);
        dodaj(miastoDrugie);
    }
    public int sumaKosztów() {
        return sumaKosztów;
    }
}
