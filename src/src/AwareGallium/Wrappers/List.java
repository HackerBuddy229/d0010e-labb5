package AwareGallium.Wrappers;

import java.util.Iterator;

public class List<T> implements Iterable<T> { //TODO: check it works
    private Object[] store;
    private int currentCapacity = 0;


    public List() {
        this(0);
    }

    public List(int initialCapacity) {
        store = new Object[initialCapacity];
        currentCapacity = initialCapacity;
    }

    public int size() {
        int notNUll = 0;
        for (Object ob: store)
            notNUll += ob != null ? 1 : 0;
        return notNUll;
    }

    public T get(int index){
        return (T)store[index];
    }
    public void remove(int index) {
        if (store.length > 1) {
            for (int i = 1; i < store.length; i++)
                store[i-1] = store[i];
            store[store.length-1] = null;
        } else
            store[0] = null;
    }

    public void add(Object ob) {
        ensureCapacity(currentCapacity+1);
        store[currentCapacity-1] = ob;
    }




    public T[] toArray(T[] base) {
        copy(base, store);
        return base;
    }


    private void grow(int newSize) {
        Object[] base = new Object[newSize];
        base = copy(base, store);

        store = base;
        currentCapacity = newSize;
    }

    private void ensureCapacity(int requiredSize) {
        if (!checkCapacity(requiredSize))
            grow(requiredSize);
    }

    private Object[] copy(Object[] base, Object[] old) {
        for (int index = 0; index < currentCapacity; index++)
            base[index] = store[index];

        return base;
    }

    private boolean checkCapacity(int size) {
        return size <= currentCapacity;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<T>(this);
    }
}

class ListIterator<T> implements Iterator<T>{

    private final List<T> list;
    private int index = 0;

    public ListIterator(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() {
        if (hasNext()) {
            T obj =  list.get(index);
            index++;
            return obj;
        }
        return null;
    }
}
