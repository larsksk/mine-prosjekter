package oppgave3;

public class BilBruk3 {

    public static void main(String[] args) {
        Bil3 bil = new Bil3("929578");
        Person pers = new Person(bil);
        pers.print();
    }
}
