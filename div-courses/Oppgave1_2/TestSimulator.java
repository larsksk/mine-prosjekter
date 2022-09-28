import java.util.ArrayList;

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
}

class Trekk{
    public static int trekkInt(int min, int max){
        return (int)(Math.random()*((max - min) + 1)) + min;
    }
}


// OPPGAVE 2:


class Simulator {
    int globalTid = 0;
    PrioKo prioKo = new PrioKo();

    Simulator (Aktivitet[] aktiviteter) {
        for (int i = 0; i < aktiviteter.length; i++){
            prioKo.settInn(aktiviteter[i]);
        }
    }

    void simuler(int t) {
        while (globalTid < t) {
            Aktivitet a = prioKo.hentUt();
            globalTid = a.tid;
            a.handling();
            prioKo.settInn(a);
        }
    }
}

abstract class Aktivitet implements Comparable<Aktivitet>{
    Aktivitet neste;
    Aktivitet forrige;
    int tid;
    abstract void handling();
}

class PrioKo{
    Aktivitet prioForst;
    Aktivitet prioSist;

    void settInn(Aktivitet a) {
        if (prioForst == null) {
            prioForst = prioSist = a;
        }
        else if (prioSist.tid <= a.tid) {
            a.forrige = prioSist;
            prioSist.neste = a;
            prioSist = a;
        }
        else{
            Aktivitet node = prioSist;
            while (true) {
                if (node.tid <= a.tid) {
                    a.forrige = node;
                    node.neste = a;
                    break;
                }
                if (node.forrige == null) {
                    prioForst.forrige = a;
                    a = prioForst;
                }
                node = node.forrige;
            }
        }
    }

    Aktivitet hentUt() {
        Aktivitet hent = prioForst;
        if(prioForst==prioSist){
            prioForst = prioSist = null;
            return hent;
        }
        prioForst = prioForst.neste;
        prioForst.forrige = null;
        return hent;
    }
}

class Turgaaer extends Aktivitet{
    int hastighet;
    Kryss referanse;

    Turgaaer (int hastighet, Kryss referanse) {
        this. hastighet = hastighet;
        this.referanse = referanse;
    }

    void handling() {
        Sti vei = referanse.tilfeldigSti();
        referanse = vei.finnAndreEndepunk(referanse);
        tid += vei.beregnGaaTid(hastighet);
    }

    public int compareTo(Aktivitet a){
        return -1;
    }
}


public class TestSimulator {

    public static void main(String[] args) {
        Skog skog = new Skog();

        int antallTurgaaere = 10;
        Aktivitet[] turgaaere = new Aktivitet[antallTurgaaere];
        for (int i = 0; i < antallTurgaaere; i++){
            turgaaere[i] = new Turgaaer(Trekk.trekkInt(20, 200), skog.hentTilfeldigStart());
        }

        Simulator simulator = new Simulator(turgaaere);

        simulator.simuler(Trekk.trekkInt(30, 480));
    }
}