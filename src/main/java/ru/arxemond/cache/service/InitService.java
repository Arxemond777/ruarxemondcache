package ru.arxemond.cache.service;

import ru.arxemond.cache.handler.exception.ExceptionHandler;
import ru.arxemond.cache.privatecache.CacheSimple;
import ru.arxemond.cache.privatecache.CacheWithExpire;

import java.util.Objects;

import static ru.arxemond.cache.config.Config.INSTANCE;

// -Dexception.handler.enabled=false

public class InitService {
    static {

        /**
         * init ExceptionHandler
         */
        String ehe = INSTANCE.getProperty("exception.handler.enabled");
        if (Objects.isNull(System.getProperty(ehe)) || Boolean.parseBoolean(System.getProperty(ehe)))
            ExceptionHandler.getInstance();

        /**
         * init cache
         * Integer.parseInt() - to avoid boxing/unboxing
         */
        CacheSimple.INIT.initial(Integer.parseInt(INSTANCE.getProperty("cache.simple")));
        CacheWithExpire.INIT.initial(Integer.parseInt(INSTANCE.getProperty("cache.with.expire")));
    }
}
