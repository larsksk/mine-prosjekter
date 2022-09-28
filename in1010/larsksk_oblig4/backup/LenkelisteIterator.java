import java.util.Iterator;

public class LenkelisteIterator<T> implements Iterator<T> {
    private Lenkeliste.Node<T> curr;

    LenkelisteIterator(Lenkeliste.Node<T> start) {
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
