package homework02;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.abs;

//HashMap с открытой адресацией
public class SimpleHashMap<K, V> implements SimpleMap<K, V> {
    private final static int step = 13; //шаг прибавления индекса при коллизии (взаимно простой с capacity)

    private final static int initial_capacity = 16;

    private int capacity = initial_capacity;

    private final double loadFactor;

    private Set<K> keySet = new HashSet<K>();

    //private Collection<V> values = new ArrayList<V>();

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
        if (contains(key)) {
            Node<K, V> node = findKey(key);
            node.v = value;
        } else {
            keySet.add(key);
            int hash = abs(key.hashCode());
            int pos = hash % capacity;
            Node<K, V> node = nodes[pos];
            while ((node != null) && !node.empty) {
                pos = (pos + step) % capacity;
                node = nodes[pos];
            }
            node = new Node<K, V>(key, value);
            nodes[pos] = node;
            size++;
            return value;
        }

        return value;
    }

    @Override
    public V get(K key) {
        if (!contains(key)) {
            return null;
        }
        Node<K, V> node = findKey(key);

        return node.v;
    }

    @Override
    public V remove(K key) {
        if (!contains(key)) {
            return null;
        }
        Node<K, V> node = findKey(key);
        keySet.remove(key);
        node.empty = true;
        size--;
        return node.v;
    }

    @Override
    public boolean contains(K key) {
        return keySet.contains(key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        return keySet;
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
        while (!(hash==node.hash) || !k.equals(node.k)) {
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
        keySet.clear();
        for (Node<K, V> node : old_nodes) {
            if ((node!=null) && !node.empty) {
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
