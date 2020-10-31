package org.pks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArrayListsMap<K, V> implements Map<K, V> {
    private ArrayList<K> keys;
    private ArrayList<V> values;

    public ArrayListsMap() {
        this.keys = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "ArrayMap{" +
                "keys=" + keys +
                ", values=" + values +
                '}';
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    private boolean isContainKeyInKeys(Object key) {
        return keys.contains(key);
    }

    private int indexOfKeyInKeys(Object key) {
        return keys.indexOf(key);
    }

    private V getOldValue(Object key) {
        return values.get(indexOfKeyInKeys(key));
    }

    @Override
    public synchronized V get(Object key) {
        if (isContainKeyInKeys(key)) {
            return getOldValue(key);
        }
        return null;
    }

    @Override
    public synchronized V put(K key, V value) {
        if (!isContainKeyInKeys(key)) {
            keys.add(key);
            values.add(value);
            return null;
        } else {
            V oldVal = getOldValue(key);
            values.set(indexOfKeyInKeys(key), value);
            return oldVal;
        }
    }

    @Override
    public synchronized V remove(Object key) {
        if (!isContainKeyInKeys(key)) {
            return null;
        } else {
            V oldVal = getOldValue(key);
            var indexForRemove = indexOfKeyInKeys(key);
            keys.remove(indexForRemove);
            keys.trimToSize();
            values.remove(indexForRemove);
            values.trimToSize();
            return oldVal;
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.keySet().forEach(k -> put(k, m.get(k)));
    }

    @Override
    public void clear() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }

    @Override
    public Set<K> keySet() {
        return new HashSet<>(keys);
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return Collections.emptySet();
    }

}
