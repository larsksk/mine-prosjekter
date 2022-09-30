public class Prevensjon extends Hvit {

    // Inherits from Hvit Resept, initializes, sets reit = 3,
    // and sets a new price given the discount of Prevensjon Resept.
    Prevensjon(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
        prisAaBetale = legemiddel.hentPris() - 108.0;
        if (prisAaBetale < 0) {
            prisAaBetale = 0;
        }
        this.reit = 3;
    }
}
