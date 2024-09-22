public class SLList {
    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first;
    private int nums;

    public SLList(int x) {
        first = new IntNode(x, null);
        nums = 1;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
        nums++;
    }

    /** Retrieves the front item from the list. */
    public int getFirst() {
        return first.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        /* Your Code Here! */
        IntNode i =first;
        if (i == null){
            addFirst(x);
        }else{
            while(i.next != null){
                i = i.next;
            }
            i.next = new IntNode(x,null);
            nums++;
        }


    }

    /** Returns the number of items in the list using recursion. */
    public int size(IntNode p) {
        /* Your Code Here! */
        if(p.next ==null){
            return 1;
        }

        p=p.next;

        return 1 + size(p);
    }

    public static void main(String[] args) {
        SLList l = new SLList(15);
        l.addFirst(10);
        l.addFirst(10);
        l.addFirst(10);
        l.addLast(20);
        IntNode first = l.first;
        System.out.println(l.nums);
    }

}