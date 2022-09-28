public class Melding {
    private String melding;
    private int sekvensnr;
    private int kanalid;

    Melding(String melding, int sekvensnr, int kanalid) {
        this.melding = melding;
        this.sekvensnr = sekvensnr;
        this.kanalid = kanalid;
    }

    public int getKanalid() {
        return kanalid;
    }

    public int getSekvensnr() {
        return sekvensnr;
    }

    public String toString() {
        return melding;
    }
}
