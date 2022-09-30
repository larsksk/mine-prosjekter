public class HvitRute extends Rute {

    HvitRute(int rad, int kol, Labyrint labyrint){
        super(rad, kol, labyrint);
    }

    public void gaa(String vei) {
        // Om traad = true betyr det at denne veien har allerede vaert innom denne ruten,
        // og den veien vil da ikke bli lagret.
        if (traad) {
            return;
        }
        // Setter traad = true, slik at denne ruten naa har vaert besoekt.
        traad = true;
        // Lagrer denne ruten til veien som er tatt.
        vei += toString() + " --> ";
        // Proever alle veier fra denne ruten. Tar ikke hensyn til hvilken rute den kom fra, fordi den ruten
        // er naa merket med traad, og denne veien blir da ikke lagret.
        nord.gaa(vei);
        oest.gaa(vei);
        soer.gaa(vei);
        vest.gaa(vei);
        // Setter traad = false igjen, slik at programmet kan bli brukt igjen uten aa maatte avsluttes.
        traad = false;
    }

    public char tilTegn() {
        return '.';
    }
}
