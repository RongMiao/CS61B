




public class LinkedListDeque<T> {

    public class Node {
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
    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node tmp = sentinel.next;
        sentinel.next = new Node(item, sentinel, tmp);
        tmp.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        Node tmp = sentinel.prev;
        sentinel.prev = new Node(item, tmp, sentinel);
        tmp.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (Node node = sentinel.next; node != sentinel; node = node.next)
            System.out.print(node.value + " ");
    }

    public T removeFirst() {
        if (size == 0)
            return null;
        Node tmp = sentinel.next;
        sentinel.next = tmp.next;
        sentinel.next.prev = sentinel;
        size--;
        if (size == 0)
            return null;
        else
            return sentinel.next.value;
    }

    public T removeLast() {
        if (size == 0)
            return null;
        Node tmp = sentinel.prev;
        sentinel.prev = tmp.prev;
        sentinel.prev.prev = sentinel;
        size--;
        if (size == 0)
            return null;
        else
            return sentinel.prev.value;
    }

    public T get(int index) {
        if (index <= 0 || index >= size)
            return null;
        else {
            Node node = sentinel;
            for (int i = 0; i < index; i++) {
                node = sentinel.next;
            }
            return node.value;
        }
    }

}