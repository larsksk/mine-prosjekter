public class Pasient {
    public static int count = 0;
    private int id;
    private String navn;
    private String fnr;
    private Stabel<Resept> resepter = new Stabel<>();

    Pasient(String navn, String fnr) {
        this.id = count;
        count += 1;
        this.navn = navn;
        this.fnr = fnr;
    }

    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public String hentFnr() {
        return fnr;
    }

    public Stabel<Resept> hentResepter() {
        return resepter;
    }

    public void leggTilResept(Resept resept) {
        resepter.leggPaa(resept);
    }

    public String toString() {
        return hentId() + ", " + hentNavn() + ", " + hentFnr() + "\n";
    }
}
