public class SortertLenkeliste<T extends Comparable<T> > extends Lenkeliste<T> {

    // Setter inn element (x) i sortert rekkefoelge
    public void leggTil(T x) {
        Node<T> node = new Node<>();
        node.data = x;

        // For tom liste
        if(stoerrelse == 0) {
            head = node;
        }
        // Om elementet (x) er mindre enn første element:
        else if(x.compareTo(head.data) < 0) {
            node.next = head;
            head = node;
        }
        // For resterende situasjoner:
        else {
            Node<T> curr = head;
            // Itererer gjennom elemter (Noder)
            for (int i = 0; i < stoerrelse - 1; i++) {
                if(x.compareTo(curr.next.data) > 0) {
                    curr = curr.next;
                }
            }
            node.next = curr.next;
            curr.next = node;
        }
        stoerrelse += 1;
    }

    // Fjerner siste/stoerste element. Stoerste element skal vaere det siste elementet i denne sorterte listen
    public T fjern() {
        return fjern(stoerrelse-1);
    }

    // Throws UnsupportedOperationException
    public void sett(int pos, T x) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    // Throws UnsupportedOperationException
    public void leggTil(int pos, T x) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
