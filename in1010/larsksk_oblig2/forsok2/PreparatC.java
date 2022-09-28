public class PreparatC extends Legemiddel {

    // Inherits from Legemiddel class and initializes
    PreparatC(String navn, double pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }

    public String toString() {
        return "Id:         " + this.id + "\nNavn:       " + this.navn + "\nPris:       "
                + this.pris + "\nVirkestoff: " + this.virkestoff + "\n";
    }
}