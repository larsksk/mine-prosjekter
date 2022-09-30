public class Hovedprogram {

    public static void main(String[] args) {
        PreparatA prepA = new PreparatA("preparatA", 200.5, 2.1, 3);
        PreparatB prepB = new PreparatB("preparatB", 200.5, 2.1, 3);
        PreparatC prepC = new PreparatC("preparatC", 200.5, 2.1);

        Lege lege = new Lege("Dr. House");
        Spesialist spesialist = new Spesialist("Dr. Wilson", 10301);

        Resept hvit = spesialist.skrivResept(prepA, 101, 12);
        Resept prevensjon = new Prevensjon(prepC, spesialist, 102);
        Resept militaer = new Militaer(prepC, lege, 103, 8);
        Resept blaa = new Blaa(prepB, lege, 104, 40);

        System.out.println("INFO OM PREPARATER:");
        System.out.println(prepA + "\n" + prepB + "\n" + prepC + "\n");
        System.out.println("INFO OM LEGER:");
        System.out.println(lege + "\n" + spesialist + "\n");
        System.out.println("INFO OM RESEPTER:");
        System.out.println(hvit + "\n" + prevensjon + "\n" + militaer + "\n" + blaa);

        TestPreparat.test();
        TestResepter.test();
    }
}