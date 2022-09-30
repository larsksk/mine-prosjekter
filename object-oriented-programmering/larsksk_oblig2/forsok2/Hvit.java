public class Hvit extends Resept {
    // Inherits from Resept, initializes.
    Hvit(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    // Methods for returning stored information.
    public String farge() {
        return "hvit";
    }

    public double prisAaBetale() {
        return legemiddel.hentPris();
    }

    public String toString() {
        return "ID:                 " + id + "\nLegemiddel:         " + legemiddel.hentNavn() + "\nUtskrivende lege:   " + utskrivendeLege.hentLege()
                + "\nFarge:              hvit" + "\nPasient ID:         " + pasientId + "\nReit:               " + reit
                + "\nPris aa betale:     " + prisAaBetale() + " kr.\n";
    }
}