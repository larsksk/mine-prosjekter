import java.util.ArrayList;
import java.util.ConcurrentModificationException;

// OPPGAVE 1:

interface Utsikt {
    int hentUtsikt();
}


class Sti implements Utsikt {
    final int lengde;
    final Kryss[] kryss;
    final int utsiktsVerdi;

    Sti(int lengde, Kryss[] kryss, int utsiktsVerdi){
        this.lengde = lengde;
        this.kryss = kryss;

        /*if (0 <= utsiktsVerdi && utsiktsVerdi <= 6){
            throw new RuntimeException();
        }*/
        this.utsiktsVerdi = utsiktsVerdi;
    }

    public Kryss finnAndreEndepunk(Kryss startPunkt){
        if (startPunkt==kryss[0]) {
            return kryss[1];
        }
        return kryss[0];
    }

    public int beregnGaaTid(int fart){
        return (lengde*100/fart*100)/100;
    }

    public int hentUtsikt() {
        return utsiktsVerdi;
    }
}

class Natursti extends Sti {
    Natursti (int lengde, Kryss[] kryss, int utsiktsVerdi) {
        super(lengde, kryss, utsiktsVerdi);
    }
}

class Kjerrevei extends Sti {
    Kjerrevei (int lengde, Kryss[] kryss, int utsiktsVerdi) {
        super(lengde, kryss, utsiktsVerdi);
    }
}

class Skog {
    int ANTSTIER;
    int ANTKRYSS;
    Kryss[] alleKryss;

    Skog (){
        ANTSTIER = 10;
        ANTKRYSS = 10;
        alleKryss = new Kryss[ANTKRYSS];

        for (int i = 0; i<ANTKRYSS; i++) {
            alleKryss[i] = new Kryss();
        }

        for (int i = 0; i <ANTSTIER; i++) {
            int lengde = Trekk.trekkInt(220, 2500);
            int utsiktsverdi = Trekk.trekkInt(1, 6);
            Sti sti;
            Kryss[] kryss = new Kryss[2];
            kryss[0] = alleKryss[Trekk.trekkInt(0, ANTKRYSS-1)];
            kryss[1] = alleKryss[Trekk.trekkInt(0, ANTKRYSS-1)];

            int type = Trekk.trekkInt(0, 4);
            if (type==0){
                sti = new Natursti(lengde, kryss, 0);
            }
            else if (type==1){
                sti = new Kjerrevei(lengde, kryss, 0);
            }
            else if (type==2){
                sti = new Natursti(lengde, kryss, utsiktsverdi);
            }
            else {
                sti = new Kjerrevei(lengde, kryss, utsiktsverdi);
            }
            kryss[0].stier.add(sti);
            kryss[1].stier.add(sti);
        }
    }

    public Kryss hentTilfeldigKryss(){
        return alleKryss[Trekk.trekkInt(0 , ANTKRYSS-1)];
    }

    public Kryss hentTilfeldigStart() {
        while(true){
            Kryss kryss = hentTilfeldigKryss();
            if(!kryss.isolert()){
                return kryss;
            }
        }
    }
}

class Kryss {
    static int ANTALL_SETER = 4;
    int ledigeSeter = 4;
    ArrayList<Sti> stier = new ArrayList<>();

    public Sti tilfeldigSti() {
        if (!isolert()) {
            int antall = stier.size();
            return stier.get(Trekk.trekkInt(0, antall-1));
        }
        return null;
    }

    public boolean isolert() {
        return stier.isEmpty();
    }

    public void turgaaerKommer() throws InterruptedException {
        if (ledigeSeter==0){

        }
        else {
            ledigeSeter -=1;
            Thread.sleep(Trekk.trekkInt(1,5));
            turgaaerGaar();
        }
    }

    public void turgaaerGaar() {
        ledigeSeter += 1;
    }
}

class Trekk{
    public static int trekkInt(int min, int max){
        return (int)(Math.random()*((max - min) + 1)) + min;
    }
}


class Turgaaer implements Runnable{
    int hastighet;
    Kryss referanse;
    int tid = 0;

    Turgaaer (int hastighet, Kryss referanse) {
        this. hastighet = hastighet;
        this.referanse = referanse;
    }

    void handling() throws InterruptedException {
        Sti vei = referanse.tilfeldigSti();
        referanse = vei.finnAndreEndepunk(referanse);
        tid += vei.beregnGaaTid(hastighet);
        Thread.sleep(tid*1000);
        referanse.turgaaerKommer();
    }

    public void run() {
        try {
            handling();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class TestTradSimulator{

    public static void main(String[] args) {
        Skog skog = new Skog();

        int antallTurgaaere = 10;
        Turgaaer[] turgaaere = new Turgaaer[antallTurgaaere];
        Thread[] trader = new Thread[antallTurgaaere];
        for (int i = 0; i < antallTurgaaere; i++){
            turgaaere[i] = new Turgaaer(Trekk.trekkInt(20, 200), skog.hentTilfeldigStart());
            trader[i] = new Thread(turgaaere[i]);
            trader[i].start();
        }

    }
}