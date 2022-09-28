public class SortRute extends Rute {

    SortRute(int rad, int kol, Labyrint labyrint){
        super(rad, kol, labyrint);
    }

    public void gaa(String retning) {
        // Svarte ruter er en vegg, så veien her stopper bare.
    }

    public char tilTegn() {
        return '#';
    }
}
