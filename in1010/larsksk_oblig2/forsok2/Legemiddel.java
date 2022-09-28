public class Legemiddel {
    public static int count = 0;
    public int id;
    public String navn;
    public double pris;
    public double virkestoff;

    Legemiddel(String navn, double pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.id = count++;
    }

    // Methods for returning the stored information
    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public double hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(double nyPris) {
        pris = nyPris;
    }
}
