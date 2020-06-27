class Pomidor extends Warzywo {
	@Override
	public float dajCene(int moment){
		if (moment <= 10) return 0;
		else if (moment <= 15) return (moment-10)*2;
		else if (moment <= 20) return (20-moment)*2;
		return 0;
	}
	@Override
	public void wypisz(){
		System.out.print("POMIDORA");
	}
	@Override
	public int numer() {
		return 1;
	}
}