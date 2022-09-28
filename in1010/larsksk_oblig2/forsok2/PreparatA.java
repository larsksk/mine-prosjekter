public class PreparatA extends Legemiddel {
    private int styrke;

    // Inherits from Legemiddel class and initializes
    PreparatA(String navn, double pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentNarkotiskStyrke() {
        return styrke;
    }

    public String toString() {
        return "Id:         " + id + "\nNavn:       " + this.navn + "\nPris:       "
                + this.pris + "\nVirkestoff: " + this.virkestoff + "\nStyrke:     " + this.styrke + "\n";
    }
}
