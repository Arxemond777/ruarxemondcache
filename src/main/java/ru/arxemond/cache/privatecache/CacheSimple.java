package ru.arxemond.cache.privatecache;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public enum CacheSimple implements ICache<Map<String, ?>> {
    INIT {
        @Override
        public Map<String, ?> initial(int initialCapacity) {
            Map<String, ?> mapResult = map;
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

    /**
     * Map<String, - key
     * ?> - value
     */
    private static Map<String, ?> map;
}