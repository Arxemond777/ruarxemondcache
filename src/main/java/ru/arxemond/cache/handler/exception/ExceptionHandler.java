package ru.arxemond.cache.handler.exception;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.io.File.separator;

/**
 * This thread run daemon-thread
 * this daemon-thread will be log exception
 * in default log path or set -Dexception.handler.path=your_path
 *
 * If you don`t want the logs to be written, set -Dexception.handler.enabled=false
 */
public final class ExceptionHandler {
    private static volatile ExceptionHandler exceptionHandlerInstance;

    private final static ExecutorService executorService = Executors.newSingleThreadExecutor(r -> {
        Thread thread = Executors.defaultThreadFactory().newThread(r);
        thread.setDaemon(true);
        return thread;
    });
    // TODO SynchronousQueues

    private final static String
            pathForLog = !"".equals(System.getProperty("exception.handler.path"))
            ? (System.getProperty("os.name").toLowerCase().startsWith("windows") // if exception.handler.path the default
                    ?
                        "C:" + separator + "Users" + separator + System.getProperty("user.name") + separator
                        + "AppData" + separator + "Local" + separator + "Temp" + separator
                        + "ruarxemondcache" + separator // TODO ruarxemondcache in enum
                    : separator  + "tmp" + separator) + "ruarxemondcache" + separator

            : System.getProperty("exception.handler.path");

    private static Lock lock = new ReentrantLock();

    private ExceptionHandler() {
    }

    public static ExceptionHandler getInstance() {
        ExceptionHandler exceptionHandler = exceptionHandlerInstance;

        if (!Objects.nonNull(exceptionHandler)) {
            lock.lock();

            try {
                ExceptionHandler.executorService.execute(() -> {
                    System.out.println("I`m alive!" + ExceptionHandler.pathForLog);
                    //new File(ExceptionHandler.pathForLog);
                });
            } finally {
                lock.unlock();
            }
        }

        return exceptionHandler;
    }
}
