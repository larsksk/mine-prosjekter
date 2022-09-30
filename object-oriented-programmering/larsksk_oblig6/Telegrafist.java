public class Telegrafist implements Runnable{
    public static int ant_tele = 0;
    private String melding;
    private Kanal kanal;
    private Monitor monitor;

    Telegrafist (Kanal kanal, Monitor monitor) {
        this.kanal = kanal;
        this.monitor = monitor;
    }

    public void run() {
        ant_tele++;
        int kanalid = kanal.hentId();
        int meldingnr = 0;

        // Lytter etter meldinger og sender dem til monitoren for krypterte meldinger,
        // til det ikke er flere og "null" blir returnert.
        melding = kanal.lytt();
        while (melding != null) {
            Melding m = new Melding(melding, meldingnr, kanalid);
            monitor.leggTil(m);
            meldingnr++;
            melding = kanal.lytt();
        }
        ant_tele--;

        try {
            Hovedprogram.vent.signalAll();
        } catch (Exception e) {}
    }
}
