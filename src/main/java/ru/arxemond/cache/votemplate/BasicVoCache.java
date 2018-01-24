package ru.arxemond.cache.votemplate;

import java.util.Objects;

@Deprecated
public class BasicVoCache<T> {

    private T value;
    private IDelayVO iDelayVO;

    public BasicVoCache(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public IDelayVO getiDelayVO() {
        return iDelayVO;
    }

    public void setiDelayVO(IDelayVO iDelayVO) {
        this.iDelayVO = iDelayVO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicVoCache<?> that = (BasicVoCache<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }
}
