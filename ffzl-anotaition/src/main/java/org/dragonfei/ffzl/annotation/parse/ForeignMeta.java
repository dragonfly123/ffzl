package org.dragonfei.ffzl.annotation.parse;

import java.math.BigInteger;

/**
 * Created by longfei on 16-4-10.
 */
public class ForeignMeta {

    private BigInteger defaults;

    private Class clazz;

    private String field;

    private boolean isForeign;

    public ForeignMeta() {
    }

    public ForeignMeta(BigInteger defaults, Class clazz, String field) {
        this.defaults = defaults;
        this.clazz = clazz;
        this.field = field;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getField() {
        return field;
    }

    public BigInteger getDefaults() {

        return defaults;
    }

    public void setDefaults(BigInteger defaults) {
        this.defaults = defaults;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isForeign() {
        return isForeign;
    }

    public void setForeign(boolean foreign) {
        isForeign = foreign;
    }
}
