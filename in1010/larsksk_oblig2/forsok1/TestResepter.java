public class TestResepter {
    // Class for testing all the aspects of the Resept subclasses, Blaa, Hvit, Prevensjon, and Militaer.
    // Tests if the results are as expected and prints out the results in an easy to read manner.
    private static int expectedId = Resept.count;
    private static PreparatC prepC = new PreparatC("preparat", 200, 2.1);

    private static Lege lege = new Lege("Dr. House");

    private static Resept hvit = new Hvit(prepC, lege, 101, 1);
    private static Resept prevensjon = new Prevensjon(prepC, lege, 101, 1);
    private static Resept militaer = new Militaer(prepC, lege, 101, 1);
    private static Resept blaa = new Blaa(prepC, lege, 101, 1);

    public static void test() {
        System.out.println("\nTESTER RESEPTER");

        System.out.println("Hvit test:");
        testResept(hvit);
        testPrisHvit(hvit);
        testHentReit(hvit);
        testFargeHvit(hvit);
        testBruk(hvit);

        System.out.println("\nPrevensjon test:");
        testResept(prevensjon);
        testPrisPrevensjon(prevensjon);
        testHentReitPrevensjon(prevensjon);
        testFargeHvit(prevensjon);
        testBrukPrevensjon(prevensjon);

        System.out.println("\nMilitaer test:");
        testResept(militaer);
        testPrisMilitaer(militaer);
        testHentReit(militaer);
        testFargeHvit(militaer);
        testBruk(militaer);

        System.out.println("\nBlaa test:");
        testResept(blaa);
        testPrisBlaa(blaa);
        testHentReit(blaa);
        testFargeBlaa(blaa);
        testBruk(blaa);
    }

    // All the common tests are called here, while the individual tests are called above in the test() method.
    private static void testResept(Resept resept) {
        testHentId(resept);
        testHentLegemiddel(resept);
        testHentLege(resept);
        testHentPasientId(resept);
    }

    // The tests compare the expected results to the actual results. Prints "success" if the two are equal,
    // and "failed" if they are not. This way we can easily see where the problem lies, if there are any.
    private static void testHentId(Resept resept) {
        System.out.print("Id...         ");
        if (resept.hentId() == expectedId) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
        expectedId += 1;
    }

    private static void testHentLegemiddel(Resept resept) {
        System.out.print("Legemiddel... ");
        if (resept.hentLegemiddel().equals("preparat")) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testHentLege(Resept resept) {
        System.out.print("Lege...       ");
        if (resept.hentLege().equals("Dr. House")) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testHentPasientId(Resept resept) {
        System.out.print("Pasient ID... ");
        if (resept.hentPasientId() == 101) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testHentReit(Resept resept) {
        System.out.print("Reit...       ");
        if (resept.hentReit() == 1) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testHentReitPrevensjon(Resept resept) {
        System.out.print("Reit...       ");
        if (resept.hentReit() == 3) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testBruk(Resept resept) {
        System.out.print("Bruk...       ");
        resept.bruk();
        if (resept.hentReit() == 0) {
            if(!resept.bruk()) {
                System.out.println("succeeded");
                return;
            }
        }
        System.out.println("failed");
    }

    private static void testBrukPrevensjon(Resept resept) {
        System.out.print("Bruk...       ");
        resept.bruk();
        resept.bruk();
        resept.bruk();
        if (resept.hentReit() == 0) {
            if(!resept.bruk()) {
                System.out.println("succeeded");
                return;
            }
        }
        System.out.println("failed");
    }

    private static void testFargeHvit(Resept resept) {
        System.out.print("Farge...      ");
        if (resept.farge().equals("hvit")) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testFargeBlaa(Resept resept) {
        System.out.print("Farge...      ");
        if (resept.farge().equals("blaa")) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testPrisHvit(Resept resept) {
        System.out.print("Pris...       ");
        if (resept.prisAaBetale() == 200) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testPrisMilitaer(Resept resept) {
        System.out.print("Pris...       ");
        if (resept.prisAaBetale() == 0) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testPrisPrevensjon(Resept resept) {
        System.out.print("Pris...       ");
        if (resept.prisAaBetale() == 92.0) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }

    private static void testPrisBlaa(Resept resept) {
        System.out.print("Pris...       ");
        if (resept.prisAaBetale() == 50) {
            System.out.println("succeeded");
        }
        else {
            System.out.println("failed");
        }
    }
}
