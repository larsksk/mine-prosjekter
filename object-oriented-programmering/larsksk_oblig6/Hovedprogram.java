import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hovedprogram {
    public static Lock laas = new ReentrantLock();
    public static Condition vent = laas.newCondition();

    public static void main(String[] args) {
        int antallTelegrafister = 3;
        int antallKryptografer = 20;

        Operasjonssentral ops = new Operasjonssentral(antallTelegrafister);
        Kanal[] kanaler = ops.hentKanalArray();

        Monitor kryptert = new Monitor();
        Monitor dekryptert = new Monitor();

        // Starter telegrafistene
        for (int i = 0; i < antallTelegrafister; i++) {
            new Thread(new Telegrafist(kanaler[i], kryptert)).start();
        }

        // Starter kryptografene
        for (int j = 0; j < antallKryptografer; j++) {
            new Thread(new Kryptograf(kryptert, dekryptert)).start();
        }

        // Venter paa vlsuttelse av telegrafistene
        while (Telegrafist.ant_tele > 0) {
            try {
                vent.await();
            } catch (Exception e) {}
        }

        // Venter paa vsluttelse av kryptografer
        while (Kryptograf.ant_krypto > 0) {
            try {
                vent.await();
            } catch (Exception e) {}
        }

        // Starter operasjonslederen
        new Thread(new Operasjonsleder(dekryptert)).start();
        System.out.println("Program ferdig.");
    }
}
