import java.util.ArrayList;

// klasa zawierająca dostępne działania wyborcze partii
public class ElectionOperations {
    // ilość atrybutów u kandydatów w Bajtocji, czyli też długość
    // wektorów działań wyborczych
    private final int attributeAmount;
    // ilość wektorów zmiany wag, które partie mogą użyć w kampaniach wyborczych
    private final int vectorsAmount;
    // tablica wektorów zmiany wag, które partie mogą użyć w kampaniach wyborczych
    private final int[][] vectors;
    // licznik przy pomocy którego dodaję wektory na tablicę w Bajtocji
    private int vecIterator = 0;

    // konstruktor
    ElectionOperations(int attributeAmount, int vectorsAmount) {
        this.attributeAmount = attributeAmount;
        this.vectorsAmount = vectorsAmount;
        vectors = new int[vectorsAmount + 1][attributeAmount + 1];
    }

    // getter zwracający specyficzny wektor
    public int[][] vectors() {
        return vectors;
    }

    // getter zwracający ilość wektorów w Bajtocji
    public int vectorsAmount() {
        return vectorsAmount;
    }

    // getter zwracający liczbę atrybutów/cech kandydatów w Bajtocji
    public int attributeAmount() {
        return attributeAmount;
    }

    // setter dodający nowy wektor do Bajtocji
    public void addVector(ArrayList<Integer> vector) {
        vecIterator++;
        for (int i = 0; i < attributeAmount; i++) {
            vectors[vecIterator][i + 1] = vector.get(i);
        }
    }
}
