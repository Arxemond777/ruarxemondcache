package ru.arxemond.cache.privatecache;

import ru.arxemond.cache.util.Pair;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public enum CacheSimple implements ICache<Map<String, Pair<Object, Class<?>>>> {
    INIT {
        @Override
        public Map<String, Pair<Object, Class<?>>> initial(int initialCapacity) {
            Map<String, Pair<Object, Class<?>>> mapResult = map;
            if (Objects.isNull(mapResult)) {
                synchronized (CacheSimple.class) {
                    mapResult = map;
                    if (Objects.isNull(mapResult))
                        map = new ConcurrentHashMap<>(initialCapacity);
                }
            }

            return mapResult;
        }
    };

    @Override
    public Pair<Object, Class<?>> getValue(String name) {
        return map.get(name);
    }

    @Override
    public void setValue(String name, Object value, Class<?> typeClass) {
        map.put(name, new Pair<>(value, typeClass));
    }

    /**
     * Map<String, - key
     * Pair<
     * Object, - value
     * Class<?> - class of value
     *     >>
     */
    private static Map<String, Pair<Object, Class<?>>> map;
}