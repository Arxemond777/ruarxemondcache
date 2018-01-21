package ru.arxemond.cache.privatecache;

import ru.arxemond.cache.util.Pair;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    /**
     * Map<String, - key
     *  Pair<LocalDateTime, - dateCreate
     *      Pair<
     *          Boolean - hasExpireTime?
     *          LocalDateTime, - expireDate
     *      >
     *  >
     * >
     */
    private final Map<String, Pair<LocalDateTime, Pair<Boolean, LocalDateTime>>> map;

    public Cache() {
        map = new ConcurrentHashMap<>(10_000);
    }
}
