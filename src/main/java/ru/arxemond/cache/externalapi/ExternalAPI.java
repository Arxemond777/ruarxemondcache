package ru.arxemond.cache.externalapi;

import ru.arxemond.cache.privatecache.CacheExpire;
import ru.arxemond.cache.privatecache.CacheSimple;
import ru.arxemond.cache.util.Pair;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

/**
 * This class is an external API
 */
public class ExternalAPI {

    /*
     * TODO think
     * return Futures<Pair<Object, Class<?>>>
     */
    public static Pair<Object, Class<?>> getValue() {
        /**
         * run executors with Futures get first element if not null
         */
        return null;
    }

    public static void setValue(String name, Object value) {
        CacheSimple.INIT.setValue(name, value, value.getClass());
    }

    public static void setValue(String name, Object value, int expiresInMillisecond) {
        if (Integer.signum(expiresInMillisecond) != 1)
            throw new IllegalArgumentException("The expires can`t be less than or equal to zero");

        CacheExpire.INIT.setValue(name, value, value.getClass(), expiresInMillisecond);
    }

    public static void setValue(String name, Object value, int expires, TimeUnit timeUnit) {
        switch (timeUnit) {
            case SECONDS: // TODO
                setValue(name, value, (expires << 10) - (expires << 4) - (expires << 3)); // expires * 1000
                break;
            default:
                throw new UnsupportedOperationException(timeUnit.toString() + " doesn`t support yet :(");
        }
    }
}
