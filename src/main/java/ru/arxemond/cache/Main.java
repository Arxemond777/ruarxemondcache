package ru.arxemond.cache;

import ru.arxemond.cache.service.InitService;

public class Main {

    public static void main(String[] args) {

        int i = 5;
        System.out.println((i << 10) - (i << 4) - (i << 3));
        System.out.println(10 >> 1);

        new InitService();

        synchronized (Main.class) {
            try {
                Main.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
