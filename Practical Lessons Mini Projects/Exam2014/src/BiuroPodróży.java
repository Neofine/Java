public class BiuroPodróży {
    public static PlanWycieczki wybierzPlan(Turysta[] turysta, PlanWycieczki plan, Modyfikacja[] dozwoloneZmiany,
                                     KryteriumOceny kryterium) {
        PlanWycieczki najlepszy = null;
        int ilośćZmian = dozwoloneZmiany.length - 1;
        int maksymalny = -1, ocena;
        if ((ocena = kryterium.oceńPlan(plan, turysta)) > maksymalny) {
            najlepszy = plan;
            maksymalny = ocena;
        }
        for (int i = 1; i <= ilośćZmian; i++) {
            for (int j = i + 1; j <= ilośćZmian; j++) {
                dozwoloneZmiany[i].wykonaj(plan);
                if ((ocena = kryterium.oceńPlan(plan, turysta)) > maksymalny) {
                    najlepszy = plan;
                    maksymalny = ocena;
                }
                dozwoloneZmiany[j].wykonaj(plan);
                if ((ocena = kryterium.oceńPlan(plan, turysta)) > maksymalny) {
                    najlepszy = plan;
                    maksymalny = ocena;
                }
                dozwoloneZmiany[i].cofnij(plan);
                if ((ocena = kryterium.oceńPlan(plan, turysta)) > maksymalny) {
                    najlepszy = plan;
                    maksymalny = ocena;
                }
                dozwoloneZmiany[j].cofnij(plan);
            }
        }
        return najlepszy;
    }
}
