package oppgave3;

public class Bil3 {
    String bilNr;
    Bil3(String nr) {
        bilNr = nr;
    }
    public void print() {
        System.out.println(bilNr);
    }
    public String hentNummer() {
        return bilNr;
    }
}
