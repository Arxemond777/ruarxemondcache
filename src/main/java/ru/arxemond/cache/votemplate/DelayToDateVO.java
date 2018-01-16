package ru.arxemond.cache.votemplate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * TODO rework as write in interface
 */
public class DelayToDateVO implements IDelayVO {
    private volatile LocalDateTime expire; //TODO v

    private static final AtomicReferenceFieldUpdater<DelayToDateVO,LocalDateTime> UPDATE_EXPIRE =
            AtomicReferenceFieldUpdater.newUpdater(DelayToDateVO.class, LocalDateTime.class, "expire");

    @Override
    public LocalDateTime getExpire() {
        return expire;
    }

    @Override
    public void setExpire(LocalDateTime expire) {
        lock.lock(); // TODO think more about this case
        if (Objects.isNull(this.expire)) {
            UPDATE_EXPIRE.compareAndSet(this, this.expire, expire);
        }
        lock.unlock();
    }
}
