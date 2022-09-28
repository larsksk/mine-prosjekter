public class PreparatC extends Legemiddel {

    // Inherits from Legemiddel class and initializes
    PreparatC(String navn, double pris, double virkestoff) {
        super(navn, pris, virkestoff);
        this.type = "c";
    }

    public String toString() {
        return this.id + ", " + this.navn + ", " + hentType() + ", " + this.pris + ", " + this.virkestoff + "\n";
    }
}