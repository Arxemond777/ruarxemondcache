package ru.arxemond.cache.service;

import ru.arxemond.cache.handler.expire.ExpireHandler;
import ru.arxemond.cache.util.Trio;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static ru.arxemond.cache.config.ConfigureConst.SCHEDULED_EXECUTOR_COUNT;
import static ru.arxemond.cache.util.wrapper.Wrapper.wrapConfigConst;

// -Dexception.handler.enabled=false

/**
 * TODO make row class (String)(EXCEPTION_HANDLER_ENABLED.getPair().getValue()))
 */
public class InitService {
    static {
        /*System.out.println(EXCEPTION_HANDLER_ENABLED.getPair().getKey());
        System.out.println(EXCEPTION_HANDLER_PATH.getPair().getKey());*/

        System.out.println("don`t foget for me");
        /*if (
                Objects.isNull(System.getProperty(((String)(EXCEPTION_HANDLER_ENABLED.getPair().getValue()))))
                || Boolean.parseBoolean(System.getProperty(((String)(EXCEPTION_HANDLER_ENABLED.getHashMap().get(String.class)))))
                ) {
            ExceptionHandler.getInstance();
        }*/
        final ConcurrentHashMap<Trio<?, ?, ?>, ?> concurrentHashMap = new ConcurrentHashMap<>(1000);
        final ScheduledExecutorService scheduledExecutorService
                = Executors.newScheduledThreadPool((int) wrapConfigConst(SCHEDULED_EXECUTOR_COUNT));

        new ExpireHandler(scheduledExecutorService, concurrentHashMap);
    }
}
