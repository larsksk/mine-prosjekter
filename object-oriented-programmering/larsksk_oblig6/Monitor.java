public class Monitor {
    private Stabel<Melding> stabel = new Stabel<>();

    public void leggTil(Melding melding) {
        // Laaser metoden slik at ikke flere bruker det samtidig, og laaser det opp naar metoden er ferdig.
        // Legger til meldinger i stabelen.
        Hovedprogram.laas.lock();
        try {
            stabel.leggPaa(melding);
            Hovedprogram.vent.signalAll();
        } catch (Exception e) {
            System.out.println("Noe gikk galt med å legge til melding.");
        }
        finally {
            Hovedprogram.laas.unlock();
        }
    }

    public Melding taAv(){
        // Laaser og laaser opp paa samme maate som leggTil-metoden.
        // Henter ut meldinger saa lenge det er meldinger aa hente ut.
        Hovedprogram.laas.lock();
        try {
            while(stabel.stoerrelse() == 0 && Telegrafist.ant_tele > 0) {
                Hovedprogram.vent.await();
            }
            return stabel.taAv();

        } catch (Exception e) {
            return null;
        } finally {
            Hovedprogram.laas.unlock();
        }
    }
}