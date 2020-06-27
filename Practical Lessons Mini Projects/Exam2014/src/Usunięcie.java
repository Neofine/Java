public class Usunięcie extends Modyfikacja{
    Usunięcie(Miasto miasto) {
        super(miasto);
    }
    @Override
    public boolean wykonaj(PlanWycieczki plan) {
        if (plan.czyIstnieje(miasto)) {
            plan.usuń(miasto);
            return true;
        }
        return false;
    }
    @Override
    public void cofnij(PlanWycieczki plan) {
        plan.dodaj(miasto);
    }
}
