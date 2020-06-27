public class Zero extends Stała{
    static final Zero zero = new Zero(0.0);
    Zero(Double wartość) {
        super(wartość);
    }

    protected Wyrażenie twórzDodawanie(Wyrażenie w) {
        return w;
    }

    protected Wyrażenie twórzDodawaniePrzem(Wyrażenie w) {
        return w;
    }
}
