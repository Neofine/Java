import java.util.ArrayList;

public class Przeciecie {
    static public ArrayList<Integer> przeciecie(ArrayList<Integer> pierwsza, ArrayList<Integer> druga) {
        int iterPierwszy, iterDrugi;
        iterPierwszy = iterDrugi = 0;

        int sizePierwszy, sizeDrugi;
        sizePierwszy = pierwsza.size();
        sizeDrugi = druga.size();

        ArrayList<Integer> odpowiedz = new ArrayList<>();

        while (iterPierwszy < sizePierwszy && iterDrugi < sizeDrugi) {
            int terazPierwszy, terazDrugi;
            terazPierwszy = pierwsza.get(iterPierwszy);
            terazDrugi = druga.get(iterDrugi);
            if (terazPierwszy == terazDrugi) {
                odpowiedz.add(terazPierwszy);
            }
            else if (terazPierwszy < terazDrugi) {
                iterPierwszy++;
            }
            else {
                iterDrugi++;
            }
        }
        return odpowiedz;
    }
}
