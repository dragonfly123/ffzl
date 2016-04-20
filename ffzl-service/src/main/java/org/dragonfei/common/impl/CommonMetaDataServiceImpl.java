package org.dragonfei.common.impl;

import org.dragonfei.common.CommonMetaDataService;
import org.dragonfei.ffzl.annotation.domain.SqlParam;
import org.dragonfei.ffzl.annotation.parse.MetaData;
import org.dragonfei.ffzl.annotation.parse.MetaDataBuilder;
import org.dragonfei.ffzl.annotation.sql.SqlCreator;
import org.dragonfei.ffzl.exception.SystemExcption;
import org.dragonfei.ffzl.exception.Type;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * Created by admin on 16/4/18.
 */
@Service
public class CommonMetaDataServiceImpl implements CommonMetaDataService {

    @Cacheable(cacheNames ={"metadata"})
    @Override
    public <T> MetaData getMetaData(Class<T> clazz) {

        return MetaDataBuilder.instance().build(clazz);
    }

    //force the metadata cache effect
    public <T> SqlParam getSqlParam(T object,MetaData metaData,SqlType sqlType){
        switch (sqlType){
            case INSERT:
                return getInsertSqlParam(object,metaData);
            case DELETE:
                return getDeleteSqlParam(object,metaData);
            case UPDATE:
                return getUpdateSqlParam(object,metaData);
            case SELECT:
                return getSelectSqlParam(object,metaData);
            default:
                throw new SystemExcption("不存在的操作!",Type.COSTOMER);
        }
    }

    private <T> SqlParam getInsertSqlParam(T object,MetaData metaData){
        if(metaData != null) {
            return SqlCreator.SIMPLE.createInsert(object, metaData);
        } else {
            return SqlCreator.SIMPLE.createInsert(object,getMetaData(object.getClass()));
        }
    }

    private <T> SqlParam getDeleteSqlParam(T object,MetaData metaData){
        if(metaData != null) {
            return SqlCreator.SIMPLE.createDelete(object, metaData);
        } else {
            return SqlCreator.SIMPLE.createDelete(object,getMetaData(object.getClass()));
        }
    }

    private<T> SqlParam getUpdateSqlParam(T object,MetaData metaData){
        if(metaData != null){
            return  SqlCreator.SIMPLE.createUpdate(object,metaData);
        } else {
            return SqlCreator.SIMPLE.createUpdate(object,getMetaData(object.getClass()));
        }
    }

    private <T> SqlParam getSelectSqlParam(T object,MetaData metaData){
        if(metaData != null){
            return SqlCreator.SIMPLE.createSelect(object,metaData);
        } else {
            return SqlCreator.SIMPLE.createSelect(object,getMetaData(object.getClass()));
        }
    }
}
