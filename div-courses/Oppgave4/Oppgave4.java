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

            for (int j = 0; j<100; j++) {
                kryss[0] = alleKryss[Trekk.trekkInt(0, ANTKRYSS-1)];
                kryss[1] = alleKryss[Trekk.trekkInt(0, ANTKRYSS-1)];
                if(!kryss[0].finnesVeiTil(kryss[1], kryss[0])) {
                    break;
                }
            }

            if(kryss[0].finnesVeiTil(kryss[1])){
                System.out.println("Fant ingen veier som ikke eksisterte");
                return;
            }

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

    public boolean finnesVeiTil(Kryss kryss2, Kryss komFra) {
        for (Sti sti: stier){
            Kryss endepunkt = sti.finnAndreEndepunk(this);
            if (endepunkt != komFra){
                if (endepunkt == kryss2){
                    return true;
                }
                endepunkt.finnesVeiTil(kryss2, endepunkt);
            }
        }
        return false;
    }
}