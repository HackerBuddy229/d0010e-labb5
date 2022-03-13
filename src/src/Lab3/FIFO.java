package Lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FIFO {
    private List<Object> store;
    private int maxQueued = 0;
    private int elementsQueued = 0;

    public FIFO() {
        this.store = new ArrayList<Object>();
    }



    public void add(Object o) {
        store.add(o);
        elementsQueued++;
        maxQueued = Math.max(maxQueued, store.size());
    }


    public void removeFirst() throws NoSuchElementException {
        if (store == null || store.isEmpty())
            throw new NoSuchElementException();

        store.remove(0);
    }


    public Object first() throws NoSuchElementException {
        if (store == null || store.isEmpty())
            throw new NoSuchElementException();

        return store.get(0);
    }

    /**
     * Largest value since creation
     * @return
     */

    public int maxSize() {
        return maxQueued;
    }

    public int allElementsQueued() {return elementsQueued; }


    public boolean isEmpty() {
        return store == null || store.isEmpty();
    }


    public int size() {
        return store.size();
    }

    /**
     *
     * @param f
     * @return
     */
    @Override
    public boolean equals(Object f) {
        if (f == null)
            return false;

        if (f.getClass() != this.getClass())
            throw new ClassCastException();

        if (((FIFO) f).size() != this.size())
            return false;

        for (int index = 0; index < this.size(); index++) {
            Object val1 = this.store.get(index);
            Object val2 = ((FIFO)f).store.get(index);

            if (val1 == null || val2 == null )
                if (val1 == val2)
                    continue;
                else
                    return false;


            if (val1.equals(val2))
                continue;

            return false;
        }

        return true;
    }

    /**
     * his method returns a string beginning with "Queue: " followed by the following, for each element
     * elem in the queue:
     * "(" + String.valueOf(elem) + ") "
     * @return
     */
    @Override
    public String toString() {
        String out = "Queue: ";
        for (Object o: store)
            out += "(" + o + ") ";

        return out;
    }
}
