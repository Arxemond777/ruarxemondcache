package ru.arxemond.cache.votemplate;

import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface IDelayVO {

    // TODO LocalDateTime - convert in long
    LocalDateTime getExpire();

    void setExpire(LocalDateTime expire);
}
