package ru.arxemond.cache.privatecache;

import ru.arxemond.cache.util.Pair;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public enum Cache {
    INIT;

    /**
     * Map<String, - key
     * Pair<LocalDateTime, - dateCreate
     * Pair<
     * Boolean - hasExpireTime?
     * LocalDateTime, - expireDate
     * >
     * >
     * >
     */
    private static Map<String, Pair<LocalDateTime, Pair<Boolean, LocalDateTime>>> map;

    public static Map<String, Pair<LocalDateTime, Pair<Boolean, LocalDateTime>>> init() {
        Map<String, Pair<LocalDateTime, Pair<Boolean, LocalDateTime>>> mapResult = map;
        if (Objects.isNull(mapResult)) {
            synchronized (Cache.class) {
                mapResult = map;
                if (Objects.isNull(mapResult))
                    mapResult = map = new ConcurrentHashMap<>(10_000);
            }
        }

        return mapResult;
    }
}