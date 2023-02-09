


public class ArrayDeque<T> {

    private T[] data;
    private int size;
    private int capacity;
    public ArrayDeque() {
        data = (T[])new Object[10];
        size = 0;
        capacity = 10;
    }

    private void resize(int s) {
        capacity = s;
        T[] tmp = (T[])new Object[capacity];
        System.arraycopy(data, 0, tmp, 0, size);
        data = tmp;
    }

    private void checkSize() {
        if (size * 1.25 < capacity || size + 1 >= capacity)
            resize((int)(size * 1.25));
    }

    public void addFirst(T item) {
        checkSize();
        System.arraycopy(data, 0, data, 1, size);
        data[0] = item;
        size++;
    }

    public void addLast(T item) {
        checkSize();
        data[size] = item;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++)
            System.out.print(data[i] + " ");
    }

    public T removeFirst() {
        if (size == 0)
            return null;
        System.arraycopy(data, 1, data, 0, size-1);
        size--;
        checkSize();
        if (size == 0)
            return null;
        else
            return data[0];
    }

    public T removeLast() {
        if (size == 0)
            return null;
        size--;
        checkSize();
        if (size == 0)
            return null;
        else
            return data[size-1];
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            return null;
        else
            return data[index];
    }


}