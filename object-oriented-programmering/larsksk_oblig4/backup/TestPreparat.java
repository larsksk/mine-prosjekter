public class TestPreparat {
    // Class for testing all the aspects of the Legemiddel subclasses, Preparat- A, B and C.
    // Tests if the results are as expected and prints out the results in an easy to read manner.
    private static int success = 0;
    private static int fail = 0;
    private static int expectedId = Legemiddel.count;
    private static PreparatA prepA = new PreparatA("preparat", 200.5, 2.1, 3);
    private static PreparatB prepB = new PreparatB("preparat", 200.5, 2.1, 3);
    private static PreparatC prepC = new PreparatC("preparat", 200.5, 2.1);

    public static void test() {
        System.out.println("\nTESTER PREPARATER");

        System.out.println("PreparatA test:");
        testPrep(prepA);
        testHentNarkotiskStyrke(prepA);

        System.out.println("\nPreparatB test:");
        testPrep(prepB);
        testHentVanedannendeStyrke(prepB);

        System.out.println("\nPreparatC test:");
        testPrep(prepC);

        int tester = success + fail;
        System.out.println("\n" + tester + " tester ferig");
        System.out.println(success + " passerte, " + fail + " feil");
    }

    // All the common tests are called here, while the individual tests are called above in the test() method.
    private static void testPrep(Legemiddel legemiddel) {
        testHentId(legemiddel);
        testHentNavn(legemiddel);
        testHentPris(legemiddel);
        testSettNyPris(legemiddel);
        testHentVirkestoff(legemiddel);
    }

    // The tests compare the expected results to the actual results. Prints "success" if the two are equal,
    // and "failed" if they are not. This way we can easily see where the problem lies, if there are any.
    private static void testHentId(Legemiddel legemiddel) {
        System.out.print("Id...         ");
        if (legemiddel.hentId() == expectedId) {
            System.out.println("succeeded");
            success++;
        }
        else {
            System.out.println("failed");
            fail++;
        }
        expectedId++;
    }

    private static void testHentNavn(Legemiddel legemiddel) {
        System.out.print("Navn...       ");
        if (legemiddel.hentNavn().equals("preparat")) {
            System.out.println("succeeded");
            success++;
        }
        else {
            System.out.println("failed");
            fail++;
        }
    }

    private static void testHentPris(Legemiddel legemiddel) {
        System.out.print("Pris...       ");
        if (legemiddel.hentPris() == 200.5) {
            System.out.println("succeeded");
            success++;
        }
        else {
            System.out.println("failed");
            fail++;
        }
    }

    private static void testSettNyPris(Legemiddel legemiddel) {
        legemiddel.settNyPris(230.2);
        System.out.print("Ny pris...    ");
        if (legemiddel.hentPris() == 230.2) {
            System.out.println("succeeded");
            success++;
        }
        else {
            System.out.println("failed");
            fail++;
        }
    }

    private static void testHentVirkestoff(Legemiddel legemiddel) {
        System.out.print("Virkestoff... ");
        if (legemiddel.hentVirkestoff() == 2.1) {
            System.out.println("succeeded");
            success++;
        }
        else {
            System.out.println("failed");
            fail++;
        }
    }

    private static void testHentNarkotiskStyrke(PreparatA legemiddel) {
        System.out.print("Styrke...     ");
        if (legemiddel.hentNarkotiskStyrke() == 3) {
            System.out.println("succeeded");
            success++;
        }
        else {
            System.out.println("failed");
            fail++;
        }
    }

    private static void testHentVanedannendeStyrke(PreparatB legemiddel) {
        System.out.print("Styrke...     ");
        if (legemiddel.hentVanedannendeStyrke() == 3) {
            System.out.println("succeeded");
            success++;
        }
        else {
            System.out.println("failed");
            fail++;
        }
    }
}