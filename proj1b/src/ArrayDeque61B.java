import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B {

    private T[] items;
    private int size;
    private int maxSize;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque61B() {
        maxSize = 8;
        items = (T[]) new Object[maxSize];
        size = 0;
        nextFirst = 1;
        nextLast = 2;

    }

    public void resizeUp() {
        int originmax = maxSize;
        maxSize = 2 * maxSize;
        T[] newItems = (T[]) new Object[maxSize];

        //Put the nextFirst and nextLast to the right place
        nextFirst = nextFirst + originmax;

        //Put the original array in new order
        if (items[0] == null) {
            for (int i = 1; i < originmax; i++) {
                newItems[i + originmax] = items[i];
            }
        } else if (items[originmax - 1] == null) {
            for (int i = 0; i < originmax - 1; i++) {
                newItems[i] = items[i];
            }
        } else {
            for (int i = 0; i < originmax - 1; i++) {
                if (items[i] == null) {
                    break;
                }
                newItems[i] = items[i];
            }

            for (int i = originmax - 1; i > 0; i--) {
                if (items[i] == null) {
                    break;
                }
                newItems[i + originmax] = items[i];
            }
        }
        items = newItems;

    }

    public void reSizeDown() {
        int originMaxSize = maxSize;
        maxSize = maxSize / 2;
        T[] newItems = (T[]) new Object[maxSize];

        //Based on nextFirst, arrange the values upon new-sizedown-array
        int index = Math.floorMod(nextFirst + 1, originMaxSize);
        while (items[index] != null) {
            if (index >= maxSize) {
                newItems[index - maxSize] = items[index];
                index = Math.floorMod(index + 1, originMaxSize);
            } else {
                newItems[index] = items[index];
                index = Math.floorMod(index + 1, originMaxSize);
            }
        }

        nextFirst = Math.floorMod(nextFirst, maxSize);
        nextLast = Math.floorMod(nextLast, maxSize);

        items = newItems;
    }


    @Override
    public void addFirst(Object x) {
        if (size == maxSize - 1) {
            resizeUp();
        }
        items[nextFirst] = (T) x;
        nextFirst = Math.floorMod(nextFirst - 1, maxSize);
        size++;
    }

    @Override
    public void addLast(Object x) {
        if (size == maxSize - 1) {
            resizeUp();
        }

        items[nextLast] = (T) x;


        nextLast = Math.floorMod(nextLast + 1, maxSize);
        size++;

    }

    @Override
    public List toList() {
        List<T> l = new ArrayList<>();
        for (T item : items) {
            l.add(item);
        }
        return l;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
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
        if (size == 0) {
            System.out.println("Array is empty now");
            System.exit(1);
        } else if (size <= maxSize / 4){
            reSizeDown();
        }

        items[Math.floorMod(nextFirst + 1, maxSize)] = null;
        T t = (T) get(Math.floorMod(nextFirst + 1, maxSize));
        nextFirst = Math.floorMod(nextFirst + 1, maxSize);
        size--;
        return t;
    }

    @Override
    public Object removeLast() {
        if (size == 0) {
            System.out.println("Array is empty now");
            System.exit(1);
        } else if (size <= maxSize / 4){
            reSizeDown();
        }
        items[Math.floorMod(nextLast - 1, maxSize)] = null;
        T t = (T) get(Math.floorMod(nextLast - 1, maxSize));
        nextLast = Math.floorMod(nextLast - 1, maxSize);
        size--;
        return t;
    }

    @Override
    public Object get(int index) {
        return items[index];
    }

    @Override
    public Object getRecursive(int index) {
        return null;
    }

    public static void main(String[] args) {
        Deque61B<Integer> l = new ArrayDeque61B<>();
        l.addFirst(5);
        l.addFirst(15);
        l.addFirst(25);
        l.addFirst(35);
        l.removeFirst();
        l.removeFirst();
        l.removeLast();
        l.removeLast();
        l.removeLast();

        System.out.println(l.toList());
    }
}
