class Rzodkiew extends Warzywo {
	@Override
	public float dajCene(int moment){
		if (moment <= 5) return 0;
		else if (moment <= 10) return (moment-5)*2;
		else if (moment == 11) return 1;
		return 0;
	}
	@Override
	public void wypisz(){
		System.out.print("RZODKIEW");
	}
	@Override
	public int numer() {
		return 2;
	}
}