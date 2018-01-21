package ru.arxemond.cache;

import ru.arxemond.cache.service.InitService;

public class Main {
    private static final Object object = new Object();

    public static void main(String[] args) {

        new InitService();

        synchronized (object) {
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
