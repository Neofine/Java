public class Miasto {
    private String nazwa;
    private int koszt;
    Miasto(String nazwa, int koszt) {
        this.nazwa = nazwa;
        this.koszt = koszt;
    }
    public String nazwa() {
        return nazwa;
    }
    public int koszt() {
        return koszt;
    }
}
