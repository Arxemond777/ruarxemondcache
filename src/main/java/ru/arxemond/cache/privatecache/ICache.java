package ru.arxemond.cache.privatecache;

public interface ICache<T> {
    T initial(int initialCapacity);
}
