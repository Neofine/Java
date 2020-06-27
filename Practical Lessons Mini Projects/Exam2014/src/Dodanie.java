public class Dodanie extends Modyfikacja{
    Dodanie(Miasto miasto) {
        super(miasto);
    }
    @Override
    public boolean wykonaj(PlanWycieczki plan) {
        plan.dodaj(miasto);
        return true;
    }
    @Override
    public void cofnij(PlanWycieczki plan) {
        plan.usuń(miasto);
    }
}
