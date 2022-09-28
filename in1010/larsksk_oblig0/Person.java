package oppgave3;

public class Person {
    private Bil3 minBil;
    public Person(Bil3 bil){
        minBil = bil;
    }

    public void print(){
        System.out.println(minBil.hentNummer());
    }
}