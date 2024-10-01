package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61BB<T> implements Deque61B<T> {
    /**
     * A nested class Node
     */
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    @Override
    public String toString(){
        StringBuilder b = new StringBuilder();
        b.append("[");
        for (T t : this){
            if (t.equals(get(size))){
                b.append(t);
            } else {
                b.append(t);
                b.append(", ");
            }
        }
        b.append("]");
        return b.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int wiz = 1;

            @Override
            public boolean hasNext() {
                if (wiz < size + 1) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                T t = get(wiz);
                wiz++;
                return t;
            }
        };
    }


    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (this == other) {
            return true;
        }

        LinkedListDeque61BB<T> o = (LinkedListDeque61BB<T>) other;
        if (size != o.size()) {
            return false;
        } else if (this.getClass() != o.getClass()) {
            return false;
        } else {
            int i = 0;
            for (T t : o){
                if (!t.equals(get(++i))) {
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * Constructor,not empty sentinel
     */
    public LinkedListDeque61BB() {
        sentinel = new Node(sentinel, null, sentinel);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }


    @Override
    public void addFirst(Object x) {
        sentinel.next = new Node(sentinel, (T) x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    @Override
    public void addLast(Object x) {
        sentinel.prev = new Node(sentinel.prev, (T) x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    @Override
    public List toList() {
        List<T> li = new ArrayList<>();
        Node p = sentinel.next;
        while (p != sentinel) {
            li.add(p.item);
            p = p.next;


        }
        return li;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size > 0) {
            Object o = get(1);
            Node n = sentinel.next.next;
            sentinel.next = n;
            n.prev = sentinel;
            size--;
            return (T) o;
        } else {
            return null;
        }

    }

    @Override
    public T removeLast() {
        if (size > 0) {
            Object o = get(size);
            Node n = sentinel.prev;
            sentinel.prev = n.prev;
            n.prev.next = sentinel;
            size--;
            return (T) o;
        }

        return null;
    }

    /**
     * if index is larger than size, return null
     */
    @Override
    public T get(int index) {
        if (index > size) {
            return null;
        } else {
            Node p = sentinel;
            while (index != 0) {
                p = p.next;
                index--;
            }
            return p.item;
        }
    }

    @Override
    public T getRecursive(int index) {
        if (index == 0) {
            return null;
        }
        return (T) getRecursive(index, sentinel);
    }

    public Object getRecursive(int index, Node n) {
        if (index > size || index < 0) {
            return null;
        } else {
            if (index == 0) {
                return n.item;
            }

            return getRecursive(index - 1, n.next);
        }
    }

    public static void main(String[] args) {
        Deque61B<String> o = new LinkedListDeque61BB<>();
        o.addLast("5");
        o.addLast("6");
        o.addLast("7");


        for (String s : o) {
            System.out.println(s);
        }

        System.out.println(o.get(1));

//        System.out.println(o.toList());

    }
}
