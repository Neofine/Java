abstract public class Operator2Arg extends Wyrażenie{
    protected Wyrażenie lewy, prawy;
    Operator2Arg(Wyrażenie lewy, Wyrażenie prawy) {
        this.lewy = lewy;
        this.prawy = prawy;
    }
}
