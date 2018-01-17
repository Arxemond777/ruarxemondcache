package ru.arxemond.cache.handler.expire;

import ru.arxemond.cache.util.Trio;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;

public class ExpireHandler {
    private final ScheduledExecutorService scheduledExecutorService;
    private final ConcurrentHashMap<Trio<?, ?, ?>, ?> concurrentHashMap;

    public ExpireHandler(ScheduledExecutorService scheduledExecutorService, ConcurrentHashMap<Trio<?, ?, ?>, ?> concurrentHashMap) {
        this.scheduledExecutorService = scheduledExecutorService;
        this.concurrentHashMap = concurrentHashMap;
    }
}
