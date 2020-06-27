public class Puste implements KryteriumOceny{
    @Override
    public int oceńPlan(PlanWycieczki plan, Turysta[] turyści) {
        int może = 0;
        for (Turysta turysta: turyści) {
            if (turysta.wybierz(plan))
                może++;
        }
        return turyści.length - może;
    }
}
