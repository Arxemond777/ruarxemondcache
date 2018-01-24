package ru.arxemond.cache.privatecache;

import ru.arxemond.cache.util.Pair;
import ru.arxemond.cache.util.collection.SelfExpiringHashMap;

import java.util.Map;
import java.util.Objects;

public enum  CacheExpire implements ICache<Map<String, Pair<Object, Class<?>>>> {
    INIT {
        @Override
        public Map<String, Pair<Object, Class<?>>> initial(int initialCapacity) {
            Map<String, Pair<Object, Class<?>>> mapResult = map;
            if (Objects.isNull(mapResult)) {
                synchronized (CacheExpire.class) {
                    mapResult = map;
                    if (Objects.isNull(mapResult))
                        map = new SelfExpiringHashMap<>(initialCapacity);

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

    private static Map<String, Pair<Object, Class<?>>> map;

}
