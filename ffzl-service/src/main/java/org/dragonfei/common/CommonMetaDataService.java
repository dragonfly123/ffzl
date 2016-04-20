package org.dragonfei.common;

import org.dragonfei.ffzl.annotation.domain.SqlParam;
import org.dragonfei.ffzl.annotation.parse.MetaData;

/**
 * Created by admin on 16/4/18.
 */
public interface CommonMetaDataService {
    enum SqlType{
        INSERT,UPDATE,DELETE,SELECT
    }
    <T> MetaData getMetaData(Class<T> clazz);

    <T> SqlParam getSqlParam(T object,MetaData metaData,SqlType sqlType);
}
