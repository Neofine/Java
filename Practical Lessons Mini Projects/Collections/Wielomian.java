import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Wielomian {
    SortedMap<Integer, Integer> wielomian;
    Wielomian() {
        wielomian = new TreeMap<Integer, Integer>();
    }

    void DodajSkładnik(int stopień, int wartość) {
        wielomian.put(stopień, wielomian.getOrDefault(stopień, 0) + wartość);
    }

    void DodajWielomian(Wielomian dodawany) {
        Iterator<Map.Entry<Integer, Integer>> iter = dodawany.wielomian.entrySet().iterator();

        while(iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = iter.next();
            DodajSkładnik(entry.getKey(), entry.getValue());
        }
    }

    void Wypisz() {
        Iterator<Map.Entry<Integer, Integer>> iter = wielomian.entrySet().iterator();

        while(iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = iter.next();
            System.out.print(entry.getValue() + "x^" + entry.getKey() + " ");
        }
    }

    void Mnożenie(int skalar) {
        Iterator<Map.Entry<Integer, Integer>> iter = wielomian.entrySet().iterator();

        while(iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = iter.next();
            wielomian.put(entry.getKey(), (skalar - 1) * wielomian.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
    }
}
