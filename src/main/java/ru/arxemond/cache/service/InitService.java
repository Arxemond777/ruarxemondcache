package ru.arxemond.cache.service;

import ru.arxemond.cache.handler.exception.ExceptionHandler;
import ru.arxemond.cache.handler.expire.ExpireHandler;
import ru.arxemond.cache.util.Trio;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static ru.arxemond.cache.config.Config.INSTANCE;

// -Dexception.handler.enabled=false

/**
 * TODO make row class (String)(EXCEPTION_HANDLER_ENABLED.getPair().getValue()))
 */
public class InitService {
    static {

        System.out.println("don`t foget for me");

        String ehe = INSTANCE.getProperty("exception.handler.enabled");
        if (Objects.isNull(System.getProperty(ehe)) || Boolean.parseBoolean(System.getProperty(ehe)))
            ExceptionHandler.getInstance();
        //    private static final ExecutorService executorService = Executors.newWorkStealingPool();


        final ConcurrentHashMap<Trio<?, ?, ?>, ?> concurrentHashMap = new ConcurrentHashMap<>(1000);
        final ScheduledExecutorService scheduledExecutorService
                = Executors.newScheduledThreadPool(Integer.valueOf(INSTANCE.getProperty("scheduled.executor.count")));

        new ExpireHandler(scheduledExecutorService, concurrentHashMap);
    }
}
