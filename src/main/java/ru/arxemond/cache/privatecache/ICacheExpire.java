package ru.arxemond.cache.privatecache;

public interface ICacheExpire<T> extends ICache<T> {
    @Override
    default void setValue(String name, Object value, Class<?> typeClass) {}

    void setValue(String name, Object value, Class<?> aClass, int expiresInMillisecond);
}
