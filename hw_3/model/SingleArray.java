package hw_3.model;

public class SingleArray<T> implements IArray<T> {

    private Object[] array;

    public SingleArray() {
        array = new Object[0];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public void add(T item) {
        resize();
        array[size() - 1] = item;
    }

    @Override
    public void add(T item, int index) {

        if (index >= size()) {
            resize(index + 1);
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
        Object[] newArray = new Object[size() + 1];
        System.arraycopy(array, 0, newArray, 0, size());
        array = newArray;
    }

    private void resize(int i) {
        Object[] newArray = new Object[size() + i];
        System.arraycopy(array, 0, newArray, 0, size());
        array = newArray;
    }
}
