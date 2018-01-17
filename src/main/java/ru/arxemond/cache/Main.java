package ru.arxemond.cache;

import ru.arxemond.cache.service.InitService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static ru.arxemond.cache.config.ConfigureConst.SCHEDULED_EXECUTOR_COUNT;
import static ru.arxemond.cache.util.wrapper.Wrapper.wrapConfigConst;

public class Main {
    private static final ExecutorService executorService = Executors.newWorkStealingPool();
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
