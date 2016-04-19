package org.dragonfei.ffzl.annotation.sql;

import org.dragonfei.ffzl.annotation.domain.SqlParam;
import org.dragonfei.ffzl.annotation.parse.MetaData;

/**
 * Created by admin on 16/4/19.
 */
public interface SqlCreator {
    <T> SqlParam createInsert(T object, MetaData metaData);
    <T> SqlParam createUpdate(T object,MetaData metaData);
    <T> SqlParam createDelete(T object,MetaData metaData);
    <T> SqlParam createSelect(T object,MetaData metaData);
    SqlCreator SIMPLE = new SimpleSqlCreator();
}
