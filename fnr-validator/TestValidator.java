// Lars Krsitian Skaarseth

public class TestValidator {
    private static int tests = 0;
    private static int passed = 0;

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        testValid();
        testValidDnr();
        testValidHnr();
        testValidFHnr();
        testLength();
        testMonth();
        testDay();
        testIndiv1();
        testIndiv2();
        testCon1();
        testCon2();
        testLeap();
        testNotLeap();
        futureDate();

        System.out.print("\n" + passed + "/" + tests + " TESTS PASSED");
    }

    /**
     * Her tenkte jeg aa lage en algoritme som genererte et valid foedselsnummer, men etter en stund skjoente jeg at
     * det ble nesten like saa mye, om ikke mer arbeid, og krever like mye testing som aa validere et foedselsnummer.
     * Bestemte meg til slutt for aa bruke en gitt dato (01.01.2000) og individsiffre (600), og deretter regne ut
     * kontrollriffrene for aa generere et valid foedselsnummer.
     **/

    private static String validFnrString(int[] validArray) {
        String fnr = "";
        for (int i : validArray) {
            fnr += String.valueOf(i);
        }
        return fnr;
    }

    private static int[] kontrollsiffer(int[] n) {
        int k1 = 11 - ((3*n[0] + 7*n[1] + 6*n[2] + n[3] + 8*n[4] + 9*n[5] + 4*n[6] + 5*n[7] + 2*n[8]) % 11);
        if (k1 == 11) {
            k1 = 0;
        }
        n[9] = k1;
        int k2 = 11 - ((5*n[0] + 4*n[1] + 3*n[2] + 2*n[3] + 7*n[4] + 6*n[5] + 5*n[6] + 4*n[7] + 3*n[8] + 2*k1) % 11);
        if (k2 == 11) {
            k2 = 0;
        }
        n[10] = k2;

        if(k1 == 10 || k2 == 10) {
            n[1] += 1;
            kontrollsiffer(n);
        }
        return n;
    }

    private static void testValid() {
        System.out.print("Tester for gyldig foedselsnummer...           ");
        int[] n = new int[]{0,1,0,1,0,0,6,0,0,0,0};
        n = kontrollsiffer(n);
        tests += 1;
        // Forventer true
        if(FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void testValidDnr() {
        System.out.print("Tester for gyldig D-nummer...                 ");
        int[] n = new int[]{4,1,0,1,0,0,6,0,0,0,0};
        kontrollsiffer(n);
        tests += 1;
        // Forventer true
        if(FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void testValidHnr() {
        System.out.print("Tester for gyldig H-nummer...                 ");
        int[] n = new int[]{0,1,4,1,0,0,6,0,0,0,0};
        kontrollsiffer(n);
        tests += 1;
        // Forventer true
        if(FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void testValidFHnr() {
        System.out.print("Tester for gyldig FH-nummer...                ");
        int[] n = new int[]{8,1,0,1,0,0,6,0,0,0,0};
        kontrollsiffer(n);
        tests += 1;
        // Forventer true
        if(FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void testLength() {
        System.out.print("Tester for ugyldig lengde...                  ");
        int[] n = new int[]{0,1,0,1,0,0,6,0,0,0,0,0};
        // Forventer false
        tests += 1;
        if(!FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void testMonth() {
        System.out.print("Tester for ugyldig maaned...                  ");
        int[] n = new int[]{0,1,1,3,0,0,6,0,0,0,0};
        kontrollsiffer(n);
        tests += 1;
        // Forventer false
        if(!FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void testDay() {
        System.out.print("Tester for ugyldig dag...                     ");
        int[] n = new int[]{3,9,0,1,0,0,6,0,0,0,0};
        kontrollsiffer(n);
        tests += 1;
        // Forventer false
        if(!FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void testIndiv1() {
        System.out.print("Tester for ugyldig individnummer 1...         ");
        int[] n = new int[]{0,1,0,1,4,1,6,0,0,0,0};
        kontrollsiffer(n);
        tests += 1;
        // Forventer false
        if(!FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void testIndiv2() {
        System.out.print("Tester for ugyldig individnummer 2...         ");
        int[] n = new int[]{0,1,0,1,3,4,8,0,0,0,0};
        kontrollsiffer(n);
        tests += 1;
        // Forventer false
        if(!FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void testCon1() {
        System.out.print("Tester for ugyldig kontrollnummer 1...        ");
        int[] n = new int[]{0,1,0,1,0,0,6,0,0,0,0};
        kontrollsiffer(n);
        n[9] += 1;
        tests += 1;
        // Forventer false
        if(!FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void testCon2() {
        System.out.print("Tester for ugyldig kontrollnummer 2...        ");
        int[] n = new int[]{0,1,0,1,0,0,6,0,0,0,0};
        kontrollsiffer(n);
        n[10] += 1;
        tests += 1;
        // Forventer false
        if(!FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void testLeap() {
        System.out.print("Tester for 29. februar i et skuddaar...       ");
        int[] n = new int[]{2,9,0,2,0,0,6,0,0,0,0};
        kontrollsiffer(n);
        tests += 1;
        // Forventer true
        if(FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void testNotLeap() {
        System.out.print("Tester for 29. februar i et ikke-skuddaar...  ");
        int[] n = new int[]{2,9,0,2,0,0,4,0,0,0,0};
        kontrollsiffer(n);
        tests += 1;
        // Forventer false
        if(!FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }

    private static void futureDate() {
        System.out.print("Tester for fremtidig dato...                  ");
        int[] n = new int[]{0,1,0,1,3,7,6,0,0,0,0};
        kontrollsiffer(n);
        tests += 1;
        // Forventer false
        if(!FnrValidator.validate(validFnrString(n))) {
            System.out.print("PASSED\n");
            passed += 1;
        } else {
            System.out.print("FAILED\n");
        }
    }
}

