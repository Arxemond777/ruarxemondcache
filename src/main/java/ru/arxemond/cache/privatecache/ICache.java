package ru.arxemond.cache.privatecache;

public interface ICache<T, V> {
    T initial();
    V getValue(String s);
    void setValue(String name, Object value, Class<?> typeClass);
}
