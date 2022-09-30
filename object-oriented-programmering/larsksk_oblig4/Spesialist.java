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

    public Resept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) {
        return new Hvit(legemiddel, this, pasient, reit);
    }

    public Resept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit){
        return new Blaa(legemiddel, this, pasient, reit);
    }

    public Resept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit){
        return new Militaer(legemiddel, this, pasient, reit);
    }

    public Resept skrivPResept(Legemiddel legemiddel, Pasient pasient){
        return new Prevensjon(legemiddel, this, pasient);
    }

    public String toString() {
        return hentLege() + ", Spesialist, Kontroll ID: " + kontrollID + "\n";
    }
}
