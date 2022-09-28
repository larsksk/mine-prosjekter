public class PreparatA extends Legemiddel {
    private int styrke;

    // Inherits from Legemiddel class and initializes
    PreparatA(String navn, double pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
        this.type = "a";
    }

    public int hentNarkotiskStyrke() {
        return styrke;
    }

    public String toString() {
        return this.id + ", " + this.navn + ", " + this.hentType() + ", " + this.pris + ", " + this.virkestoff
                + ", " + this.styrke + "\n";
    }
}
