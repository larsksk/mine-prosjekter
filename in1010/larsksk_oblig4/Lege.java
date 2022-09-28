public class Lege implements Comparable<Lege> {
    public String utskrivendeLege;
    private Lenkeliste<Resept> resepter = new Lenkeliste<>();

    Lege(String utskrivendeLege) {
        this.utskrivendeLege = utskrivendeLege;
    }

    public String hentLege() {
        return utskrivendeLege;
    }

    // Method for writing prescriptions of the Resept sub-classes.
    // If the prescriptions Legemiddel is of the type PreparatA, checks if the person writing the prescription
    // has Godkjenningsfritak, and calls the UlovligUtskrift custom exception if it does not.

    public Resept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if(legemiddel instanceof PreparatA) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        return new Hvit(legemiddel, this, pasient, reit);
    }

    public Resept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if(legemiddel instanceof PreparatA) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        return new Blaa(legemiddel, this, pasient, reit);
    }

    public Resept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if(legemiddel instanceof PreparatA) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        return new Militaer(legemiddel, this, pasient, reit);
    }

    public Resept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if(legemiddel instanceof PreparatA) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        return new Prevensjon(legemiddel, this, pasient);
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
