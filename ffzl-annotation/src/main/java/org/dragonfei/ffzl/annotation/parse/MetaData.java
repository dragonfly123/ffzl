package org.dragonfei.ffzl.annotation.parse;

import java.util.List;

/**
 * Created by longfei on 16-4-10.
 */
public class MetaData {

    private TableMeta tableMeta;

    private List<FieldMeta> filedMetaList;

    public MetaData(TableMeta tableMeta, List<FieldMeta> filedMetaList) {
        this.tableMeta = tableMeta;
        this.filedMetaList = filedMetaList;
    }

    public TableMeta getTableMeta() {
        return tableMeta;
    }

    public List<FieldMeta> getFiledMetaList() {
        return filedMetaList;
    }
}
