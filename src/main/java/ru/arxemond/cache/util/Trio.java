package ru.arxemond.cache.util;

import javafx.beans.NamedArg;
import javafx.util.Pair;

import java.io.Serializable;

/**
 * This class analogue to the
 * @see javafx.util.Pair
 * but unlike the Pair it contain 3 elements
 */
public class Trio<K,V, T> implements Serializable {
    private T middle;
    private Pair<K, V> pair;

    public K getKey() { return pair.getKey(); }

    public V getValue() { return pair.getValue(); }

    public T getMiddle() {
        return middle;
    }

    public Trio(@NamedArg("key") K key, @NamedArg("value") V value, @NamedArg("value") T middle) {
        pair = new Pair<>(key, value);

        this.middle = middle;
    }

    @Override
    public String toString() {
        return "key=" + pair.getKey() + "; middle=" + this.getMiddle() + "; value=" + pair.getValue();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (pair.getKey() != null ? pair.getKey().hashCode() : 0);
        hash = 31 * hash + (pair.getValue() != null ? pair.getValue().hashCode() : 0);
        hash = 31 * hash + (this.getMiddle() != null ? this.getMiddle().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Trio) {
            Trio trio = (Trio) o;
            if (pair.getKey() != null ? !pair.getKey().equals(trio.pair.getKey()) : trio.pair.getKey() != null) return false;
            if (pair.getValue() != null ? !pair.getValue().equals(trio.pair.getValue()) : trio.pair.getValue() != null) return false;

            if (middle != null ? !middle.equals(trio.getMiddle()) : trio.getMiddle() != null) return false;

            return true;
        }
        return false;
    }
}
