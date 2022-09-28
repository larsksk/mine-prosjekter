public abstract class Resept {
    // Resept is an abstract class, therefore an object cannot be of type Resept only of one of its subclasses.
    // Starts a count and gives each Resept its own unique ID.
    public static int count = 0;
    public int id;

    public Legemiddel legemiddel;
    public Lege utskrivendeLege;
    public int pasientId;
    public int reit;

    Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;
        this.id = count++;
    }

    // Methods for returning stored information about the prescription.
    public int hentId() {
        return id;
    }

    public String hentLegemiddel() {
        return legemiddel.hentNavn();
    }

    public String hentLege() {
        return utskrivendeLege.hentLege();
    }

    public int hentPasientId() {
        return pasientId;
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

    abstract public double prisAaBetale();
}