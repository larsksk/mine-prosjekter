public class Militaer extends Hvit {

    // Inherits from Hvit Resept, initializes, and sets price to 0kr.
    Militaer(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public double prisAaBetale() {
        return 0;
    }

    public String type() {
        return "militaer";
    }
}
