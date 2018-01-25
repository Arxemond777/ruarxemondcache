package ru.arxemond.cache.util.collection;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = 1L;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public ConcurrentLinkedHashMap() {
        super();
    }

    public ConcurrentLinkedHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public ConcurrentLinkedHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public ConcurrentLinkedHashMap(Map t) {
        super(t);
    }

    @Override
    public V put(K key, V value) {
        lock.writeLock().lock();
        try {
            return super.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public V get(Object key) {
        lock.readLock().lock();
        try {
            return super.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        lock.writeLock().lock();
        try {
            super.putAll(m);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
