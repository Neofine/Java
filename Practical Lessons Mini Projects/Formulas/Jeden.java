public class Jeden extends Stała{
    static final Jeden jeden = new Jeden(1.0);
    Jeden(Double wartość) {
        super(wartość);
    }
    protected Wyrażenie twórzMnożenie(Wyrażenie w) {
        return w;
    }

    protected Wyrażenie twórzMnożeniePrzem(Wyrażenie w) {
        return w;
    }
}
