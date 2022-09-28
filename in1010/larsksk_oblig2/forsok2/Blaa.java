public class Blaa extends Resept {
    // Inherits from Resept, initializes.
    Blaa(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    // Methods for returning stored information.
    public String farge() {
        return "blaa";
    }

    public double prisAaBetale() {
        return legemiddel.hentPris()*(0.25);
    }

    public String toString() {
        return "ID:                 " + id + "\nLegemiddel:         " + legemiddel.hentNavn() + "\nUtskrivende lege:   " + utskrivendeLege.hentLege()
                + "\nFarge:              blaa" + "\nPasient ID:         " + pasientId + "\nReit:               " + reit
                + "\nPris aa betale:     " + prisAaBetale() + " kr.\n";
    }
}