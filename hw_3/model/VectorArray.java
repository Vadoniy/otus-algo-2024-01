package hw_3.model;

public class VectorArray<T> implements IArray<T> {

    private Object[] array;
    private int vector;
    private int size;

    public VectorArray(int vector) {
        this.vector = vector;
        array = new Object[0];
        size = 0;
    }

    public VectorArray() {
        this(10);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        if (size() >= array.length)
            resize();
        array[size-1] = item;
        size++;
    }

    @Override
    public void add(T item, int index) {
        while (index >= size()) {
            resize();
        }
        array[index] = item;
    }

    @Override
    public T remove(int index) {
        var element = array[index];
        array[index] = null;
        return (T)element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T)array[index];
    }

    private void resize() {
        Object[] newArray = new Object[array.length + vector];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
        size = array.length;
    }
}
