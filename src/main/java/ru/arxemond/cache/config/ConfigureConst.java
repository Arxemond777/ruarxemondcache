package ru.arxemond.cache.config;

import java.io.File;
import java.util.HashMap;

import javafx.util.Pair;

public enum ConfigureConst {
    EXCEPTION_HANDLER_ENABLED(String.class, "exception.handler.enabled"),
    EXCEPTION_HANDLER_PATH(boolean.class, "exception.handler.path");

    public static final String FS = File.separator;

    //private final HashMap<Class<?>, Object> hashMap = new HashMap<>();
    private final Pair<Class<?>, Object> pair;
//    /usr/lib/jvm/jdk-9.0.4_linux-x64_bin/jdk-9.0.4!/javafx.base
//    /usr/lib/jvm/jdk-9.0.4_linux-x64_bin/jdk-9.0.4!/javafx.base/javafx

    ConfigureConst(Class<?> vClass, Object value) {
        pair = new Pair<>(vClass, value);
        //hashMap.put(vClass, value);
    }

    public Pair<Class<?>, Object> getPair() {
        return pair;
    }

    /*private final Pair<Class<?>, ?> pair;
    private final String value;
    private final Class<?> vClass;

    ConfigureConst(Class<?> vClass, String value) {
        this.value = value;
        this.vClass = vClass;
    }

    public String getValue() {
        return value;
    }

    public Class<?> getVClass() {
        return vClass;
    }*/
}