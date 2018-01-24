package ru.arxemond.cache.votemplate;

import java.time.LocalDateTime;
import java.util.Objects;

@Deprecated
public class BasicVO {
    private final Class<?> aClass;
    private final LocalDateTime localDateTime;
    private IDelayVO iDelayVO;

    private BasicVO(Builder builder) {
        this.localDateTime = builder.getLocalDateTime();
        this.aClass = builder.getaClass();
        this.iDelayVO = builder.getiDelayVO();
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Class<?> getaClass() {
        return aClass;
    }

    public IDelayVO getiDelayVO() {
        return iDelayVO;
    }

    /**
     * TODO safe about tread-safety as advise Joshua Bloch
     * for
     */
    public static class Builder {
        // Required parameters
        private final LocalDateTime localDateTime;
        private final Class<?> aClass;

        // optional parameters
        private IDelayVO iDelayVO;

        public Builder(LocalDateTime localDateTime, Class<?> aClass) {
            this.localDateTime = localDateTime;
            this.aClass = aClass;
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public Class<?> getaClass() {
            return aClass;
        }

        public IDelayVO getiDelayVO() {
            return iDelayVO;
        }

        /**
         * can be replace
         * @param iDelayVO
         * @return
         */
        public Builder setiDelayVO(IDelayVO iDelayVO) {
            this.iDelayVO = iDelayVO;
            return this;
        }

        public BasicVO build() {return new BasicVO(this);}
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("BasicVO{");
        stringBuilder.append("localDateTime=");
        stringBuilder.append(localDateTime);
        stringBuilder.append(", aClass=");
        stringBuilder.append(aClass.getName());

        if (Objects.nonNull(iDelayVO)) {
            stringBuilder.append(", iDelayVO=");
            stringBuilder.append(iDelayVO);
        }

        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
