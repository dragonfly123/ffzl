package org.dragonfei.ffzl.annotation.sql;

import org.dragonfei.ffzl.annotation.domain.SqlParam;
import org.dragonfei.ffzl.annotation.parse.FieldMeta;
import org.dragonfei.ffzl.annotation.parse.MetaData;
import org.dragonfei.ffzl.exception.SystemExcption;
import org.dragonfei.ffzl.exception.Type;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * Created by admin on 16/4/19.
 */
public class SimpleSqlCreator implements SqlCreator {

    @Override
    public <T> SqlParam createInsert(T object, MetaData metaData) {
        SqlParam sqlParam = new SqlParam();
        String table = metaData.getTableMeta().getName();
        Map<String,String> map = Maps.newHashMap();
        List<FieldMeta> list = metaData.getFiledMetaList().parallelStream().filter(fieldMeta -> {
            Field field =  fieldMeta.getField();
            try {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(),object.getClass());
                Method method = pd.getReadMethod();
                //原生数据类型如何处理
                Object obj = method.invoke(object);
                if(!ObjectUtils.isEmpty(obj)){
                    map.put(fieldMeta.getColumnName(),obj.toString());
                    return true;
                }
            } catch (Exception e) {
                throw new SystemExcption(e,Type.SYSTEM);
            }
            return false;
        }).collect(Collectors.toList());

        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ").append(table).
                append(" (").append(
                StringUtils.toCommaDelimitedString(
                        list.stream().map(
                                filedMata->{
                                    sqlParam.params.add(map.get(filedMata.getColumnName()));
                                    return "`"+filedMata.getColumnName()+"`";
                                }).
                        collect(Collectors.toList()))).append(") VALUES(").
                append(StringUtils.repeatString("?",list.size())).append(")");

        sqlParam.sql = sqlBuilder.toString();

        return sqlParam;
    }

    @Override
    public <T> SqlParam createUpdate(T object, MetaData metaData) {
        return null;
    }

    @Override
    public <T> SqlParam createDelete(T object, MetaData metaData) {
        return null;
    }

    @Override
    public <T> SqlParam createSelect(T object, MetaData metaData) {
        return null;
    }
}
