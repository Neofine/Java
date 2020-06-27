public class Dodać extends Operator2Arg{
    public Dodać(Wyrażenie lewy, Wyrażenie prawy) {
        super(lewy, prawy);
    }

    @Override
    Double policz(Double x) {
        return lewy.policz(x) + prawy.policz(x);
    }

    @Override
    public String toString() {
        return lewy.toString() + " + " + prawy.toString();
    }

    @Override
    public boolean czyJestDodawaniem() {
        return true;
    }
}
