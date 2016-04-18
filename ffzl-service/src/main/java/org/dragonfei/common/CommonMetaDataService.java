package org.dragonfei.common;

import org.dragonfei.ffzl.annotation.parse.MetaData;

/**
 * Created by admin on 16/4/18.
 */
public interface CommonMetaDataService {
    <T> MetaData getMetaData(T object);
}
