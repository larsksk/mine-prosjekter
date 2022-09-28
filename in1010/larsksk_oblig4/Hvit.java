public class Hvit extends Resept {

    // Inherits from Resept, initializes, and gives each Resept its own ID unique to the other prescriptions.
    Hvit(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    // Methods for returning stored information.
    public String farge() {
        return "hvit";
    }

    public String type() {
        return "hvit";
    }

    public double prisAaBetale() {
        return legemiddel.hentPris();
    }

    public String toString() {
        return id + ", " + legemiddel.hentNavn() + ", " + utskrivendeLege.hentLege() + ", " + this.farge() + ", "
                + pasient.hentId() + ", " + reit + ", " + prisAaBetale() + " kr.\n";
    }
}