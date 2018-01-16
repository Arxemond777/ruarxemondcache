package ru.arxemond.cache.service;

import ru.arxemond.cache.handler.exception.ExceptionHandler;

import java.util.Objects;

import static ru.arxemond.cache.config.ConfigureConst.EXCEPTION_HANDLER_ENABLED;
import static ru.arxemond.cache.config.ConfigureConst.EXCEPTION_HANDLER_PATH;

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
    }
}
