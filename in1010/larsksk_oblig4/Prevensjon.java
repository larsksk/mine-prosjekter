public class Prevensjon extends Hvit {

    // Inherits from Hvit Resept, initializes, sets reit = 3,
    // and sets a new price given the discount of Prevensjon Resept.
    Prevensjon(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, 3);
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

    public String type() {
        return "prevensjon";
    }
}
