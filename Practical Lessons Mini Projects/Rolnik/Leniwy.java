import java.util.ArrayList;


public class Leniwy extends Rolnik{
	int zarobek;
	ArrayList<Float> cenyWarzyw = new ArrayList<Float>();
	ArrayList<Integer> dniOdZasadzenia = new ArrayList<Integer>();
	@Override
	public void symuluj (Ogrod ogrod, int liczbaDni) {
		ogrod.zacznij();
		int liczbaGrzadek = ogrod.dajLiczbeGrzadek();
		for (int i = 0; i <= liczbaGrzadek; i++) {
			cenyWarzyw.add(0f);
			dniOdZasadzenia.add(0);
		}
		for (int i = 1; i <= liczbaDni; i++) {
			symulujTure(ogrod, i);
		}
		System.out.println("ZAROBEK " + zarobek);
	}
	@Override
	public void aktualizujCene (int grzadka, Ogrod ogrod) {
		dniOdZasadzenia.set(grzadka, dniOdZasadzenia.get(grzadka)+1);
		cenyWarzyw.set(grzadka, ogrod.cenaTerazWarzywa(grzadka,
		dniOdZasadzenia.get(grzadka)));
	}
	private void symulujTure(Ogrod ogrod, int liczbaDni) {
		int liczbaGrzadek = ogrod.dajLiczbeGrzadek();
		for (int grzadka = 1; grzadka <= liczbaGrzadek; grzadka++) {
			if ((liczbaDni-1)%10==0) {
				if (ogrod.czyGrzadkaPusta(grzadka)) {
					zasadz(grzadka, ogrod);
				}
				else zbierzOrazPosadz(grzadka, ogrod);
			}
			aktualizujCene(grzadka, ogrod);
		}
	}
	@Override
	public void zbierzOrazPosadz(int grzadka, Ogrod ogrod) {
		
	}
	@Override
	public void zasadz(int grzadka, Ogrod ogrod) {
		System.out.print("POSADZILEM: ");
		ogrod.posadzWarzywo(grzadka);
		ogrod.wypiszNazweWarzywa(grzadka);
		System.out.println(" !");
		dniOdZasadzenia.set(grzadka, 0);
		cenyWarzyw.set(grzadka, ogrod.cenaTerazWarzywa(grzadka,
		dniOdZasadzenia.get(grzadka)));
	}
}
