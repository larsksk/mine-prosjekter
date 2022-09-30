public class PreparatB extends Legemiddel {
    private int styrke;

    // Inherits from Legemiddel class and initializes
    PreparatB(String navn, double pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    public int hentVanedannendeStyrke() {
        return styrke;
    }

    public String toString() {
        return "Id:         " + this.id + "\nNavn:       " + this.navn + "\nPris:       "
                + this.pris + "\nVirkestoff: " + this.virkestoff + "\nStyrke:     " + this.styrke + "\n";
    }
}