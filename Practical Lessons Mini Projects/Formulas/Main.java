public class Main {
    public static void main(String args[]) {
        Stała stała1 = new Stała(3.0);
        Stała stała2 = new Zero(0.0);
        Wyrażenie stała = stała1.twórzDodawaniePrzezStała(stała2);
        Wyrażenie sinus = new Sinus(stała);
        System.out.print(sinus.policz(2.0));
    }
}
