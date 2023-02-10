


public class ArrayDeque<T> {

    private T[] data;
    private int size;
    private int capacity;
    public ArrayDeque() {
        data = (T[]) new Object[10];
        size = 0;
        capacity = 10;
    }

    private void resize(int s) {
        capacity = s;
        T[] tmp = (T[]) new Object[capacity];
        System.arraycopy(data, 0, tmp, 0, size);
        data = tmp;
    }

    private void checkRemoveSize() {
        if (size * 4 < capacity && size >= 16) {
            resize((int) (capacity / 2));
        }
    }

    private void checkAddSize() {
        if (size + 1 >= capacity) {
            resize((int) (size * 2));
        }
    }

    public void addFirst(T item) {
        checkAddSize();
        System.arraycopy(data, 0, data, 1, size);
        data[0] = item;
        size++;
    }

    public void addLast(T item) {
        checkAddSize();
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
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T tmp = data[0];
        System.arraycopy(data, 1, data, 0, size - 1);
        size--;
        checkRemoveSize();
        return tmp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T tmp = data[size-1];
        size--;
        checkRemoveSize();
        return tmp;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            return data[index];
        }
    }

}
