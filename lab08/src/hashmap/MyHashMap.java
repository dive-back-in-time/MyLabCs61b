package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private int initialCapacity ;
    private  double loadFactor ;

    /** Constructors */
    public MyHashMap() {
        initialCapacity = 16;
        loadFactor = 0.75f;
        buckets = new Collection[initialCapacity];
        //实例化每个bucket
        // TODO：不能用for each
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();  // 正确初始化每个桶
        }
    }

    public MyHashMap(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        loadFactor = 0.75f;
        buckets = new Collection[initialCapacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();  // 正确初始化每个桶
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        buckets = new Collection[initialCapacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();  // 正确初始化每个桶
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!



    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>(){
            private int wiz = 0;
            private int index = 0;

            @Override
            public boolean hasNext() {
                wiz++;
                return wiz <= size;
            }

            @Override
            public K next() {
                while(buckets[index] == null) {
                    index++;
                }



                return null;
            }
        };
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }

        if (containsKey(key)) {
            // TODO：感觉有问题
            int index = Math.floorMod(key.hashCode(),initialCapacity);
            for (Node node : buckets[index]) {
                if (node.key .equals(key)) {
                    node.value = value;
                }
            }
        } else {
            Node node = new Node(key,value);

            //判断放到哪个buckets，并加入对应的list
            int index = Math.floorMod(key.hashCode(), initialCapacity);
            buckets[index].add(node);

            size++;
        }

        //超过对应loadFactor则resize()
        if ((double) size / initialCapacity >= loadFactor) {
            resize();
        }

    }

    //这里resize()后需要让所有对应元素归类
    public void resize(){
        //重新创建一个容量为2倍的buckets
        initialCapacity = 2 * initialCapacity;
        Collection<Node>[] bucketsNew = new Collection[initialCapacity];
        for (int i = 0; i < bucketsNew.length; i++) {
            bucketsNew[i] = createBucket();  // 正确初始化每个桶
        }

        for (Collection<Node> bucket : buckets) {
            for (Node buck : bucket) {
                int index = Math.floorMod(buck.key.hashCode(),initialCapacity);
                bucketsNew[index].add(buck);
            }
        }
        buckets = bucketsNew;
    }

    @Override
    public V get(K key) {
        if (containsKey(key)) {
            int index = Math.floorMod(key.hashCode(),initialCapacity);
            for (Node node : buckets[index]) {
                if (node.key .equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }

        int index = Math.floorMod(key.hashCode(),initialCapacity);

        if (buckets[index] == null) {
            return false;
        }
        for (Node node : buckets[index]) {
            if (node.key .equals(key)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = null;  // 正确初始化每个桶
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public V remove(K key) {
        return null;
    }

}
