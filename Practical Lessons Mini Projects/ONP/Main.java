public class Main {
    public static void main(String[] args) throws IStack.EmptyStackException, ONP.IllegalComputationException, ONP.IllegalCharException, IStack.FullStackException, ONP.IllegalExpresionException {
        // operuję na Doublach gdyby były jakieś dzielenia np 5 2 /
        // poprawne wejście
        System.out.println(ONP.compute( "3 4 2 * 1 5 - 2 ^ / +"));
        System.out.println(ONP.compute( "5 1 2 + 4 * + 3 -"));
        System.out.println(ONP.compute( "12 2 3 4 * 10 5 / + * +"));
        System.out.println(ONP.compute( "2 7 + 3 / 14 3 - 4 * + 2 /"));
        System.out.println(ONP.compute( "2 3 + 5 *"));

        // stack przepełniony
        System.out.println(ONP.compute( "2 3 5 1 2 3 4 5 6 7 8 9"));

        // za duzo zdejmowania
        System.out.println(ONP.compute( "2 3 + - + + +"));

        // Dzielenie przez 0 chyba nie dziala z powodu typów double
        System.out.println(ONP.compute( "2 0 /"));
        // Nielegalny symbol
        System.out.println(ONP.compute( "2 2 x d"));
        // Na końcu na stacku jest więcej niż 1 liczba
        System.out.println(ONP.compute( "2 2"));
    }
}
