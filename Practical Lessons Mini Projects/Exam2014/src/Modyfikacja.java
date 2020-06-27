public abstract class Modyfikacja {
    protected Miasto miasto;
    Modyfikacja(Miasto miasto) {
        this.miasto = miasto;
    }
    public abstract boolean wykonaj(PlanWycieczki plan);
    public abstract void cofnij(PlanWycieczki plan);
}
