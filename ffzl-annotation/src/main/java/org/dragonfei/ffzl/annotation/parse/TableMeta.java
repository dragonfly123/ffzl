package org.dragonfei.ffzl.annotation.parse;

/**
 * Created by longfei on 16-4-10.
 */
public class TableMeta {

    private String name;
    private String desc;

    public TableMeta(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }
}
