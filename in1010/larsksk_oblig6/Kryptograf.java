public class Kryptograf implements Runnable{
    public static int ant_krypto = 0;
    private Monitor kryptert;
    private Monitor dekryptert;
    private Melding kryptertMelding;

    Kryptograf(Monitor kryptert, Monitor dekrypert) {
        this.kryptert = kryptert;
        this.dekryptert = dekrypert;
    }

    public void run() {
        ant_krypto++;
        // Henter ut krypterte meldinger og deres informasjon, dekrypterer de, sender de videre til
        // monitor for dekrypterte meldinger. Avlsutter naar det ikke er flere meldinger (faar returnert "null").
        kryptertMelding = kryptert.taAv();
        while (kryptertMelding != null) {
            int meldingid = kryptertMelding.getKanalid();
            int meldingnr = kryptertMelding.getSekvensnr();
            String melding = kryptertMelding.toString();

            String m = Kryptografi.dekrypter(melding);
            Melding dekryptertMelding = new Melding(m, meldingnr, meldingid);
            dekryptert.leggTil(dekryptertMelding);
            kryptertMelding = kryptert.taAv();
        }
        ant_krypto--;

        try {
            Hovedprogram.vent.signalAll();
        } catch (Exception e) {}
    }
}
