public class Chciwe implements KryteriumOceny{
    @Override
    public int oceńPlan(PlanWycieczki plan, Turysta[] turyści) {
        int zarobek = 0;
        for (Turysta turysta: turyści) {
            if (turysta.wybierz(plan))
                zarobek+=plan.sumaKosztów();
        }
        return zarobek;
    }
}
