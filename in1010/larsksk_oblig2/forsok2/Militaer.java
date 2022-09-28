public class Militaer extends Hvit {

    // Inherits from Hvit Resept, initializes.
    Militaer(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    public double prisAaBetale() {
        return 0;
    }
}
