package ru.arxemond.cache;

import ru.arxemond.cache.service.InitService;
import ru.arxemond.cache.votemplate.BasicVO;
import ru.arxemond.cache.votemplate.DelayFromCurrentTimeVO;
import ru.arxemond.cache.votemplate.DelayToDateVO;
import ru.arxemond.cache.votemplate.IDelayVO;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final ExecutorService executorService = Executors.newWorkStealingPool();
    private static final Object object = new Object();

    public static void main(String[] args) {
        new InitService();

        // TODO for delete
        IDelayVO delayToDateVO = new DelayToDateVO();
        delayToDateVO.setExpire(LocalDateTime.now());
        BasicVO.Builder basicVO =
                new BasicVO.Builder(LocalDateTime.now(), String.class)
                        .setiDelayVO(delayToDateVO)
                        //.build()
                ;
        System.out.println(delayToDateVO.getExpire());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        delayToDateVO.setExpire(LocalDateTime.now());
        System.out.println(basicVO.build().getiDelayVO().getExpire());
        // TODO for delete

        synchronized (object) {
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
