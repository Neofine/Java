package cover;

import java.util.ArrayList;
import java.util.Scanner;

// Klasa czyta wejście
public class Czytanie {
    // funkcja przy pomocy skanera wrzuca na listę wejście wszystkie
    // liczby podane na nim (osobna komórka listy to osobna liczba oddzielona
    // znakami białymi od innych)
    public static void czytaj(Scanner scan, ArrayList <Integer> wejście) {
        while(scan.hasNextLine()) {
            String linia[] = scan.nextLine().trim().split("\\s+");
            for (String i: linia) {
                if (!i.isBlank())
                    wejście.add(Integer.parseInt(i));
            }
        }
    }
}
