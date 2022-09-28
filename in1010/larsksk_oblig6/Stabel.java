public class Stabel<T> extends Lenkeliste<T> {

    // Legger til element i slutten av stabelen
    public T leggPaa(T x) {
        leggTil(x);
        return x;
    }

    // Fjerner element i slutten av stabelen
    public T taAv() {
        return fjern(stoerrelse-1);
    }
}
