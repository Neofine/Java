public class Minus extends Operator1Arg{
    Wyrażenie argument;
    Minus(Wyrażenie argument) {
        super(argument);
    }
    @Override
    Double policz(Double x) {
        return -argument.policz(x);
    }

    @Override
    public String toString() {
        return "(-" + argument.toString() + ")";
    }
}
