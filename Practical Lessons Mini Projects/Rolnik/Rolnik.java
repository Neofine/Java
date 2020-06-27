public abstract class Rolnik {
	public abstract void symuluj (Ogrod ogrod, int liczbaDni);
	public abstract void aktualizujCene (int grzadka, Ogrod ogrod);
	public abstract void zbierzOrazPosadz(int grzadka, Ogrod ogrod);
	public abstract void zasadz(int grzadka, Ogrod ogrod);
}