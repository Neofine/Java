public class Obojętny extends Turysta{
    Obojętny(int cenaMaksymalna, Wymaganie[] wymagania, int ilośćWymagań) {
        super(cenaMaksymalna, wymagania, ilośćWymagań);
    }
    public boolean wybierz(PlanWycieczki wycieczka) {
        return true;
    }
}
