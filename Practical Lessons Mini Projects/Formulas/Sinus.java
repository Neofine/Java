import javax.swing.*;

public class Sinus extends Operator1Arg{
    Sinus(Wyrażenie argument) {
        super(argument);
    }
    @Override
    Double policz(Double x) {
        return Math.sin(argument.policz(x));
    }

    @Override
    public String toString() {
        return "sin(" + argument.toString() + ")";
    }
}
