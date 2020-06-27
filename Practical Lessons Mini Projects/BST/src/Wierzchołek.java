public class Wierzchołek <W extends Comparable<W>>{
    W wartość;
    Wierzchołek lewy, prawy;
    Wierzchołek(W wartość) {
        this.wartość = wartość;
        this.lewy = this.prawy = null;
    }
}
