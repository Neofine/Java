class PustaGrzadka extends Warzywo {
	@Override
	public float dajCene(int moment){
		return 0;
	}
	@Override
	public void wypisz(){
		System.out.print("BLAD WYPISANA PUSTA GRZADKA");
	}
	@Override
	public int numer() {
		return 0;
	}
}