package homework02;

import java.util.*;

import static java.lang.Math.abs;

//HashMap с открытой адресацией
public class SimpleHashMap<K, V> implements SimpleMap<K, V> {
    private final static int step = 13; //шаг прибавления индекса при коллизии (взаимно простой с capacity)

    private final static int initial_capacity = 16;

    private int capacity = initial_capacity;

    private final double loadFactor;

    private int size = 0;

    private Node<K, V>[] nodes = (Node<K, V>[]) new Node[initial_capacity];

    public SimpleHashMap(double loadFactor) {
        this.loadFactor = loadFactor;
    }

    public SimpleHashMap() {
        this(0.75);
    }

    @Override
    public V put(K key, V value) {
        if (size >= capacity * loadFactor) {
            resize();
        }
        Node<K, V> node = findKey(key);
        if (node != null) {
            node.v = value;
        } else {
            //keySet.add(key);
            int hash = abs(key.hashCode());
            int pos = hash % capacity;
            node = nodes[pos];
            while ((node != null) && !node.empty) {
                pos = (pos + step) % capacity;
                node = nodes[pos];
            }
            node = new Node<K, V>(key, value);
            nodes[pos] = node;
            size++;
        }

        return value;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = findKey(key);

        if (node == null) return null;

        return node.v;
    }

    @Override
    public V remove(K key) {
        Node<K, V> node = findKey(key);

        if (node == null) return null;

        node.empty = true;
        size--;

        return node.v;
    }

    @Override
    public boolean contains(K key) {
        return findKey(key) != null;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public Set<K> keySet() {
        Set<K> ks = new SimpleHashMap.KeySet();

        return ks;
    }

    final class KeySet extends AbstractSet<K> {
        @Override
        public Iterator<K> iterator() {
            return new Iterator<K>() {
                private int pos = 0;

                @Override
                public boolean hasNext() {
                    int p = pos;
                    while (p < capacity) {
                        Node<K, V> node = nodes[p++];
                        if ((node != null) && (!node.empty)) return true;
                    }
                    return false;
                }

                @Override
                public K next() {
                    while (pos < capacity) {
                        Node<K, V> node = nodes[pos++];
                        if ((node != null) && (!node.empty)) return node.k;
                    }
                    return null;
                }
            };
        }

        @Override
        public int size() {
            return size;
        }
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<V>();
        for (int i = 0; i < capacity; i++) {
            if ((nodes[i] != null) && (!nodes[i].empty)) {
                values.add(nodes[i].v);
            }
        }
        return values;
    }

    private Node<K, V> findKey(K k) {
        int hash = abs(k.hashCode());
        int pos = hash % capacity;
        Node<K, V> node = nodes[pos];
        while ((node != null) && (!(hash == node.hash) || !k.equals(node.k))) {
            pos = (pos + step) % capacity;
            node = nodes[pos];
        }
        return node;
    }

    private void resize() {
        Node<K, V>[] old_nodes = nodes;

        capacity *= 2;
        nodes = (Node<K, V>[]) new Node[capacity];
        size = 0;
        //keySet.clear();
        for (Node<K, V> node : old_nodes) {
            if ((node != null) && !node.empty) {
                put(node.k, node.v);
            }
        }
    }

    private class Node<K, V> {
        private final int hash;
        private final K k;
        private V v;
        private boolean empty = true;

        Node(K k, V v) {
            this.k = k;
            this.v = v;
            this.hash = abs(k.hashCode());
            this.empty = false;
        }
    }
}
