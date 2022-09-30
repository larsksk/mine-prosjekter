public class Blaa extends Resept {
    private static double rabatt = 0.75;
    private double prisAaBetale;

    // Inherits from Resept, initializes, and gives each Resept it's own ID unique to the other prescriptions.
    // Also sets new price to a percentage of the original.
    Blaa(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
        prisAaBetale = legemiddel.hentPris()*(1 - rabatt);
        id = count++;
    }

    // Methods for returning stored information.
    public String farge() {
        return "blaa";
    }

    public double prisAaBetale() {
        return prisAaBetale;
    }

    public String toString() {
        return "ID:                 " + id + "\nLegemiddel:         " + legemiddel.hentNavn() + "\nUtskrivende lege:   " + utskrivendeLege.hentLege()
                + "\nFarge:              blaa" + "\nPasient ID:         " + pasientId + "\nReit:               " + reit
                + "\nPris aa betale:     " + prisAaBetale + " kr.\n";
    }
}