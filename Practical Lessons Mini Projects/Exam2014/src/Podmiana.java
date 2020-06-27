public class Podmiana extends Modyfikacja{
    private Miasto naMiasto;
    Podmiana(Miasto miasto, Miasto naMiasto) {
        super(miasto);
        this.naMiasto = naMiasto;
    }
    @Override
    public boolean wykonaj(PlanWycieczki plan) {
        if (plan.czyIstnieje(miasto)) {
            plan.usuń(miasto);
            plan.dodaj(naMiasto);
            return true;
        }
        return false;
    }
    @Override
    public void cofnij(PlanWycieczki plan) {
        plan.dodaj(miasto);
        plan.usuń(naMiasto);
    }
}
