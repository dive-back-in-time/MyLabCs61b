import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B {
    /**
     * A nested class Node
     */
    private class Node{
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



    /**
     * Constructor,not empty sentinel
     */
    public LinkedListDeque61B(){
        sentinel = new Node(sentinel,null, sentinel);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }


    @Override
    public void addFirst(Object x) {
        sentinel.next = new Node(sentinel,(T)x,sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    @Override
    public void addLast(Object x) {
        sentinel.prev = new Node(sentinel.prev,(T)x,sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    @Override
    public List toList() {
        List<T> li = new ArrayList<>();
        Node p =sentinel.next;
        while(p != sentinel){
            li.add(p.item);
            p = p.next;


        }
        return li;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object removeFirst() {
        if (size > 0){
            Object o = get(1);
            Node n = sentinel.next.next;
            sentinel.next = n;
            n.prev = sentinel;
            size --;
            return o;
        } else {
            return null;
        }

    }

    @Override
    public Object removeLast() {
        if (size > 0){
            Object o = get(size);
            Node n = sentinel.prev;
            sentinel.prev = n.prev;
            n.prev.next = sentinel;
            size --;
            return o;
        }

        return null;
    }

    /** if index is larger than size, return null*/
    @Override
    public Object get(int index) {
        if (index > size){
            return null;
        } else {
            Node p = sentinel;
            while(index != 0){
                p = p.next;
                index --;
            }
            return p.item;
        }
    }

    @Override
    public Object getRecursive(int index) {
        if (index == 0){
            return null;
        }
        return getRecursive(index, sentinel);
    }

    public Object getRecursive(int index, Node n){
        if (index > size || index < 0){
            return null;
        } else {
            if (index == 0){
                return n.item;
            }

            return getRecursive(index - 1, n.next);
        }
    }

    public static void main(String[] args) {
        LinkedListDeque61B<String> b = new LinkedListDeque61B<>();
        b.addFirst("back");
        b.addFirst("right");
        b.addFirst("right");
        System.out.println(b.toList());
    }
}
