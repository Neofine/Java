public class Razy extends Operator2Arg{
    public Razy(Wyrażenie lewy, Wyrażenie prawy) {
        super(lewy, prawy);
    }
    @Override
    Double policz(Double x) {
        return lewy.policz(x) * prawy.policz(x);
    }

    @Override
    public String toString() {
        String zwróć = "";
        if (lewy.czyJestDodawaniem())
            zwróć += "(" + lewy.toString() + ")";
        else zwróć += lewy.toString();
        zwróć += " * ";
        if (prawy.czyJestDodawaniem())
            zwróć += "(" + prawy.toString() + ")";
        else zwróć += prawy.toString();
        return zwróć;
    }
}
