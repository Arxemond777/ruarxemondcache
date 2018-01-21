package ru.arxemond.cache.votemplate;

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
}
