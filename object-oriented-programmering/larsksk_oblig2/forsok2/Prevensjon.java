public class Prevensjon extends Hvit {

    // Inherits from Hvit Resept, initializes, sets reit = 3.
    Prevensjon(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId) {
        super(legemiddel, utskrivendeLege, pasientId, 3);
    }

    public double prisAaBetale() {
        double pris = legemiddel.hentPris() - 108;
        if(pris > 0) {
            return pris;
        }
        else {
            return 0;
        }
    }
}
