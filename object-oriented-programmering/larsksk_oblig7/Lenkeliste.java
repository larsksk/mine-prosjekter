import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T>{
    Node<T> head;
    int stoerrelse;

    static class Node<T>
    {
        T data;
        Node<T> next;
    }

    public Iterator<T> iterator() {
        return new LenkelisteIterator<>(head);
    }

    public class LenkelisteIterator<T> implements Iterator<T> {
        private Lenkeliste.Node<T> curr;

        LenkelisteIterator(Node<T> start) {
            curr = start;
        }

        public boolean hasNext() {
            return curr != null;
        }

        public T next() {
            T data = curr.data;
            curr = curr.next;
            return data;
        }

        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }

    public int stoerrelse() {
        return stoerrelse;
    }

    // Legger til elemement (x) til index (pos)
    public void leggTil(int pos, T x) throws UgyldigListeIndeks {
        if(pos > stoerrelse || pos < 0) {
            throw new UgyldigListeIndeks(pos);
        }
        Node<T> node = new Node<>();
        node.data = x;

        // Legger til forran i listen
        if(pos == 0) {
            node.next = head;
            head = node;
        }
        else {
            Node<T> curr = head;
            // Itererer gjennom elemter (Noder)
            for (int i = 0; i < pos - 1; i++) {
                curr = curr.next;
            }
            node.next = curr.next;
            curr.next = node;
        }
        stoerrelse += 1;
    }

    // Legger til element (x) i slutten av listen
    public void leggTil(T x) {
        leggTil(stoerrelse, x);

        /*
        Node<T> node = new Node<>();
        node.data = x;

        if(head == null) {
            head = node;
        }
        else {
            Node<T> curr = head;
            // Itererer gjennom elemter (Noder)
            while(curr.next != null) {
                curr = curr.next;
            }
            curr.next = node;
        }
        stoerrelse += 1;
        */
    }

    // Setter inn elementet (x) til posisjon (pos) og overskriver elementet som var der tidligere
    public void sett(int pos, T x) throws UgyldigListeIndeks {
        if(stoerrelse == 0 || pos > stoerrelse-1 || pos < 0) {
            throw new UgyldigListeIndeks(pos);
        }

        // Setter foerste element
        if(pos == 0) {
            head.data = x;
        }
        else {
            Node<T> curr = head;
            // Itererer gjennom elemter (Noder)
            for (int i = 0; i < pos - 1; i++) {
                curr = curr.next;
            }
            curr.next.data = x;
        }
    }

    // Henter ut et element (uten å fjerne det fra lista) på indeks (pos)
    public T hent(int pos) throws UgyldigListeIndeks {
        if(stoerrelse == 0 || pos > stoerrelse-1 || pos < 0) {
            throw new UgyldigListeIndeks(pos);
        }
        // Henter foerste element
        if(pos == 0) {
            return head.data;
        }
        else {
            Node<T> curr = head;
            // Itererer gjennom elemter (Noder)
            for (int i = 0; i < pos - 1; i++) {
                curr = curr.next;
            }
            return curr.next.data;
        }
    }

    // Fjerner elementet på indeks (pos)
    public T fjern(int pos) throws UgyldigListeIndeks {
        if(stoerrelse == 0 || pos > stoerrelse-1 || pos < 0) {
            throw new UgyldigListeIndeks(pos);
        }
        // Fjerner foerste element
        if(pos == 0) {
            T retur = head.data;
            head = head.next;
            stoerrelse -= 1;
            return retur;
        }
        else {
            Node<T> curr = head;
            Node<T> prev = head;
            // Itererer gjennom elemter (Noder)
            for (int i = 0; i < pos; i++) {
                prev = curr;
                curr = curr.next;
            }
            prev.next = curr.next;
            stoerrelse -= 1;
            return curr.data;
        }
    }

    // Fjerner og returnerer elementet på starten av listen
    public T fjern() throws UgyldigListeIndeks {
        return fjern(0);

        /*
        if(stoerrelse == 0) {
            throw new UgyldigListeIndeks(0);
        }

        T retur;
        retur = head.data;
        head = head.next;
        stoerrelse -= 1;
        return retur;
        */
    }

    // Skriver ut listen
    public void skrivUt() {
        Node curr = head;

        // Itererer gjennom elemter (Noder) og skriver de ut
        while(curr != null) {
            System.out.print(curr.data + "");
            curr = curr.next;
        }
        System.out.println(" ");
    }
}
