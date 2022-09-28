public class Hovedprogram {

    public static void main(String[] args){
        Regneklynge abel = new Regneklynge("regneklynge.txt");

        int min32 = abel.noderMedNokMinne(32);
        int min64 = abel.noderMedNokMinne(64);
        int min128 = abel.noderMedNokMinne(128);

        int antRack = abel.antRack();
        int antPros = abel.antProsessor();

        System.out.println("Noder med minst 32 GB: " + min32);
        System.out.println("Noder med minst 64 GB: " + min64);
        System.out.println("Noder med minst 128 GB: " + min128);
        System.out.println(" ");
        System.out.println("Antall prosessorer: " + antPros);
        System.out.println("Antall rack: " + antRack);
    }
}