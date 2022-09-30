public class Spesialist extends Lege implements Godkjenningsfritak{
    private int kontrollID;

    // Inherits from Lege class and initializes.
    Spesialist(String utskrivendeLege, int kontrollID) {
        super(utskrivendeLege);
        this.kontrollID = kontrollID;
    }

    // Implements interface for KontrollID, to check if the person is eligible to write out PreparatA-type Resepter.
    public int hentKontrollID(){
        return kontrollID;
    }

    public Resept skrivResept(Legemiddel legemiddel, Pasient pasient, int reit, String type) {
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

    public String toString() {
        return hentLege() + ", Spesialist, Kontroll ID: " + kontrollID + "\n";
    }
}
