public class Lege {
    public String utskrivendeLege;

    Lege(String utskrivendeLege) {
        this.utskrivendeLege = utskrivendeLege;
    }

    public String hentLege() {
        return utskrivendeLege;
    }

    // Method for writing prescriptions of the Resept sub-classes. Here, the example is of a Hvit Resept.
    // If the prescriptions Legemiddel is of the type PreparatA, checks if the person writing the prescription
    // has Godkjenningsfritak, and calls the UlovligUtskrift custom exception if it does not.
    public Resept skrivResept(Legemiddel legemiddel, int pasientId, int reit) throws UlovligUtskrift{
        if(legemiddel instanceof PreparatA) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else {
            return new Hvit(legemiddel, this, pasientId, reit);
        }
    }

    public String toString() {
        return "Navn, Lege: " + hentLege() + "\n";
    }
}
