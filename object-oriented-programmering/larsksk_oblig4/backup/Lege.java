public class Lege implements Comparable<Lege> {
    public String utskrivendeLege;
    private Lenkeliste<Resept> resepter = new Lenkeliste<>();

    Lege(String utskrivendeLege) {
        this.utskrivendeLege = utskrivendeLege;
    }

    public String hentLege() {
        return utskrivendeLege;
    }

    // Method for writing prescriptions of the Resept sub-classes. Here, the example is of a Hvit Resept.
    // If the prescriptions Legemiddel is of the type PreparatA, checks if the person writing the prescription
    // has Godkjenningsfritak, and calls the UlovligUtskrift custom exception if it does not.
    public Resept skrivResept(Legemiddel legemiddel, Pasient pasient, int reit, String type) throws UlovligUtskrift{
        if(legemiddel instanceof PreparatA) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else {
            Resept resept;
            if (type.equals("blaa")) {
                resept = new Blaa(legemiddel, this, pasient, reit);
            }
            else if (type.equals("militaer")) {
                resept = new Militaer(legemiddel, this, pasient, reit);
            }
            else if (type.equals("prevensjon")) {
                resept = new Prevensjon(legemiddel, this, pasient);
            }
            else {
                resept = new Hvit(legemiddel, this, pasient, reit);
            }
            return resept;
        }
    }

    public int compareTo(Lege lege2) {
        return utskrivendeLege.compareTo(lege2.hentLege());
    }

    public void leggTilResept(Resept resept) {
        resepter.leggTil(resept);
    }

    public Lenkeliste<Resept> hentResepter() {
        return resepter;
    }

    public String toString() {
        return hentLege() + ", Lege\n";
    }
}
