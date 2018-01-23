package ru.arxemond.cache.privatecache;

import com.google.common.collect.MapDifference;
import ru.arxemond.cache.util.Pair;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.google.common.collect.Maps;

public enum CacheWithExpire implements ICache<Map<Pair<String, ?>, LocalDateTime>> {
    INIT {
        @Override
        public Map<Pair<String, ?>, LocalDateTime> initial(int initialCapacity) {
            Map<Pair<String, ?>, LocalDateTime> mapResult = map;
            if (Objects.isNull(mapResult)) {
                synchronized (CacheWithExpire.class) {
                    mapResult = map;
                    if (Objects.isNull(mapResult)) {
                        map = new ConcurrentHashMap<>(initialCapacity);
                    }
                }

                scheduledExecutor.scheduleWithFixedDelay(ExpireHandler::new, 0, 1, TimeUnit.SECONDS);
            }

            return mapResult;
        }

    };

    private final static ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    private static Map<Pair<String, ?>, LocalDateTime> map;

    private static class ExpireHandler implements Runnable {
        @Override
        public void run() {
            Map<Pair<String, ?>, LocalDateTime>
                    copyOldMap = new ConcurrentHashMap<>(map); // make copy old collection

            Stream<Map.Entry<Pair<String, ?>, LocalDateTime>> stream =
                    copyOldMap.entrySet().stream();

            if (copyOldMap.size() >= 1_000_000)
                stream = stream.parallel();

            Map<Pair<String, ?>, LocalDateTime> copyMap =
                    stream // generate new collection whit actuality values
                            .filter(t -> t.getValue().compareTo(LocalDateTime.now()) > 0)
                            .collect(Collectors.toConcurrentMap(
                                    k -> new Pair<>(k.getKey().getKey(), k.getKey().getValue()),
                                    Map.Entry::getValue
                            ));


            synchronized (CacheWithExpire.class) { // TODO think more about all case sync

                // TODO check that correctly
                /**
                 * git diff for time checked expires
                 */
                MapDifference<Pair<String, ?>, LocalDateTime> diff = Maps.difference(map, copyOldMap);

                map.putAll(diff.entriesOnlyOnRight());
                map.putAll(copyMap); // overwrite old

            }
        }
    }
}
