package ru.arxemond.cache;

import ru.arxemond.cache.privatecache.Cache;
import ru.arxemond.cache.service.InitService;

public class Main {

    public static void main(String[] args) {

        new InitService();

        Cache.init()/*.put()*/;

        synchronized (Main.class) {
            try {
                Main.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
