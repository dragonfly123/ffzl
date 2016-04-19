package org.dragonfei.common.impl;

import org.dragonfei.common.CommonMetaDataService;
import org.dragonfei.ffzl.annotation.domain.SqlParam;
import org.dragonfei.ffzl.annotation.parse.MetaData;
import org.dragonfei.ffzl.annotation.parse.MetaDataBuilder;
import org.dragonfei.ffzl.annotation.sql.SqlCreator;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Calendar;

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

    public <T> SqlParam getSqlParam(T object){
        System.out.println("-");
        long time = Calendar.getInstance().getTimeInMillis();
        MetaData metaData = getMetaData(object.getClass());
        System.out.println(Calendar.getInstance().getTimeInMillis()-time);
        System.out.println("-");
        return SqlCreator.SIMPLE.createInsert(object,getMetaData(object.getClass()));
    }
}
