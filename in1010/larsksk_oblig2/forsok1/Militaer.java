public class Militaer extends Hvit {

    // Inherits from Hvit Resept, initializes, and sets price to 0kr.
    Militaer(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
        prisAaBetale = 0;
    }
}
