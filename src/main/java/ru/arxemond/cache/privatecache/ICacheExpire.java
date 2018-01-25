package ru.arxemond.cache.privatecache;

import ru.arxemond.cache.util.Pair;

import java.util.Optional;

public interface ICacheExpire<T> extends ICache<T, Optional<Pair<Object, Class<?>>>> {
    @Override
    default void setValue(String name, Object value, Class<?> typeClass) {}

    void setValue(String name, Object value, Class<?> aClass, int expiresInMillisecond);
}
