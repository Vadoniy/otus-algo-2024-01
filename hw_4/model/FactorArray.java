package hw_4.model;

public class FactorArray<T> implements IArray<T> {

    private Object[] array;
    private int factor;
    private int indexToAdd;

    public FactorArray(int factor, int initLength) {
        this.factor = factor;
        array = new Object[initLength];
        indexToAdd = 0;
    }

    public FactorArray() {
        this(50, 10);
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void add(T item) {
        if (indexToAdd == size())
            resize();
        array[indexToAdd] = item;
        indexToAdd++;
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
        return (T) element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    private void resize() {
        Object[] newArray = new Object[array.length + (array.length * factor) / 100];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}
