import java.io.PrintWriter;
import java.io.File;

public class Operasjonsleder implements Runnable{
    private Monitor dekryptert;

    // Oppretter en liste med tomme Strings for hver av kanalene. Her kunne jeg muligens ha laget programmet slik
    // at den teller opp antall meldinger som skal inn i hver av kanalene, slik at det ikke blir laget for store lister.
    private String[] kanal1 = new String[150];
    private String[] kanal2 = new String[150];
    private String[] kanal3 = new String[150];

    Operasjonsleder(Monitor dekryptert) {
        this.dekryptert = dekryptert;
    }

    public void run(){
        sorterILister();
        skrivTilFil("Kanal1", kanal1);
        skrivTilFil("Kanal2", kanal2);
        skrivTilFil("Kanal3", kanal3);
    }

    // Sorterer meldingene etter kanal og sekvensnummer. Slik at meldingene kommer til riktig tekst og rekkefoelge.
    private void sorterILister(){
        Melding melding = dekryptert.taAv();
        while(melding != null){
            if (melding.getKanalid() == 1) {
                settInn(melding, kanal1);
            }
            else if (melding.getKanalid() == 2) {
                settInn(melding, kanal2);
            }
            else if (melding.getKanalid() == 3) {
                settInn(melding, kanal3);
            }
            else {
                System.out.println("Ugyldig kanal.");
            }
            melding = dekryptert.taAv();
        }
    }

    private void settInn(Melding melding, String[] kanal){
        int i = melding.getSekvensnr();
        kanal[i] = melding.toString();
    }

    // Skriver tekstene fra hver av kanalene til egen fil, naa i riktig rekkefoelge.
    private void skrivTilFil(String utfil, String[] kanal){
        try {
            PrintWriter writer = new PrintWriter(new File(utfil+".txt"), "utf-8");
            writer.write(utfil + "\n__________________________");

            for (String string : kanal) {
                if (string != null) {
                    writer.write("\n\n" + string);
                }
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Ble ikke skrevet til fil.");
        }
    }
}
