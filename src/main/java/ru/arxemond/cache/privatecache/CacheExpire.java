package ru.arxemond.cache.privatecache;

import ru.arxemond.cache.util.Pair;
import ru.arxemond.cache.util.collection.SelfExpiringHashMap;
import ru.arxemond.cache.util.collection.SelfExpiringMap;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static ru.arxemond.cache.config.Config.INSTANCE;

public enum  CacheExpire implements ICacheExpire<Map<String, Pair<Object, Class<?>>>> {
    INIT {
        @Override
        public Map<String, Pair<Object, Class<?>>> initial() {
            Map<String, Pair<Object, Class<?>>> mapResult = map;
            if (Objects.isNull(mapResult)) {
                synchronized (CacheExpire.class) {
                    mapResult = map;
                    if (Objects.isNull(mapResult))
                        map = new SelfExpiringHashMap<>(CacheExpire.initialCapacity);

                }

            }

            return mapResult;
        }

    };

    private static final int initialCapacity = Integer.parseInt(INSTANCE.getProperty("cache.with.expire"));

    @Override
    public Optional<Pair<Object, Class<?>>> getValue(String name) {
        return Optional.of(map.get(name));
    }

    private static SelfExpiringMap<String, Pair<Object, Class<?>>> map;

    @Override
    public void setValue(String name, Object value, Class<?> aClass, int expiresInMillisecond) {
        map.put(name, new Pair<>(value, aClass), expiresInMillisecond);
    }
}
