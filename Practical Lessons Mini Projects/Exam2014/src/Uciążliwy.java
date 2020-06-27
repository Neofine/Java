public class Uciążliwy extends Turysta{
    Uciążliwy(int cenaMaksymalna, Wymaganie[] wymagania, int ilośćWymagań) {
        super(cenaMaksymalna, wymagania, ilośćWymagań);
    }
    public boolean wybierz(PlanWycieczki wycieczka) {
        for (Wymaganie wymaganie: wymagania) {
            if (!(wymaganie.czyChce() && wycieczka.czyIstnieje(wymaganie.miasto()) ||
                    !wymaganie.czyChce() && !wycieczka.czyIstnieje(wymaganie.miasto())))
                return false;
        }
        return true;
    }
}
