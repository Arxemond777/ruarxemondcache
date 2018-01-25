package ru.arxemond.cache.privatecache;

import ru.arxemond.cache.util.Trio;
import ru.arxemond.cache.util.collection.ConcurrentLinkedHashMap;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


import static ru.arxemond.cache.config.Config.INSTANCE;

public enum CacheSimple implements ICache<Map<String, Trio<Object,Class<?>,Long>>, Optional<Trio<Object,Class<?>,Long>>> {
    INIT {
        @Override
        public Map<String, Trio<Object, Class<?>, Long>> initial() {
            Map<String, Trio<Object, Class<?>, Long>> mapResult = map;
            if (Objects.isNull(mapResult)) {
                synchronized (CacheSimple.class) {
                    mapResult = map;
                    if (Objects.isNull(mapResult)) {
                        map = new ConcurrentLinkedHashMap<>(CacheSimple.initialCapacity);
//                        map = new ConcurrentHashMap<>(CacheSimple.initialCapacity);
                    }
                }
            }

            return mapResult;
        }
    };

    @Override
    public Optional<Trio<Object,Class<?>,Long>> getValue(String name) {
        return Optional.of(map.get(name));
    }

    @Override
    public void setValue(String name, Object value, Class<?> typeClass) {
        this.setValue(name, value, typeClass, LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());
    }

    private void setValue(String name, Object value, Class<?> typeClass, long dateCreate) {
        long size = map.entrySet().size();
        // TODO run Daemon thread which will be check size?
        //if ((size * 100 / CacheSimple.initialCapacity) > percentAfter)
            // TODO write on disk on new thread

        map.put(name, new Trio<>(value, typeClass, dateCreate));
    }

    /**
     * Map<String, - key
     * Trio<
     * Object, - value
     * Class<?>, - class of value
     * dateCreate<?> - dateCreate
     *     >>
     */
    private static Map<String, Trio<Object, Class<?>, Long>> map;
    private static final int initialCapacity = Integer.parseInt(INSTANCE.getProperty("cache.simple"));
    private static final long percentAfter = Long.parseLong(INSTANCE.getProperty("cache.simple.size.whit.start.write.on.disk"));
}