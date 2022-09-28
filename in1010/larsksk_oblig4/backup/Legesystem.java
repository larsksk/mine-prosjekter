import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Legesystem {
    private static Lenkeliste<Pasient> pasienter = new Lenkeliste<>();
    private static SortertLenkeliste<Lege> leger = new SortertLenkeliste<>();
    private static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<>();
    private static Lenkeliste<Resept> resepter = new Lenkeliste<>();

    public static void main(String[] args) {
        Scanner system = new Scanner(System.in);
        System.out.println("Program startet.\n");
        lesFraFil(system);
        hovedmeny(system);
    }

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
        String type = data[0];
        Legemiddel legemiddel = legemidler.hent(Integer.parseInt(data[1]));
        Pasient pasient = pasienter.hent(Integer.parseInt(data[3]));
        Lege lege = null;
        for (Lege l : leger) {
            if (l.hentLege().equals(data[2])) {
                lege = l;
                break;
            }
        }
        if(lege == null) {
            return false;
        }
        switch (type) {
            case "hvit":
                resept = new Hvit(legemiddel, lege, pasient, Integer.parseInt(data[4]));
                break;
            case "militaer":
                resept = new Militaer(legemiddel, lege, pasient, Integer.parseInt(data[4]));
                break;
            case "prevensjon":
                resept = new Prevensjon(legemiddel, lege, pasient);
                break;
            case "blaa":
                resept = new Blaa(legemiddel, lege, pasient, Integer.parseInt(data[4]));
                break;
            default :
                return false;
        }
        resepter.leggTil(resept);
        return true;
    }

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

    private static void avslutte(Scanner system) {
        Lenkeliste<String> alts = new Lenkeliste<>();
        String melding;
        String feil;
        alts.leggTil("y");
        alts.leggTil("n");
        melding = "Vil du skrive data til fil foer du avlsutter? (y/n)";
        feil = "Vennligst svar y (yes) eller n (nei).";
        if(innAltsString(system, melding, feil, alts).equals("y")){
            skrivTilFil(system);
        }
    }

    private static void brukResept(Scanner system) {
        if (resepter.stoerrelse() == 0) {
            System.out.println("Ingen registrerte resepter.");
            return;
        }
        Resept resept;
        String melding;
        String feil;

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

    private static Lenkeliste<String> skrivFilPasienter() {
        Lenkeliste<String> sPasienter = new Lenkeliste<>();
        sPasienter.leggTil("# Pasienter  (navn, fnr)");
        if(pasienter.stoerrelse == 0) {
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
        sLegemidler.leggTil("# Legemidler   (navn, type, pris, virkestoff [, styrke])");
        if(legemidler.stoerrelse == 0) {
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
        sLeger.leggTil("# Leger  (navn, kontrollid / 0 hvis ingen id)");
        if(leger.stoerrelse == 0) {
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
        sResepter.leggTil("# Resepter (type, legemiddelNummer, legeNavn, persID, [reit])");
        if(resepter.stoerrelse == 0) {
            System.out.println("Ingen registrerte resepter.");
        }
        else {
            for (Resept resept : resepter) {
                sResepter.leggTil(resept.type() + ", " + resept.hentLegemiddel().hentId() + ", " + resept.hentLege() + ", " + resept.hentPasientId() + ", " + resept.hentReit());
            }
        }
        return sResepter;
    }

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

    private static void skrivUtAlle() {
        skrivUtPasienter();
        skrivUtLegemidler();
        skrivUtLeger();
        skrivUtResepter();
    }

    private static void skrivUtPasienter() {
        if(pasienter.stoerrelse == 0) {
            System.out.println("Ingen registrerte pasienter.");
        }
        else {
            System.out.println("Pasienter:  (Id, Navn, Fnr)");
            pasienter.skrivUt();
        }
    }

    private static void skrivUtLeger() {
        if(leger.stoerrelse == 0) {
            System.out.println("Ingen registrerte leger.");
        }
        else {
            System.out.println("Leger:  (Navn, Type, KontrollId)");
            leger.skrivUt();
        }
    }

    private static void skrivUtLegemidler() {
        if(legemidler.stoerrelse == 0) {
            System.out.println("Ingen registrerte legemidler");
        }
        else {
            System.out.println("Legemidler:     (Id, Navn, Type, Pris, Virkestoff, Styrke)");
            legemidler.skrivUt();
        }
    }

    private static void skrivUtResepter() {
        if(resepter.stoerrelse == 0) {
            System.out.println("Ingen registrerte resepter");
        }
        else {
            System.out.println("Resepter:   (Id, Legemiddel, Utskrivende lege, Farge, PasientId, Reit, Pris)");
            resepter.skrivUt();
        }
    }

    private static void menyStatistikk(Scanner system) {
        Lenkeliste<String> kommandoer = new Lenkeliste<>();
        kommandoer.leggTil("1:  Alle\n");
        kommandoer.leggTil("2:  Resepter paa vanedannede legemidler\n");
        kommandoer.leggTil("3:  Resepter paa vanedannede legemidler skrevet til en militaer\n");
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
                    statistikkVaneMilitaer();
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

    private static void statistikkAlle() {
        statistikkVanedannede();
        statistikkVaneMilitaer();
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

    private static void statistikkVaneMilitaer(){
        int count = 0;
        for (Resept resept : resepter) {
            if (resept.legemiddel.hentType().equals("b")) {
                if (resept.type().equals("militaer")) {
                    count++;
                }
            }
        }
        System.out.println("Antall vanedannende resepter utskrevne til militære:  " + count);
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
            resept = lege.skrivResept(legemiddel, pasient, reit, type);
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
