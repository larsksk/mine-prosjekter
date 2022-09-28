public class Hvit extends Resept {
    public double prisAaBetale;

    // Inherits from Resept, initializes, and gives each Resept its own ID unique to the other prescriptions.
    Hvit(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
        prisAaBetale = legemiddel.hentPris();
        id = count++;
    }

    // Methods for returning stored information.
    public String farge() {
        return "hvit";
    }

    public double prisAaBetale() {
        return prisAaBetale;
    }

    public String toString() {
        return "ID:                 " + id + "\nLegemiddel:         " + legemiddel.hentNavn() + "\nUtskrivende lege:   " + utskrivendeLege.hentLege()
                + "\nFarge:              hvit" + "\nPasient ID:         " + pasientId + "\nReit:               " + reit
                + "\nPris aa betale:     " + prisAaBetale + " kr.\n";
    }
}