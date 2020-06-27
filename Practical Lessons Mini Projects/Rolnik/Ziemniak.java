class Ziemniak extends Warzywo {
	@Override
	public float dajCene(int moment){
		if (moment <= 10) return 0;
		else if (moment <= 15) return 5;
		else return 2;
	}
	@Override
	public void wypisz(){
		System.out.print("ZIEMNIAKA");
	}
	@Override
	public int numer() {
		return 3;
	}
}