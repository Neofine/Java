public class Main {

    public static void main(String[] args) {
        Scanning what = new Scanning();
        // wczytuję na Bajtocję wejście i na niej wszystkie
        // informacje zapisuję
        Byteotion byteotion = what.read();
        Simulation simulation = new Simulation();

        System.out.println("Metoda De'Hondta");
        simulation.simulateElection(byteotion, new DHondtMethod());
        simulation.resetByteotion(byteotion);

        System.out.println("Metoda Sainte-Lague");
        simulation.simulateElection(byteotion, new SainteLagueMethod());
        simulation.resetByteotion(byteotion);

        System.out.println("Metoda Hare'a-Neimeyera");
        simulation.simulateElection(byteotion, new HareNiemeyerMethod());
        simulation.resetByteotion(byteotion);
    }

}