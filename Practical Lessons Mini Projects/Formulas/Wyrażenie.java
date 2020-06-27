abstract public class Wyrażenie {

    protected Wyrażenie twórzDodawanie(Wyrażenie w) {
        return w.twórzDodawaniePrzem(this);
    }

    protected Wyrażenie twórzDodawaniePrzem(Wyrażenie w) {
        return new Dodać(w, this);
    }

    protected Wyrażenie twórzDodawaniePrzezStała(Stała s) {
        return new Dodać(s, this);
    }

    protected boolean czyJestDodawaniem() {
        return false;
    }

    abstract Double policz(Double x);

    public abstract String toString();
}
