public class Aapning extends HvitRute {

    Aapning(int rad, int kol, Labyrint labyrint){
        super(rad, kol, labyrint);
    }

    public void gaa(String vei) {
        // Naar veien treffer en aapning, vil veien returneres som en gyldig utvei.
        vei += toString();
        labyrint.utveier.leggTil(vei);
    }

    public char tilTegn() {
        return '.';
    }
}
