


public class ArrayDeque<T> {

    private T[] data;
    private int size;
    private int capacity;
    public ArrayDeque() {
        data = (T[]) new Object[8];
        size = 0;
        capacity = 8;
    }

    public void addFirst(T item) {
        if (size + 1 >= capacity) {
            if (size <= 16) {
                capacity = (int) (capacity * 1.25);
            } else {
                capacity = (int) (capacity * 1.5);
            }
            T[] tmp = (T[]) new Object[capacity];
            System.arraycopy(data, 0, tmp, 1, size);
            data = tmp;
        } else {
            System.arraycopy(data, 0, data, 1, size);
        }
        data[0] = item;
        size++;
    }

    public void addLast(T item) {
        if (size + 1 >= capacity) {
            if (size <= 16) {
                capacity = (int) (capacity * 1.25);
            } else {
                capacity = (int) (capacity * 1.5);
            }
            T[] tmp = (T[]) new Object[capacity];
            System.arraycopy(data, 0, tmp, 0, size);
            data = tmp;
        }
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
        T ret = data[0];
        if (size * 2 < capacity && size >= 16) {
            capacity = (int) (capacity / 2);
            T[] tmp = (T[]) new Object[capacity];
            System.arraycopy(data, 1, tmp, 0, size - 1);
            data = tmp;
        } else {
            System.arraycopy(data, 1, data, 0, size - 1);
        }
        size--;
        return ret;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T ret = data[size - 1];
        size--;
        if (size * 2 < capacity && size >= 16) {
            capacity = (int) (capacity / 2);
            T[] tmp = (T[]) new Object[capacity];
            System.arraycopy(data, 0, tmp, 0, size);
            data = tmp;
        }

        return ret;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            return data[index];
        }
    }

}
