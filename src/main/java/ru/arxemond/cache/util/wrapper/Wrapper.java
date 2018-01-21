package ru.arxemond.cache.util.wrapper;

import ru.arxemond.cache.config.ConfigureConst;

/**
 * util class
 */
public class Wrapper {
    private Wrapper() {
    }

    public static Object wrapConfigConst(ConfigureConst configureConst) {
        AbstractFactory factory = FactoryProducer.getFactory(configureConst);
//        return factory.createConfigureConst(configureConst).getInt();

        // TODO think about realisation
        return configureConst.getPair().getValue().getClass().cast(configureConst.getPair().getValue());
    }
}

class FactoryProducer {
    public static AbstractFactory getFactory(Object o) {

        if (o instanceof ConfigureConst) {
            return new ConfigureConstFactory();
        }

        return null;
    }
}

abstract class AbstractFactory {
    abstract A createConfigureConst(ConfigureConst configureConst);
}

interface A {
    int getInt();
}

class ConfigureConstFactory extends AbstractFactory {

    @Override
    A createConfigureConst(ConfigureConst configureConst) {

        if (configureConst == null) {
            return null;
        }

        if (configureConst.equals(ConfigureConst.SCHEDULED_EXECUTOR_COUNT)) {
            return new SheduleExecuteCount();
        }

        return null;
    }

}

class SheduleExecuteCount implements A {

    @Override
    public int getInt() {
        return 0;
    }
}