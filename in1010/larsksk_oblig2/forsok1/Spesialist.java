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

    public String toString() {
        return "Navn, Spesialist: " + hentLege() + "\nKontroll ID:  " + kontrollID + "\n";
    }
}
