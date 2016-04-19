package org.dragonfei.ffzl.annotation.parse;

import org.dragonfei.ffzl.utils.collections.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by longfei on 16-4-10.
 */
public class MetaData {

    private TableMeta tableMeta;

    private List<FieldMeta> filedMetaList;

    private  List<FieldMeta> pkList;

    private List<FieldMeta> bkList;
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

    public List<FieldMeta> getPkList(){
        return Lists.addAll(filedMetaList.stream().
                filter(fieldMeta -> fieldMeta.isPk()).
                collect(Collectors.toList()),pkList);

    }

    public List<FieldMeta> getBkList(){
        return Lists.addAll(filedMetaList.stream().
                filter(fieldMeta -> fieldMeta.isBk()).
                collect(Collectors.toList()),bkList);
    }
}
