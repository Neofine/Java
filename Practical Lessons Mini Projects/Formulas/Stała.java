public class Stała extends Wyrażenie{
    Double wartość;
    Stała(Double wartość) {
        this.wartość = wartość;
    }
    @Override
    Double policz(Double x) {
        return wartość;
    }

    @Override
    public String toString() {
        return Double.toString(this.wartość);
    }

    protected Wyrażenie twórzDodawaniePrzezStała(Stała s) {
        return new Stała(this.wartość + s.wartość);
    }

    protected Wyrażenie twórzDodawanie(Wyrażenie w) {
        return w.twórzDodawaniePrzezStała(this);
    }


}
