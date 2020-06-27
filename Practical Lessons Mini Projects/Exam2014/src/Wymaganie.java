public class Wymaganie {
    private Miasto miasto;
    private boolean czyChce;
    Wymaganie(Miasto miasto, boolean czyChce) {
        this.miasto = miasto;
        this.czyChce = czyChce;
    }
    public Miasto miasto() {
        return miasto;
    }
    public boolean czyChce() {
        return czyChce;
    }
}
