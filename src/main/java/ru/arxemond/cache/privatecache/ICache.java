package ru.arxemond.cache.privatecache;

import ru.arxemond.cache.util.Pair;

public interface ICache<T> {
    T initial(int initialCapacity);
    Pair<Object, Class<?>> getValue(String s);
    void setValue(String name, Object value, Class<?> typeClass);
}
