public abstract class Turysta {
    protected int cenaMaksymalna;
    protected Wymaganie[] wymagania;
    protected int ilośćWymagań;
    Turysta(int cenaMaksymalna, Wymaganie[] wymagania, int ilośćWymagań) {
        this.cenaMaksymalna = cenaMaksymalna;
        this.wymagania = wymagania;
        this.ilośćWymagań = ilośćWymagań;
    }
    abstract boolean wybierz(PlanWycieczki wycieczka);
}
