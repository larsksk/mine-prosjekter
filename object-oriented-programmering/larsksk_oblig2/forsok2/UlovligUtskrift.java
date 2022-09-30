public class UlovligUtskrift extends Exception {
    // Custom exception which is called if person, writing a Resept with Legemiddel of the type PreparatA,
    // does not have the Godkjenningsfritak interface implemented.
    UlovligUtskrift(Lege lege, Legemiddel legemiddel){
        super("Legen "+lege.hentLege()+ " har ikke lov til å skrive ut " + legemiddel.hentNavn());
    }
}
