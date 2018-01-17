package ru.arxemond.cache.config;

import java.io.File;
import java.util.HashMap;

import javafx.util.Pair;

public enum ConfigureConst {
    EXCEPTION_HANDLER_ENABLED(String.class, "exception.handler.enabled"),
    EXCEPTION_HANDLER_PATH(boolean.class, "exception.handler.path"),
    SCHEDULED_EXECUTOR_COUNT(int.class, 3);

    public static final String FS = File.separator;
    private final Pair<Class<?>, Object> pair;

    ConfigureConst(Class<?> vClass, Object value) {
        pair = new Pair<>(vClass, value);
    }

    public Pair<Class<?>, Object> getPair() {
        return pair;
    }
}