import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Labyrint klassen tar vare paa alle rutene sine, hvor mange rader og kolonner det er,
// antall utveier og den raskeste utveien.
public class Labyrint {
    private Rute[][] ruter;
    public int rader, kolonner;
    Lenkeliste<String> utveier;
    Lenkeliste<String> raskUtvei;

    private Labyrint(Rute[][] ruter, int rader, int kolonner) {
        this.ruter = ruter;
        this.rader = rader;
        this.kolonner = kolonner;
    }

    public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        Scanner scanner = new Scanner(fil);
        // Itererer over de to foerste dataene, og leser da inn antall rader og kolonner i labyrinten.
        int rader = scanner.nextInt();
        int kolonner = scanner.nextInt();
        // Lager saa rutenettet med antall rader og kolloner lest inn.
        Rute[][] ruter = new Rute[rader][kolonner];
        // Lager Labyrint-objektet med det tomme rutenettet.
        Labyrint labyrint = new Labyrint(ruter, rader, kolonner);
        scanner.nextLine();

        // Leser inn verdiene til hver av rutene i labyrinten fra fil, linje for linje.
        // Setter saa rutene til Hvit, Sort eller Aaåning.
        for (int rad = 0; rad < rader; rad++) {
            char[] tegn = scanner.nextLine().toCharArray();
            for (int kol = 0; kol < kolonner; kol++) {
                ruter[rad][kol] = Rute.lagRute(tegn[kol], rad, kol, labyrint);
            }
        }

        // Gir hver av rutene naboer.
        for (int i = 0; i < rader; i++) {
            for (int j = 0; j < kolonner; j++) {
                if (i > 0) {
                    ruter[i][j].nord = ruter[i-1][j];
                }
                if (i < rader-1) {
                    ruter[i][j].soer = ruter[i+1][j];
                }
                if (j > 0) {
                    ruter[i][j].vest = ruter[i][j-1];
                }
                if (j < kolonner-1) {
                    ruter[i][j].oest = ruter[i][j+1];
                }
            }
        }
        System.out.println(labyrint.toString());
        return labyrint;
    }

    public Lenkeliste<String> finnUtveiFra(int kol, int rad) {
        utveier = new Lenkeliste<>();
        raskUtvei = new Lenkeliste<>();
        ruter[rad][kol].finnUtvei();
        int shortest = 0;
        // Finner antall ruter som passeres i den korteste utveien.
        for (String utvei : utveier) {
            String[] antRuter = utvei.split(" --> ");
            int len = antRuter.length;
            if (shortest == 0 || len < shortest) {
                shortest = len;
            }
        }
        // Finner alle utveier som har lengde = korteste utvei (i tilfelle det er flere utveier som er like korte)
        for (String utvei : utveier) {
            String[] antRuter = utvei.split(" --> ");
            if (antRuter.length == shortest) {
                raskUtvei.leggTil(utvei);
            }
        }

        // Returnerer her raskeste utvei (raskUtvei), bytt til "return utveier" for aa returnere alle utveier.
        return raskUtvei;
        //return utveier;
    }

    public String toString() {
        String retur = "";
        for (Rute[] rad : ruter) {
            for (Rute rute : rad) {
                retur += rute.tilTegn();
            }
            retur += "\n";
        }
        return retur;
    }
}
