import org.checkerframework.checker.units.qual.K;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    /**
     * 创建一个nested class，类似之前学习的DLList
     */
    public class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private BSTNode node;
    private int size;

    public BSTMap() {
        node = new BSTNode(null, null);
        node.left = null;
        node.right = null;
    }

    /**
     * 采用了栈的数据结构，将左边节点压入栈中
     * pop出栈的最右边节点，进行压栈操作
     * @return
     */
    @Override
    public Iterator<K> iterator() {
        return new BSTMapiterator();
    }

    private class BSTMapiterator implements Iterator<K> {
        public Stack<BSTNode> take = new Stack<>();

        public BSTMapiterator() {
            pushleft(node);
        }

        //循环，把b的所有左边节点压入栈中
        public void pushleft(BSTNode b) {
            while (b != null) {
                take.push(b);
                b = b.left;
            }
        }

        @Override
        public boolean hasNext() {
            return take.isEmpty();
        }

        //取出栈中的一个数
        @Override
        public K next() {
            BSTNode n = take.pop();
            pushleft(n.right);
            return n.key;
        }
    }

    /**
     * 采用了递归调用的思想
     * 但要注意BSTMap map = putHelper()…………
     * 左边需要有对应变量
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if (node.key == null) {
            node.key = key;
            node.value = value;
        } else if (containsKey(key)) {
            putHelperContains(node, key, value);
            size--;
        } else {
            BSTNode n = putHelperNoContains(node, key, value);
        }
        size++;
    }

    public BSTNode putHelperNoContains(BSTNode b, K key, V value) {
        if (b == null) {
            return new BSTNode(key, value);
        }

        if (b.key.compareTo(key) < 0) {
            b.right = putHelperNoContains(b.right, key, value);
        } else {
            b.left = putHelperNoContains(b.left, key, value);
        }
        return b;
    }

    public void putHelperContains(BSTNode b, K key, V value) {
        if (b.key.compareTo(key) == 0) {
            b.value = value;
            return;
        }
        if (b.key.compareTo(key) < 0) {
            putHelperContains(b.right, key, value);
        } else {
            putHelperContains(b.left, key, value);
        }

    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        } else {
            return getHelper(node, key);
        }
    }

    public V getHelper(BSTNode b, K key) {
        if (b.key.compareTo(key) == 0) {
            return b.value;
        }

        if (b.key.compareTo(key) < 0) {
            return getHelper(b.right, key);
        } else {
            return getHelper(b.left, key);
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (node.key == null) {
            return false;
        }
        return containsKeyHelper(node, key);
    }

    public boolean containsKeyHelper(BSTNode b, K key) {
        if (b == null) {
            return false;
        } else if (b.key.compareTo(key) == 0) {
            return true;
        }

        if (b.key.compareTo(key) < 0) {
            return containsKeyHelper(b.right, key);
        } else {
            return containsKeyHelper(b.left, key);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        node.key = null;
        node.value = null;
        node.left = null;
        node.right = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> s = new HashSet<>();
        for (K key : this) {
            s.add(key);
        }

        return s;
    }

    /**
     * 递归调用，实现流程比较tricky
     * 注意 “node= removeHelper(node,key);”
     * 不能够直接return，需要有变量承接
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }

        V re = get(key);
        node= removeHelper(node,key);
        size--;
        return re;
    }

    public BSTNode removeHelper(BSTNode n, K key) {
        if (n.key.compareTo(key) == 0) {
            if (n.left != null && n.right != null) {
                //将找到的左边节点最大值进行替换
                //递归删除左边节点最大值
                n.key = findMaxLeft(n).key;
                n.value = findMaxLeft(n).value;
                return removeHelper(n.left,n.key);
            } else if (n.left == null && n.right == null) {
                return null;
            } else {
                if (n.left != null) {
                    return n.left;
                } else {
                    return n.right;
                }
            }
        }

        if (n.key.compareTo(key) < 0) {
            n.right = removeHelper(n.right, key);
        } else if (n.key.compareTo(key) > 0) {
            n.left = removeHelper(n.left, key);
        }
        return n;
    }

    public BSTNode findMaxLeft(BSTNode n) {
        BSTNode left = n.left;
        while (left.right != null) {
            left = left.right;
        }
        return left;
    }
}
