import java.util.Random;

public class Ogrod {
	int LICZBA_RODZAJOW_WARZYW = 3;
	int LICZBA_GRZADEK = 10;
	Warzywo[] warzywa = new Warzywo[dajLiczbeGrzadek()+1];
	public void zacznij() {
		int grzadek = dajLiczbeGrzadek();
		for (int i = 1; i <= grzadek; i++) {
			warzywa[i] = new PustaGrzadka();
		}
	}
	public int dajLiczbeGrzadek() {
		return LICZBA_GRZADEK;
	}
	public float cenaTerazWarzywa(int grzadka, int dzienOdZasadzenia) {
		return (warzywa[grzadka]).dajCene(dzienOdZasadzenia);
	}
	Warzywo posadzWarzywo(int grzadka) {
		Random random = new Random();
		int numerTypuWarzywa = random.nextInt(LICZBA_RODZAJOW_WARZYW);
		Warzywo warzywo;
		if (numerTypuWarzywa == 0) {
			warzywo = new Pomidor();
		}
		else if (numerTypuWarzywa == 1) {
			warzywo = new Ziemniak();
		}
		else {
			warzywo = new Rzodkiew();
		}
		warzywa[grzadka] = warzywo;
		return warzywo;
	}
	public void wypiszNazweWarzywa(int grzadka) {
		warzywa[grzadka].wypisz();
	}
	public float cenaWarzywa(int grzadka, int dzien) {
		return warzywa[grzadka].dajCene(dzien);
	}
	public boolean czyGrzadkaPusta(int grzadka) {
		return (warzywa[grzadka].numer() == 0);
	}
}