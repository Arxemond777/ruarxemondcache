package ru.arxemond.cache.votemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

@Deprecated
public class DelayFromCurrentTimeVO implements IDelayVO {
    private volatile long expire; //TODO v

    private static final AtomicReferenceFieldUpdater<DelayFromCurrentTimeVO, Long> UPDATE_EXPIRE =
            AtomicReferenceFieldUpdater.newUpdater(DelayFromCurrentTimeVO.class, long.class, "expire");

    @Override
    public LocalDateTime getExpire() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(this.expire), ZoneId.systemDefault());
    }

    @Override
    public void setExpire(LocalDateTime expire) {
        UPDATE_EXPIRE.compareAndSet(this, this.expire, expire.atZone(ZoneId.systemDefault()).toEpochSecond());
    }
}
