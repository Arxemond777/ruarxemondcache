package ru.arxemond.cache.votemplate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * TODO rework as write in interface
 */
public class DelayFromCurrentTimeVO implements IDelayVO {
    private volatile LocalDateTime expire; //TODO v

    private static final AtomicReferenceFieldUpdater<DelayFromCurrentTimeVO,LocalDateTime> UPDATE_EXPIRE =
            AtomicReferenceFieldUpdater.newUpdater(DelayFromCurrentTimeVO.class, LocalDateTime.class, "expire");

    @Override
    public LocalDateTime getExpire() {
        return expire;
    }

    @Override
    public void setExpire(LocalDateTime expire) {
        lock.lock(); // think more about this case
        if (Objects.isNull(this.expire)) {
            UPDATE_EXPIRE.compareAndSet(this, this.expire, expire);
        }
        lock.unlock();
    }
}
