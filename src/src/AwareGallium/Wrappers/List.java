package AwareGallium.Wrappers;

public class List<R> { //TODO: check it works
    private Object[] store;
    private int currentCapacity = 0;


    public List() {
        this(0);
    }

    public List(int initialCapacity) {
        store = new Object[initialCapacity];
        currentCapacity = initialCapacity;
    }

    public int size() { return currentCapacity; }

    public R get(int index){
        return (R)store[index];
    }

    public void add(Object ob) {
        ensureCapacity(currentCapacity+1);
        store[currentCapacity-1] = ob;
    }




    public R[] toArray(R[] base) {
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

}
