// Lars Kristian Skaarseth

public class FnrValidator {

    /**
     * Programmet skjekker om et foedselsnummer er gyldig eller ikke, og returnerer boolean true om det er gyldig
     * eller false om det er ugyldig. Jeg valgte aa gjoere programmet saa minimalistisk som mulig ved det punket, så
     * programmet gir ikke tilbakemelding paa hva som er ugyldig ved et ugyldig nummer. Dette kan forandres relativt
     * enkelt om det skulle oenskes (kaste en exception, eller gi tilbakemelding ved return false som et par eksempler).
     *
     * Jeg valgte ogsaa aa importere saa faa klasser som mulig, men heller implementere det meste selv.
    **/

    public static boolean validate(String fnr) {
        int length = 11;

        // Skjekker om nummeret har riktig lengde
        if (fnr.length()!=length){
            return false;
        }

        // Splitter opp foedselsnummeret, og omgjoer til et int array
        String[] split = fnr.split("");
        int[] n = new int[length];
        for (int i = 0; i<length; i++) {
            n[i] = Integer.parseInt(split[i]);
        }

        // Skjekker om nummeret inneholder en gyldig dato med mindre det er et FH-nummer,
        // som har 8 eller 9 som foerste siffer.
        if(n[0] < 8) {

            // Her kunne jeg brukt innebygde pakker eller andre sin kode for aa validere datoen, men valgte heller aa
            // proeve aa implementere min egen validering. Den er ikke den mest effektive, men den fungerer

            int day = Integer.parseInt(split[0]+split[1]);
            int month = Integer.parseInt(split[2]+split[3]);
            int year = Integer.parseInt(split[4]+split[5]);

            int indiv = Integer.parseInt(split[6]+split[7]+split[8]);

            // Om foerste siffer er stoerre enn 3, er det et D-nummer
            if (n[0] > 3) {
                day -= 40;
            }

            // Om tredje siffer er stoerre enn 1, er det et H-nummer
            if (n[2] > 1) {
                month -= 40;
            }

            // Skjekker om maaneden eksisterer
            if (month < 1 || month > 12) {
                return false;
            }

            // Skjekker hvor mange dager det er i maaneden
            int dim = 31;
            if (month == 2) {
                // Skjekker om det er et skuddaar hvis maaneden er februar. Bruker individsifrene til aa skille
                // mellom 00 for 1900 eller 2000, siden 2000 er et skuddaar men ikke 1900
                if (year % 4 == 0 && year != 0 || year == 0 && indiv > 500) {
                    dim = 29;
                }
                else {
                    dim = 28;
                }
            }
            else {
                // Ser om det er 31 dager i maaneden
                int[] longM = new int[] {4, 6, 9, 11};
                for (int m: longM) {
                    if (month == m) {
                        dim = 30;
                        break;
                    }
                }
            }

            // Skjekker om dagen eksisterer
            if (day < 1 || day > dim) {
                return false;
            }

            // Skjekker om datoen er foer eller etter dags dato om individsiffrene er 500-999. Da kan ikke foedselsaar
            // vaere fra dags dato til 31. desember (20)39.

            // Finner dags dato, og omgjoer til ints
            String[] dateNow = java.time.LocalDate.now().toString().split("-");
            int[] date = new int[dateNow.length];
            for (int i = 0; i < dateNow.length; i++) {
                date[i] = Integer.parseInt(dateNow[i]);
            }
            date[0] = date[0]-2000;

            if (indiv > 499) {
                if (year > date[0] && year < 40 || year == date[0] && month > date[1] ||
                        year == date[0] && month == date[1] && day > date[2]) {
                    return false;
                }
            }

            // Skjekker inividsiffrene opp mot aarstall
            if (indiv > 499 && indiv < 750) {
                if (!(year < date[0] || year == date[0] && month < date[1] ||
                        year == date[0] && month == date[1] && day < date[2]
                        || year > 53)) {
                    return false;
                }
            }
            else if (indiv > 749 && indiv < 1000) {
                if (!(year < 21 || year > 39)) {
                    return false;
                }
            }
        }

        // Utregning av kontrollsiffre
        int k1 = 11 - ((3*n[0] + 7*n[1] + 6*n[2] + n[3] + 8*n[4] + 9*n[5] + 4*n[6] + 5*n[7] + 2*n[8]) % 11);
        int k2 = 11 - ((5*n[0] + 4*n[1] + 3*n[2] + 2*n[3] + 7*n[4] + 6*n[5] + 5*n[6] + 4*n[7] + 3*n[8] + 2*k1) % 11);

        // Skjekker foerste kontrollsiffer
        if(!(k1 == n[9] || k1 == 11 && n[9] == 0)){
            return false;
        }

        // Skjekker andre kontrollsiffer
        if(!(k2 == n[10] || k2 == 11 && n[10] == 0)){
            return false;
        }

        return true;
    }
}

