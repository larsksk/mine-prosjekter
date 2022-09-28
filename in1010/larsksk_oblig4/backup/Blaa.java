public class Blaa extends Resept {

    // Inherits from Resept, initializes, and gives each Resept it's own ID unique to the other prescriptions.
    // Also sets new price to a percentage of the original.
    Blaa(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    // Methods for returning stored information.
    public String farge() {
        return "blaa";
    }

    public String type() {
        return "blaa";
    }

    public double prisAaBetale() {
        return legemiddel.hentPris()*(0.25);
    }

    public String toString() {
        return id + ", " + legemiddel.hentNavn() + ", " + utskrivendeLege.hentLege() + ", " + this.farge() + ", "
                + pasient.hentId() + ", " + reit + ", " + prisAaBetale() + " kr.\n";
    }
}