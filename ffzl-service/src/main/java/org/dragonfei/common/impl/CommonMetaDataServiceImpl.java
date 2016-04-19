package org.dragonfei.common.impl;

import org.dragonfei.common.CommonMetaDataService;
import org.dragonfei.ffzl.annotation.parse.MetaData;
import org.dragonfei.ffzl.annotation.parse.MetaDataBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 16/4/18.
 */
@Service
public class CommonMetaDataServiceImpl implements CommonMetaDataService {

    @Cacheable(cacheNames ={"metadata"},key = "#object.getId()")
    @Override
    public <T> MetaData getMetaData(Class<T> clazz) {
        System.out.println("test ---");
        return MetaDataBuilder.instance().build(clazz);
    }
}
