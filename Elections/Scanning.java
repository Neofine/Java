import java.util.ArrayList;
import java.util.Scanner;

public class Scanning {
    // skaner dzięki któremu będę wczytywał wejście
    private final Scanner scan = new Scanner(System.in);

    // klasa w której procesuję wejście (kandydatów i wyborców)
    private final Processing process = new Processing();

    // funkcja wczytuje wejście i je procesuje zwracając w pełni
    // uzupełnioną klasę Bajtocji
    public Byteotion read() {
        // pierwsza linia wejścia
        ArrayList<Integer> scannedIntLine = readIntLine();
        Byteotion byteotion = new Byteotion(scannedIntLine.get(0), scannedIntLine.get(1), scannedIntLine.get(2), scannedIntLine.get(3));
        // połączone dystrykty
        String[] connectedDistricts = readLine();
        // nazwy partii
        String[] partiesNames = readLine();
        // budżety partii
        ArrayList<Integer> partiesBudgets = readIntLine();
        // strategie partii
        String[] partiesStrategies = readLine();

        for (int i = 0; i < byteotion.partiesAmount(); i++) {
            byteotion.addParty(new Party(partiesBudgets.get(i), partiesNames[i], partiesStrategies[i].charAt(0)));
        }

        // ilości kandydatów w każdym z dystryktów
        ArrayList<Integer> votersNumber = readIntLine();
        int candidatesAmount = 0;
        for (int i = 0; i < byteotion.districtsAmount(); i++) {
            byteotion.addDistrict(new ElectionalDistrict(byteotion, i+1, votersNumber.get(i)));
            candidatesAmount += votersNumber.get(i) / 10;
        }

        int connectedAmount = Integer.parseInt(connectedDistricts[0]);
        for (int i = 1; i <= connectedAmount; i++) {
            int number = 0, k;
            for (k = 1; connectedDistricts[i].charAt(k) != ','; k++) {
                number *= 10;
                number += connectedDistricts[i].charAt(k) - '0';
            }
            ElectionalDistrict firstDistrict = byteotion.district(number);
            ElectionalDistrict secondDistrict = byteotion.district(number + 1);
            firstDistrict.connectTo(secondDistrict);
        }
        for (int i = 1; i <= candidatesAmount * byteotion.partiesAmount(); i++) {
            // kandydat
            String[] candidateLine = readLine();
            process.addCandidate(byteotion, candidateLine);
        }
        int votersAmount = candidatesAmount * 10;
        for (int i = 1; i <= votersAmount; i++) {
            // wyborca
            String[] voterLine = readLine();
            process.addVoter(byteotion, voterLine);
        }
        for (int i = 1; i <= byteotion.operations().vectorsAmount(); i++) {
            // wektor dostępny do użycia w kampanii
            ArrayList<Integer> vector = readIntLine();
            byteotion.operations().addVector(vector);
        }
        return byteotion;
    }

    // czyta jedną linię wejście i zapisuję ją jako tablicę stringów
    private String[] readLine() {
        return scan.nextLine().trim().split("\\s+");
    }

    // czyta jedną linię wejścia i zapisuje ją jako tablicę integerów
    private ArrayList<Integer> readIntLine() {
        String[] line = readLine();
        ArrayList<Integer> output = new ArrayList<>();
        for (String i: line) {
            if (!i.isBlank())
                output.add(Integer.parseInt(i));
        }
        return output;
    }

}
