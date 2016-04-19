package org.dragonfei.ffzl.annotation.parse;

import java.lang.reflect.Field;

/**
 * Created by longfei on 16-4-10.
 */
public class FieldMeta {

    private Field field;

    private String columnName;

    private String  desc;

    private boolean required;

    private boolean unique;

    private boolean notNull;

    private boolean pk;

    private boolean bk;

    private ForeignMeta foreignMeta;

    public ForeignMeta getForeignMeta() {
        return foreignMeta;
    }

    public FieldMeta() {
    }

    public FieldMeta(Field field,String columnName, String desc, boolean required, boolean unique, boolean notNull, boolean pk, boolean bk, ForeignMeta foreignMeta) {

        this.field = field;
        this.columnName = columnName;
        this.desc = desc;
        this.required = required;
        this.unique = unique;
        this.notNull = notNull;
        this.pk = pk;
        this.bk = bk;
        this.foreignMeta = foreignMeta;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isUnique() {
        return unique;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public boolean isPk() {
        return pk;
    }

    public boolean isBk() {
        return bk;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public void setPk(boolean pk) {
        this.pk = pk;
    }

    public void setBk(boolean bk) {
        this.bk = bk;
    }

    public void setForeignMeta(ForeignMeta foreignMeta) {
        this.foreignMeta = foreignMeta;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
