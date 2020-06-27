public class Logarytm extends Operator1Arg{
    Logarytm(Wyrażenie argument) {
        super(argument);
    }
    @Override
    Double policz(Double x) {
        return Math.log(argument.policz(x));
    }

    @Override
    public String toString() {
        return "log(" + argument.toString() + ")";
    }
}
