public abstract class Rute {
    int rad, kol;
    public Labyrint labyrint;
    public Rute nord, oest, soer, vest;
    public boolean traad = false;

    Rute(int rad, int kol, Labyrint labyrint) {
        this.rad = rad;
        this.kol = kol;
        this.labyrint = labyrint;
    }

    // Tar inn tegn og koordinater og lager ruter. En hvit rute som ligger i kanten av labyrinten er en aapning.
    public static Rute lagRute(char tegn, int rad, int kol, Labyrint labyrint) {
        switch (tegn) {
            case '.':
                if (rad > 0 && kol > 0 && rad < labyrint.rader-1 && kol < labyrint.kolonner-1) {
                    return new HvitRute(rad, kol, labyrint);
                }
                else {
                    return new Aapning(rad, kol, labyrint);
                }
            case '#':
                return new SortRute(rad, kol, labyrint);
            default:
                throw new RuntimeException("");
        }
    }

    public void finnUtvei() {
        gaa("");
    }

    public abstract void gaa(String vei);

    public abstract char tilTegn();

    public String toString() {
        return "(" + kol + ", " + rad + ")";
    }
}
