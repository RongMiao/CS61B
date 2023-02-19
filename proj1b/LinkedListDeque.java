
public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        public T value;
        public Node prev;
        public Node next;
        public Node(T v, Node p, Node n) {
            value = v;
            prev = p;
            next = n;
        }
        public Node() {
            value = null;
            prev = null;
            next = null;
        }
    }
    private Node sentinel;
    private int size;
    private Node recursive;
    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        recursive = null;
    }
    @Override
    public void addFirst(T item) {
        Node tmp = sentinel.next;
        sentinel.next = new Node(item, sentinel, tmp);
        tmp.prev = sentinel.next;
        size++;
    }
    @Override
    public void addLast(T item) {
        Node tmp = sentinel.prev;
        sentinel.prev = new Node(item, tmp, sentinel);
        tmp.next = sentinel.prev;
        size++;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        for (Node node = sentinel.next; node != sentinel; node = node.next) {
            System.out.print(node.value + " ");
        }
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node tmp = sentinel.next;
        sentinel.next = tmp.next;
        sentinel.next.prev = sentinel;
        size--;
        return tmp.value;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node tmp = sentinel.prev;
        sentinel.prev = tmp.prev;
        sentinel.prev.next = sentinel;
        size--;
        return tmp.value;
    }
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            Node node = sentinel.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.value;
        }
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (recursive == null) {
            recursive = sentinel;
        }
        recursive = recursive.next;
        if (index == 0) {
            T v = recursive.value;
            recursive = null;
            return v;
        } else {
            index--;
            return getRecursive(index);
        }
    }

}
