import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

// Valgte aa legge alle metodene tilhørende selve legesystemet i samme fil under samme classe, Legesystem. Er usikker
// paa om det hadde vaert mer ryddig aa gjoere det paa en annen maate. Proevde aa dele det opp, og det ble da veldig
// mange filer, som jeg foelte ble uryddig, men jeg kan ta feil her. Tar gjerne tilbakemelding angaaende dette valget.

public class Legesystem {
    // Lager tomme listene for programmets lagrede elementer.
    private static Lenkeliste<Pasient> pasienter = new Lenkeliste<>();
    private static SortertLenkeliste<Lege> leger = new SortertLenkeliste<>();
    private static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<>();
    private static Lenkeliste<Resept> resepter = new Lenkeliste<>();

    // Main metoden starter legesystemprogrammet med metoden start().
    public static void main(String[] args) {
        start();
    }

    // Starter legesystemprogrammet og initierer lesFraFil() og deretter hovedmeny() metodene.
    private static void start() {
        Scanner system = new Scanner(System.in);
        System.out.println("Program startet.\n");
        lesFraFil(system);
        hovedmeny(system);
    }

    // Spoerr brukeren om den vil lese inn elementer fra. Leser, deler opp og lagrer data fra fil. Initierer deretter
    // metodene for aa legge til elementene med lagrede data. Teller opp antall vellykkede elementer lest inn og
    // lagt til i systemet, og skriver saa dette ut til brukeren.
    private static void lesFraFil(Scanner system) {
        int pasientCount = 0;
        int legemiddelCount = 0;
        int legeCount = 0;
        int reseptCount = 0;

        Lenkeliste<String> alts = new Lenkeliste<>();
        String melding;
        String feil;

        alts.leggTil("y");
        alts.leggTil("n");
        melding = "Vil du lese inn elementer fra fil? (y/n)";
        feil = "Vennligst svar yes(y) eller nei(n).";
        if(innAltsString(system, melding, feil, alts).equals("n")){
            return;
        }

        melding = "Filnavn for innlesning:";
        feil = "Vennligst oppgi et ikke-tomt filnavn med .txt paa enden.";
        String filename = innString(system, melding, feil);
        Scanner file;
        String line;
        try {
            file = new Scanner(new File(filename));
        }
        catch (FileNotFoundException e) {
            System.out.println("Finner ikke filen '" + filename + "'");
            lesFraFil(system);
            return;
        }

        // Leser gjennom linjene i filen for hver av elementene i en gitt rekkefølge. Ser etter '#' for neste type.
        file.nextLine();
        line = file.nextLine();
        while (line.charAt(0) != '#') {
            String[] data = line.split(", ");
            if(leggTilPasientFraFil(data)) {
                pasientCount++;
            }
            line = file.nextLine();
        }
        line = file.nextLine();
        while (line.charAt(0) != '#') {
            String[] data = line.split(", ");
            if(leggTilLegemiddelFraFil(data)) {
                legemiddelCount++;
            }
            line = file.nextLine();
        }
        line = file.nextLine();
        while (line.charAt(0) != '#') {
            String[] data = line.split(", ");
            if(leggTilLegeFraFil(data)) {
                legeCount++;
            }
            line = file.nextLine();
        }
        while (file.hasNextLine()) {
            line = file.nextLine();
            String[] data = line.split(", ");
            if(leggTilReseptFraFil(data)) {
                reseptCount++;
            }
        }
        file.close();

        // Skriver ut informasjon om hvor mange av hver av typene elementer som ble lagt til fra filen.
        System.out.println("Registrerte");
        if (pasientCount == 1) {
            System.out.print(pasientCount + " pasient, ");
        }
        else if (pasientCount > 1) {
            System.out.print(pasientCount + " pasienter, ");
        }
        if (legemiddelCount == 1) {
            System.out.print(legemiddelCount + " legemiddel, ");
        }
        else if (legemiddelCount > 1) {
            System.out.print(legemiddelCount + " legemidler, ");
        }
        if (legeCount == 1) {
            System.out.print(legeCount + " lege, ");
        }
        else if (legeCount > 1) {
            System.out.print(legeCount + " leger, ");
        }
        if (reseptCount == 1) {
            System.out.println(reseptCount + " resept,");
        }
        else if (reseptCount > 1) {
            System.out.println(reseptCount + " resepter,");
        }
        System.out.println("fra fil.\n");
    }

    // Metoder for hver av de 4 elementene som kan legges til fra fil.
    private static boolean leggTilPasientFraFil(String[] data) {
        Pasient pasient = new Pasient(data[0], data[1]);
        pasienter.leggTil(pasient);
        return true;
    }

    private static boolean leggTilLegemiddelFraFil(String[] data) {
        Legemiddel legemiddel;
        switch (data[1]){
            case "a":
                legemiddel = new PreparatA(data[0], Double.parseDouble(data[2]),
                        Double.parseDouble(data[3]), Integer.parseInt(data[4]));
                break;
            case "b" :
                legemiddel = new PreparatB(data[0], Double.parseDouble(data[2]),
                        Double.parseDouble(data[3]), Integer.parseInt(data[4]));
                break;
            case "c" :
                legemiddel = new PreparatC(data[0], Double.parseDouble(data[2]),
                        Double.parseDouble(data[3]));
                break;
            default :
                return false;
        }
        legemidler.leggTil(legemiddel);
        return true;
    }

    private static boolean leggTilLegeFraFil(String[] data) {
        Lege lege;
        if (data[1].equals("0")) {
            lege = new Lege(data[0]);
        }
        else {
            lege = new Spesialist(data[0], Integer.parseInt(data[1]));
        }
        leger.leggTil(lege);
        return true;
    }

    private static boolean leggTilReseptFraFil(String[] data) {
        Resept resept;
        Legemiddel legemiddel = legemidler.hent(Integer.parseInt(data[0]));
        Pasient pasient = pasienter.hent(Integer.parseInt(data[2]));
        Lege lege = null;
        for (Lege l : leger) {
            if (l.hentLege().equals(data[1])) {
                lege = l;
                break;
            }
        }
        if(lege == null) {
            return false;
        }

        try {
            resept = lege.skrivHvitResept(legemiddel, pasient, Integer.parseInt(data[3]));
        }
        catch(UlovligUtskrift e) {
            return false;
        }
        resepter.leggTil(resept);
        return true;
    }

    // Hovedmenyen for systemet. Skriver ut alternativene brukeren har i menyen.
    private static void hovedmeny(Scanner system) {
        Lenkeliste<String> kommandoer = new Lenkeliste<>();
        kommandoer.leggTil("1:  Skriv ut data\n");
        kommandoer.leggTil("2:  Legg til nytt element\n");
        kommandoer.leggTil("3:  Bruk resept\n");
        kommandoer.leggTil("4:  Skriv ut statistikk\n");
        kommandoer.leggTil("5:  Skriv data til fil\n");
        kommandoer.leggTil("0:  Avslutt programmet");
        int menyNr = kommandoer.stoerrelse() - 1;

        System.out.println("HOVEDMENY\nHva vil du gjoere?");
        kommandoer.skrivUt();

        boolean kjoerer = true;
        while(kjoerer) {
            switch (system.nextLine()) {

                case "1":
                    menySkrivUt(system);
                    System.out.println("HOVEDMENY\nHva vil du gjoere?");
                    kommandoer.skrivUt();
                    break;

                case "2":
                    menyLeggTil(system);
                    System.out.println("HOVEDMENY\nHva vil du gjoere?");
                    kommandoer.skrivUt();
                    break;

                case "3":
                    brukResept(system);
                    break;

                case "4":
                    menyStatistikk(system);
                    System.out.println("HOVEDMENY\nHva vil du gjoere?");
                    kommandoer.skrivUt();
                    break;

                case "5":
                    System.out.println("Skriver data til fil...");
                    skrivTilFil(system);
                    break;

                case "0":
                    avslutte(system);
                    kjoerer = false;
                    System.out.print("Program avsluttet.");
                    break;

                default:
                    System.out.println("Ugyldig valg. Vennligst velg et alternativ, (0-" + menyNr + ").");
                    break;
            }
        }
    }

    // Brukeren blir faar spoersmaal om den vil skrive elementene lagret til fil, naar den avslutter programmet.
    private static void avslutte(Scanner system) {
        Lenkeliste<String> alts = new Lenkeliste<>();
        String melding;
        String feil;
        alts.leggTil("y");
        alts.leggTil("n");
        melding = "Vil du lagre endringene dine,\nved aa skrive data til fil foer du avlsutter? (y/n)";
        feil = "Vennligst svar y (yes) eller n (nei).";
        if(innAltsString(system, melding, feil, alts).equals("y")){
            skrivTilFil(system);
        }
    }

    // Metode for aa bruke resept.
    private static void brukResept(Scanner system) {
        // Skjekker om det eksisterer resepter.
        if (resepter.stoerrelse() == 0) {
            System.out.println("Ingen registrerte resepter.");
            return;
        }
        Resept resept;
        String melding;
        String feil;

        // Ser gjennom alle pasienter registrert som har registrerte resepter og gir brukeren valg om hvem av pasientene
        // den bil bruke en resept av.
        melding = "Hvilken pasient vol du se resepter for:";
        feil = "Input maa vaere heltall innenfor rekkevidde. Proev igjen:";
        Lenkeliste<Pasient> reseptPasienter = new Lenkeliste<>();
        Lenkeliste<String> pasientS = new Lenkeliste<>();
        Lenkeliste<Integer> pasientAlts = new Lenkeliste<>();
        int pasientCount = 0;
        for (Pasient pasient : pasienter) {
            if (pasient.hentResepter().stoerrelse() > 0) {
                reseptPasienter.leggTil(pasient);
                pasientS.leggTil(pasientCount + ": " + pasient.hentNavn() + " (fnr " + pasient.hentFnr() + ")\n");
                pasientAlts.leggTil(pasientCount);
                pasientCount++;
            }
        }
        if (pasientCount == 0) {
            System.out.println("Ingen registrerte pasienter med resepter.");
            return;
        }
        Pasient pasient = reseptPasienter.hent(innAltsInt(system, melding, feil, pasientAlts, pasientS));
        System.out.println("Valgt pasient: " + pasient.hentNavn() + " (fnr " + pasient.hentFnr() + ")");

        // Gir brukeren et valg om hvilken resept den vil bruke, fra valgt pasient.
        melding = "Hvilken resept vil du bruke?";
        feil = "Input maa vaere heltall innenfor rekkevidde. Proev igjen:";
        Lenkeliste<String> reseptS = new Lenkeliste<>();
        Lenkeliste<Integer> reseptAlts = new Lenkeliste<>();
        int reseptCount = 0;
        for (Resept res : pasient.hentResepter()) {
            reseptS.leggTil(reseptCount + ": " + res.hentLegemiddel().hentNavn() +
                    " (" + res.hentReit() + " reit)\n");
            reseptAlts.leggTil(reseptCount);
            reseptCount++;
        }
        resept = pasient.hentResepter().hent(innAltsInt(system, melding, feil, reseptAlts, reseptS));
        if(resept.bruk()) {
            System.out.println("Brukte resept paa " + resept.hentLegemiddel().hentNavn() +
                    ". Antall gjenvaerende reit: " + resept.hentReit());
        }
        else {
            System.out.println("Kan ikke brukes resept paa " + resept.hentLegemiddel().hentNavn()
                    + " (ingen gjenvaerende reit).");
        }
    }

    // Metode for aa skrive alle lagrede elementer i systemet til fil. Skriver til fil på samme format som filer til
    // innlesning, slik at programmet kan lese inn sine egen utskrevende filer.
    private static void skrivTilFil(Scanner system) {
        Lenkeliste<Lenkeliste<String>> lenker = new Lenkeliste<>();
        String melding = "Filnavn:";
        String feil = "Vennligst oppgi et ikke-tomt filnavn med .txt paa enden.";
        String filename = innString(system, melding, feil);
        try {
            PrintStream writer = new PrintStream(filename);
            lenker.leggTil(skrivFilPasienter());
            lenker.leggTil(skrivFilLegemidler());
            lenker.leggTil(skrivFilLeger());
            lenker.leggTil(skrivFilResepter());
            for (Lenkeliste<String> lenke : lenker) {
                for (String string : lenke) {
                    writer.println(string);
                }
            }
            writer.close();
            System.out.println("Fil skrevet ut.\n");
        }
        catch (FileNotFoundException e) {
            System.out.println("Fil ikke skrevet ut.");
        }
    }

    // Metoder for hver av de 4 forskjellige objektene, som returnerer lister av Strings som skal skrives til fil.
    private static Lenkeliste<String> skrivFilPasienter() {
        Lenkeliste<String> sPasienter = new Lenkeliste<>();
        sPasienter.leggTil("# Pasienter (navn, fnr)");
        if(pasienter.stoerrelse() == 0) {
            System.out.println("Ingen registrerte pasienter.");
        }
        else {
            for (Pasient pasient : pasienter) {
                sPasienter.leggTil(pasient.hentNavn() + ", " + pasient.hentFnr());
            }
        }
        return sPasienter;
    }

    private static Lenkeliste<String> skrivFilLegemidler() {
        Lenkeliste<String> sLegemidler = new Lenkeliste<>();
        sLegemidler.leggTil("# Legemidler (navn, type, pris, virkestoff [, styrke])");
        if(legemidler.stoerrelse() == 0) {
            System.out.println("Ingen registrerte legemidler.");
        }
        else {
            for (Legemiddel legemiddel : legemidler) {
                String styrke = ", 0";
                if (legemiddel instanceof PreparatA) {
                    styrke = ", " + ((PreparatA) legemiddel).hentNarkotiskStyrke();
                }
                else if (legemiddel instanceof PreparatB) {
                    styrke = ", " + ((PreparatB) legemiddel).hentVanedannendeStyrke();
                }
                sLegemidler.leggTil(legemiddel.hentNavn() + ", " + legemiddel.hentType() + ", " + legemiddel.hentPris() + ", " + legemiddel.hentVirkestoff() + styrke);
            }
        }
        return sLegemidler;
    }

    private static Lenkeliste<String> skrivFilLeger() {
        Lenkeliste<String> sLeger = new Lenkeliste<>();
        sLeger.leggTil("# Leger (navn, kontrollid / 0 hvis vanlig lege)");
        if(leger.stoerrelse() == 0) {
            System.out.println("Ingen registrerte leger.");
        }
        else {
            for (Lege lege : leger) {
                String id = ", 0";
                if(lege instanceof Spesialist){
                    id = ", " + ((Spesialist) lege).hentKontrollID();
                }
                sLeger.leggTil(lege.hentLege() + id);
            }
        }
        return sLeger;
    }

    private static Lenkeliste<String> skrivFilResepter() {
        Lenkeliste<String> sResepter = new Lenkeliste<>();
        sResepter.leggTil("# Resepter (legemiddelNummer, legeNavn, pasientID, reit)");
        if(resepter.stoerrelse() == 0) {
            System.out.println("Ingen registrerte resepter.");
        }
        else {
            for (Resept resept : resepter) {
                sResepter.leggTil(resept.hentLegemiddel().hentId() + ", " + resept.hentLege() + ", " + resept.hentPasientId() + ", " + resept.hentReit());
            }
        }
        return sResepter;
    }

    // Meny for aa skrive ut informasjon for lagrede elementer i systemet.
    private static void menySkrivUt(Scanner system) {
        Lenkeliste<String> kommandoer = new Lenkeliste<>();
        kommandoer.leggTil("1:  Alle\n");
        kommandoer.leggTil("2:  Pasienter\n");
        kommandoer.leggTil("3:  Legemidler\n");
        kommandoer.leggTil("4:  Leger\n");
        kommandoer.leggTil("5:  Resepter\n");
        kommandoer.leggTil("0:  Tilbake til hovedmenyen");
        int menyNr = kommandoer.stoerrelse() - 1;

        System.out.print("SKRIV UT\nHva vil du skrive ut?\n");
        kommandoer.skrivUt();

        boolean kjoerer = true;
        while(kjoerer) {
            switch (system.nextLine()) {

                case "1":
                    skrivUtAlle();
                    break;

                case "2":
                    skrivUtPasienter();
                    break;

                case "3":
                    skrivUtLegemidler();
                    break;

                case "4":
                    skrivUtLeger();
                    break;

                case "5":
                    skrivUtResepter();
                    break;

                case "0":
                    kjoerer = false;
                    break;

                default:
                    System.out.println("Ugyldig valg. Vennligst velg et alternativ, (0-" + menyNr + ").");
                    break;
            }
        }
    }

    // Metoder for valgt type element til utskrivelse, inkludert en metode som skriver ut alt.
    private static void skrivUtAlle() {
        skrivUtPasienter();
        skrivUtLegemidler();
        skrivUtLeger();
        skrivUtResepter();
    }

    private static void skrivUtPasienter() {
        if(pasienter.stoerrelse() == 0) {
            System.out.println("Ingen registrerte pasienter.");
        }
        else {
            System.out.println("Pasienter:  (Id, Navn, Fnr)");
            pasienter.skrivUt();
        }
    }

    private static void skrivUtLeger() {
        if(leger.stoerrelse() == 0) {
            System.out.println("Ingen registrerte leger.");
        }
        else {
            System.out.println("Leger:  (Navn, Type, KontrollId)");
            leger.skrivUt();
        }
    }

    private static void skrivUtLegemidler() {
        if(legemidler.stoerrelse() == 0) {
            System.out.println("Ingen registrerte legemidler");
        }
        else {
            System.out.println("Legemidler:     (Id, Navn, Type, Pris, Virkestoff, Styrke)");
            legemidler.skrivUt();
        }
    }

    private static void skrivUtResepter() {
        if(resepter.stoerrelse() == 0) {
            System.out.println("Ingen registrerte resepter");
        }
        else {
            System.out.println("Resepter:   (Id, Legemiddel, Utskrivende lege, Farge, PasientId, Reit, Pris)");
            resepter.skrivUt();
        }
    }

    // Meny for utskrivelse av diverse statistikk.
    private static void menyStatistikk(Scanner system) {
        Lenkeliste<String> kommandoer = new Lenkeliste<>();
        kommandoer.leggTil("1:  Alle\n");
        kommandoer.leggTil("2:  Resepter paa vanedannede legemidler\n");
        kommandoer.leggTil("3:  Resepter paa narkotiske legemidler\n");
        kommandoer.leggTil("4:  Misbruk\n");
        kommandoer.leggTil("0:  Tilbake til hovedmenyen");
        int menyNr = kommandoer.stoerrelse() - 1;

        System.out.print("SKRIV UT STATISTIKK\nHvilken statistikk vil du skrive ut?\n");
        kommandoer.skrivUt();

        boolean kjoerer = true;
        while(kjoerer) {
            switch (system.nextLine()) {

                case "1":
                    statistikkAlle();
                    break;

                case "2":
                    statistikkVanedannede();
                    break;

                case "3":
                    statistikkNarkotisk();
                    break;

                case "4":
                    statistikkMisbruk();
                    break;

                case "0":
                    kjoerer = false;
                    break;

                default:
                    System.out.println("Ugyldig valg. Vennligst velg et alternativ, (0-" + menyNr + ").");
                    break;
            }
        }
    }

    // Metode for hver av statistikkene som kan skrives ut, inkludert en metode for aa skrive ut all statistikk.
    private static void statistikkAlle() {
        statistikkVanedannede();
        statistikkNarkotisk();
        statistikkMisbruk();
    }

    private static void statistikkVanedannede() {
        int count = 0;
        for (Resept resept : resepter) {
            if (resept.legemiddel.hentType().equals("b")) {
                count++;
            }
        }
        System.out.println("Antall utskrevne resepter på vanedannende legemidler: " + count);
    }

    private static void statistikkNarkotisk(){
        int count = 0;
        for (Resept resept : resepter) {
            if (resept.legemiddel.hentType().equals("a")) {
                count++;
            }
        }
        System.out.println("Antall utskrevne resepter på narkotiske legemidler:  " + count);
    }

    private static void statistikkMisbruk(){
        Lenkeliste<String> sLege = new Lenkeliste<>();
        sLege.leggTil("Leger som har skrevet ut resept paa narkotiske legemidler,\n" +
                "og antall slike resepter skrevet:");
        for (Lege lege : leger) {
            int count = 0;
            Lenkeliste<Resept> legeResepter = lege.hentResepter();
            for (Resept resept : legeResepter) {
                if (resept.hentLegemiddel().hentType().equals("a")) {
                    count++;
                }
            }
            if (count > 0) {
                sLege.leggTil("\n" + lege.hentLege() + ", " + count);
            }
        }
        if (sLege.stoerrelse() == 1) {
            System.out.println("Ingen leger har skrevet ut resepter paa narkotiske stoffer.");
        }
        else {
            sLege.skrivUt();
        }

        Lenkeliste<String> sPasient = new Lenkeliste<>();
        sPasient.leggTil("\nPasienter som resept paa narkotiske legemidler,\n" +
                "og antall slike resepter personen har:");
        for (Pasient pasient : pasienter) {
            int count = 0;
            Lenkeliste<Resept> pasientResepter = pasient.hentResepter();
            for (Resept resept : pasientResepter) {
                if (resept.hentLegemiddel().hentType().equals("a")) {
                    count++;
                }
            }
            if (count > 0) {
                sPasient.leggTil("\n" + pasient.hentNavn() + ", " + count);
            }
        }
        if (sLege.stoerrelse() == 1) {
            System.out.println("Ingen pasienter har resepter paa narkotiske stoffer.");
        }
        else {
            sPasient.skrivUt();
        }
    }

    // Meny for äa velge hvilket type element som skal legges til i systemet.
    private static void menyLeggTil(Scanner system) {
        Lenkeliste<String> kommandoer = new Lenkeliste<>();
        kommandoer.leggTil("1:  Pasient\n");
        kommandoer.leggTil("2:  Legemiddel \n");
        kommandoer.leggTil("3:  Lege\n");
        kommandoer.leggTil("4:  Resept\n");
        kommandoer.leggTil("0:  Tilbake til hovedmenyen");
        int menyNr = kommandoer.stoerrelse() - 1;

        System.out.println("LEGG TIL\nHva vil du legge til?");
        kommandoer.skrivUt();

        boolean kjoerer = true;
        while(kjoerer) {
            switch (system.nextLine()) {

                case "1":
                    leggTilPasient(system);
                    break;

                case "2":
                    leggTilLegemiddel(system);
                    break;

                case "3":
                    leggTilLege(system);
                    break;

                case "4":
                    leggTilResept(system);
                    break;

                case "0":
                    kjoerer = false;
                    break;

                default:
                    System.out.println("Ugyldig valg. Vennligst velg et alternativ, (0-" + menyNr + ").");
                    break;
            }
        }
    }

    // Metoder for aa legge til et nytt element manuelt. En for hver type element.
    // Bruker metoder for aa ta inn informasjon fra brukeren om elementene som skal legges til.
    private static void leggTilLege(Scanner system) {
        Lege lege;
        String melding;
        String feil;

        melding = "Oppgi navn paa lege:";
        feil = "Vennligst oppgi et ikke-tomt navn. Proev igjen.";
        String navn = innString(system, melding, feil);

        melding = "Oppgi kontrollId (0 hvis vanlig lege):";
        feil = "Vennligst oppgi et heltall. Proev igjen.";
        int id = innInt(system, melding, feil);

        if (id == 0) {
            lege = new Lege(navn);
        }
        else{
            lege = new Spesialist(navn, id);
        }
        leger.leggTil(lege);
        System.out.println("Ny lege, " + navn + ", registrert.\n");
    }

    private static void leggTilPasient(Scanner system) {
        Pasient pasient;
        String melding;
        String feil;

        melding = "Navn:";
        feil = "Vennligst oppgi et ikke-tomt navn:";
        String navn = innString(system, melding, feil);

        melding = "Foedselsnummer";
        feil = "Vennligst oppgi et ikke-tomt foedselsnummer;";
        String fnr = innString(system, melding, feil);

        pasient = new Pasient(navn, fnr);
        pasienter.leggTil(pasient);
        System.out.println("Ny pasient, " + navn + ", registrert.\n");
    }

    private static void leggTilResept(Scanner system) {
        // Skjekker foesrt om det elemtene som kreves for at en resept skal skrives, allerede eksisterer i systemet,
        // og printer ut hva slags elementer som ikke eksisterer om det er noen.
        if (leger.stoerrelse() == 0 || legemidler.stoerrelse() == 0 || pasienter.stoerrelse() == 0) {
            if (leger.stoerrelse() == 0) {
                System.out.println("Ingen registrerte leger til aa skrive ut resept.");
            }
            if (legemidler.stoerrelse() == 0) {
                System.out.println("Ingen registrerte legemidler aa skrive resept paa.");
            }
            if (pasienter.stoerrelse() == 0) {
                System.out.println("Ingen registrerte pasienter aa skrive resept til.");
            }
            System.out.println("Ingen resept registrert.\n");
            return;
        }

        // Spoerr om input fra brukeren for all data som trengs for aa lage et resept objekt. Lister opp eksisterende
        // legemidler, leger og pasienter aa velge mellom.
        Resept resept;
        String melding;
        String feil;

        Lenkeliste<String> typer = new Lenkeliste<>();
        typer.leggTil("hvit");
        typer.leggTil("prevensjon");
        typer.leggTil("militaer");
        typer.leggTil("blaa");
        melding = "Type: (hvit, prevensjon, militaer, blaa)";
        feil = "Type maa vaere hvit, prevensjon, militaer, eller blaa. Proev igjen:";
        String type = innAltsString(system, melding, feil, typer);

        melding = "Velg legemiddel: (type)";
        feil = "Input maa vaere heltall innenfor rekkevidde. Proev igjen:";
        Lenkeliste<String> lmiddelS = new Lenkeliste<>();
        Lenkeliste<Integer> lmiddelAlts = new Lenkeliste<>();
        int lmiddelCount = 0;
        for (Legemiddel lmiddel : legemidler) {
            lmiddelS.leggTil(lmiddel.hentId() + ": " + lmiddel.hentNavn() + " (" + lmiddel.hentType() + ")\n");
            lmiddelAlts.leggTil(lmiddelCount);
            lmiddelCount++;
        }
        Legemiddel legemiddel = legemidler.hent(innAltsInt(system, melding, feil, lmiddelAlts, lmiddelS));

        melding = "Velg lege:";
        feil = "Input maa vaere heltall innenfor rekkevidde. Proev igjen:";
        Lenkeliste<String> legeS = new Lenkeliste<>();
        Lenkeliste<Integer> legeAlts = new Lenkeliste<>();
        int legeCount = 0;
        for (Lege lege : leger) {
            legeS.leggTil(legeCount + ": " + lege.toString());
            legeAlts.leggTil(legeCount);
            legeCount++;
        }
        Lege lege = leger.hent(innAltsInt(system, melding, feil, legeAlts, legeS));

        melding = "Velg pasient:";
        feil = "Input maa vaere heltall innenfor rekkevidde. Proev igjen:";
        Lenkeliste<String> pasientS = new Lenkeliste<>();
        Lenkeliste<Integer> pasientAlts = new Lenkeliste<>();
        int pasientCount = 0;
        for (Pasient pasient : pasienter) {
            pasientS.leggTil(pasient.hentId() + ": " + pasient.hentNavn() + " (fnr " + pasient.hentFnr() + ")\n");
            pasientAlts.leggTil(pasientCount);
            pasientCount++;
        }
        Pasient pasient = pasienter.hent(innAltsInt(system, melding, feil, pasientAlts, pasientS));

        int reit;
        if (!type.equals("prevensjon")) {
            melding = "Reit:";
            feil = "Reit maa vaere et heltall. Proev igjen:";
            reit = innInt(system, melding, feil);
        }
        else {
            reit = 3;
        }

        try {
            switch (type) {
                case "hvit":
                    resept = lege.skrivHvitResept(legemiddel, pasient, reit);
                    break;

                case "blaa":
                    resept = lege.skrivBlaaResept(legemiddel, pasient, reit);
                    break;

                case "militaer":
                    resept = lege.skrivMilitaerResept(legemiddel, pasient, reit);
                    break;

                case "prevensjon":
                    resept = lege.skrivPResept(legemiddel, pasient);
                    break;

                default:
                    System.out.println("Type resept ikke kjent igjen.");
                    return;
                }
        }
        catch (UlovligUtskrift e) {
            e.printStackTrace();
            System.out.println("Ingen resept registrert.\n");
            return;
        }
        resepter.leggTil(resept);
        System.out.println("Ny resept registrert.\n");
    }

    private static void leggTilLegemiddel(Scanner system) {
        Legemiddel legemiddel;
        String melding;
        String feil;

        Lenkeliste<String> alts = new Lenkeliste<>();
        alts.leggTil("a");
        alts.leggTil("b");
        alts.leggTil("c");
        melding = "Type: (a, b, c)";
        feil = "Type maa vaere a, b, eller c. Proev igjen:";
        String type = innAltsString(system, melding, feil, alts);

        melding = "Navn:";
        feil = "Vennligst angi et ikke-tomt navn:";
        String navn = innString(system, melding, feil);

        melding = "Pris:";
        feil = "Pris maa vare heltall eller desimaltall. Proev igjen:";
        double pris = innDouble(system, melding, feil);

        melding = "Virkestoff: (double)";
        feil = "Virkestoff maa vaere heltall eller desimaltall. Proev igjen:";
        double virkestoff = innDouble(system, melding, feil);

        if (type.equals("a") || type.equals("b")) {
            melding = "Styrke: (int)";
            feil = "Styrke maa vaere et heltall. Proev igjen:";
            int styrke = innInt(system, melding, feil);
            if(type.equals("a")) {
                legemiddel = new PreparatA(navn, pris, virkestoff, styrke);
            }
            else {
                legemiddel = new PreparatB(navn, pris, virkestoff, styrke);
            }
        }
        else {
            legemiddel = new PreparatC(navn, pris, virkestoff);
        }
        legemidler.leggTil(legemiddel);
        System.out.println("Nytt legemiddel, " + navn + ", registrert.\n");
    }

    // Metoder for aa ta inn input fra brukeren. Tar inn og skjekker om input er av riktig type.
    private static double innDouble(Scanner system, String melding, String feil) {
        double input = 0;
        boolean bool = true;

        System.out.println(melding);
        while(bool) {
            try {
                input = Double.parseDouble(system.nextLine());
                return input;
            }
            catch (Exception e) {
                System.out.println(feil);
            }
        }
        return input;
    }

    private static int innInt(Scanner system, String melding, String feil) {
        int input = 0;
        boolean bool = true;

        System.out.println(melding);
        while(bool) {
            try {
                input = Integer.parseInt(system.nextLine());
                return input;
            }
            catch (Exception e) {
                System.out.println(feil);
            }
        }
        return input;
    }

    private static String innString(Scanner system, String melding, String feil) {
        String input = "";
        boolean bool = true;

        System.out.println(melding);
        while(bool) {
            input = system.nextLine();
            if (input.equals("")) {
                System.out.println(feil);
            }
            else {
                bool = false;
            }
        }
        return input;
    }

    // Metoder for aa ta in input fra brukeren av en gitt liste alternativer.
    private static String innAltsString(Scanner system, String melding, String feil, Lenkeliste<String> alts) {
        String input = "";
        boolean bool = true;
        boolean equal = false;

        System.out.println(melding);
        while(bool) {
            input = system.nextLine();
            for (String alt: alts) {
                if(input.equals(alt)) {
                    equal = true;
                }
            }
            if (equal) {
                bool = false;
            }
            else {
                System.out.println(feil);
            }
        }
        return input;
    }

    private static int innAltsInt(Scanner system, String melding, String feil, Lenkeliste<Integer> alts, Lenkeliste<String> print) {
        int input = 0;
        boolean bool = true;
        boolean equal = false;

        System.out.println(melding);
        print.skrivUt();
        while(bool) {
            input = Integer.parseInt(system.nextLine());
            for (int alt: alts) {
                if(input == alt) {
                    equal = true;
                }
            }
            if (equal) {
                bool = false;
            }
            else {
                System.out.println(feil);
            }
        }
        return input;
    }
}
