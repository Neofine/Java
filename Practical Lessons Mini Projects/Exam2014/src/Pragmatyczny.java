public class Pragmatyczny extends Turysta{
    Pragmatyczny(int cenaMaksymalna, Wymaganie[] wymagania, int ilośćWymagań) {
        super(cenaMaksymalna, wymagania, ilośćWymagań);
    }
    public boolean wybierz(PlanWycieczki wycieczka) {
        int spełnionych = 0, niespełnionych = 0;
        for (Wymaganie wymaganie: wymagania) {
            if (wymaganie.czyChce() && wycieczka.czyIstnieje(wymaganie.miasto()) ||
                    !wymaganie.czyChce() && !wycieczka.czyIstnieje(wymaganie.miasto()))
                spełnionych++;
            else niespełnionych++;
        }
        if (spełnionych >= niespełnionych)
            return true;
        return false;
    }
}
