public abstract class Resept {
    // Resept is an abstract class, therefore an object cannot be of type Resept only of one of its subclasses.
    // Starts a count to give each Resept its own unique ID.
    public static int count = 0;
    public int id;

    public Legemiddel legemiddel;
    public Lege utskrivendeLege;
    public Pasient pasient;
    public int reit;

    Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        this.pasient.leggTilResept(this);
        this.utskrivendeLege.leggTilResept(this);
        id = count++;
    }

    // Methods for returning stored information about the prescription.
    public int hentId() {
        return id;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public String hentLege() {
        return utskrivendeLege.hentLege();
    }

    public int hentPasientId() {
        return pasient.hentId();
    }

    public int hentReit() {
        return reit;
    }

    // Method that uses the prescription if the prescription is not already depleted, and lowers reit by one.
    public boolean bruk() {
        if (reit != 0) {
            reit -= 1;
            return true;
        }
        else {
            return false;
        }
    }

    abstract public String farge();

    abstract public String type();

    abstract public double prisAaBetale();
}
