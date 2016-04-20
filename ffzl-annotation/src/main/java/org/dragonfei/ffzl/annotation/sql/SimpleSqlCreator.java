package org.dragonfei.ffzl.annotation.sql;

import org.dragonfei.ffzl.annotation.domain.SqlParam;
import org.dragonfei.ffzl.annotation.parse.FieldMeta;
import org.dragonfei.ffzl.annotation.parse.MetaData;
import org.dragonfei.ffzl.exception.SystemExcption;
import org.dragonfei.ffzl.exception.Type;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.date.DateUtils;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 *Created by longfei on 16/4/19.
 */
public class SimpleSqlCreator implements SqlCreator {

    @Override
    public <T> SqlParam createInsert(T object, MetaData metaData) {
        SqlParam sqlParam = new SqlParam();
        Map<String,String> map = Maps.newHashMap();
        List<FieldMeta> list = achiveNotNull(
                object,metaData,(obj,fieldMeta)->map.put(fieldMeta.getColumnName(),obj.toString()),
                ((obj, fieldMeta) ->map.put(fieldMeta.getColumnName(), DateUtils.datetimeNow())),
                (obj, fieldMeta) ->map.put(fieldMeta.getColumnName(), DateUtils.datetimeNow()));

        sqlParam.sql = "INSERT INTO "+
                metaData.getTableMeta().getName()+ " ("+
                StringUtils.toCommaDelimitedString(list.stream().map(
                        filedMata->{sqlParam.params.add(map.get(filedMata.getColumnName()));
                    return "`"+filedMata.getColumnName()+"`";
                }).collect(Collectors.toList()))+") VALUES(" +StringUtils.repeatString("?",list.size())+")";

        return sqlParam;
    }

    private <T> List<FieldMeta> achiveNotNull(T object,MetaData metaData,FieldHandle handle){
        return achiveNotNull(object,metaData,handle,null,null);
    }
    private <T> List<FieldMeta> achiveNotNull(T object,MetaData metaData,FieldHandle handle, FieldHandle creattimeHandle,FieldHandle modifytimeHandle){
        return  metaData.getFiledMetaList().parallelStream().filter(fieldMeta -> {
            Object obj = getValue(fieldMeta,object);
            if(!ObjectUtils.isEmpty(obj)){
                if(handle != null) {
                    synchronized (this) {
                        handle.handle(obj, fieldMeta);
                    }
                }
                return true;
            } else if(creattimeHandle != null && "createtime".equals(fieldMeta.getField().getName().toLowerCase())){
                creattimeHandle.handle(object,fieldMeta);
                return true;
            } else if(modifytimeHandle != null && "lastmodifytime".equals(fieldMeta.getField().getName().toLowerCase())){
                modifytimeHandle.handle(object,fieldMeta);
                return true;
            }
            return false;
        }).collect(Collectors.toList());
    }

    private  List<FieldMeta> achiveByCondition(MetaData metaData, Condition condition, FieldHandle handle){
        return metaData.getFiledMetaList().stream().filter(
                fieldMeta -> condition.pass(fieldMeta, handle)).collect(Collectors.toList());
    }
    private<T> Object getValue(FieldMeta fieldMeta,T object){
        Field field =  fieldMeta.getField();
        try {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), object.getClass());
            Method method = pd.getReadMethod();
            //原生数据类型如何处理
            return method.invoke(object);
        } catch (Exception e){
            throw new SystemExcption(e,Type.SYSTEM);
        }
    }
    @Override
    public <T> SqlParam createUpdate(T object, MetaData metaData) {
        SqlParam sqlParam = new SqlParam();
        Map<String,String> map = Maps.newHashMap();
        List<FieldMeta> listPrimary = Lists.newArrayList();
        List<FieldMeta> fieldMetaList = achiveByCondition(metaData,
                ((fieldMeta, handle) -> {
                    if(fieldMeta.isBk() || fieldMeta.isPk()){
                        listPrimary.add(fieldMeta);
                    } else {
                        Object obj = getValue(fieldMeta,object);
                        if(!ObjectUtils.isEmpty(obj)){
                            if(handle != null) {
                                handle.handle(obj, fieldMeta);
                            }
                            return true;
                        }
                    }
                    return false;
                }),(obj,fieldMeta)->map.put(fieldMeta.getColumnName(),obj.toString()));

        if(listPrimary.isEmpty()){
            return sqlParam;
        }

        sqlParam.sql = "UPDATE " + metaData.getTableMeta().getName() + " SET " +
                StringUtils.toCommaDelimitedString(fieldMetaList.stream().map(fieldMeta -> {
                    sqlParam.params.add(map.get(fieldMeta.getColumnName()));
                    return "`"+fieldMeta.getColumnName()+"`";
                }).collect(Collectors.toList()),"", " = ? ") + " WHERE 1=1 " +
                StringUtils.toCommaDelimitedString(listPrimary.stream().map(fieldMeta1 -> {
            sqlParam.params.add(getValue(fieldMeta1,object).toString());
            return "`" + fieldMeta1.getColumnName() + "`";
                }).collect(Collectors.toList())," AND "," = ? ");
        return sqlParam;
    }

    @Override
    public <T> SqlParam createDelete(T object, MetaData metaData) {
        SqlParam sqlParam = new SqlParam();
        Map<String,String> map = Maps.newHashMap();

        List<FieldMeta> fieldMetaList = achiveNotNull(object,metaData,
                (obj,fieldMeta)->map.put(fieldMeta.getColumnName(),obj.toString()));
        sqlParam.sql = "DELETE FROM " +
                metaData.getTableMeta().getName() +
                " WHERE 1=1 " + StringUtils.toCommaDelimitedString(
                fieldMetaList.stream().map(fieldMeta -> {
                    sqlParam.params.add(map.get(fieldMeta.getColumnName()));
                    return "`" + fieldMeta.getColumnName() + "`";
                }).collect(Collectors.toList()), "", " AND ", " = ? ");

        return sqlParam;
    }

    @Override
    public <T> SqlParam createSelect(T object, MetaData metaData) {
        SqlParam sqlParam = new SqlParam();
        Map<String,String> map = Maps.newHashMap();
        List<FieldMeta> fieldMetaList = achiveByCondition(metaData,
                (fieldMeta,handle)->{
                    if(fieldMeta.isBk() || fieldMeta.isPk()){
                        Object obj = getValue(fieldMeta,object);
                        if(!ObjectUtils.isEmpty(obj)){
                            if(handle != null) {
                                handle.handle(obj, fieldMeta);
                            }
                            return true;
                        }
                    }
                    return false;
                }, (obj,fieldMeta)->map.put(fieldMeta.getColumnName(),obj.toString()));

        sqlParam.sql  = "SELECT * FROM " + metaData.getTableMeta().getName() +
                " WHERE 1=1 " + StringUtils.toCommaDelimitedString(
                fieldMetaList.stream().map(fieldMeta -> {
                    sqlParam.params.add(map.get(fieldMeta.getColumnName()));
                    return "`" + fieldMeta.getColumnName() + "`";
                }).collect(Collectors.toList()), "", " AND ", " = ? ");
        return sqlParam;
    }
}

@FunctionalInterface
interface FieldHandle{
    void handle(Object object,FieldMeta fieldMeta);
}
@FunctionalInterface
interface Condition{
     boolean pass(FieldMeta fieldMeta,FieldHandle handle);
}