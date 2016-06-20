package org.dragonfei.ffzl.params.sql.common.inter;

import org.dragonfei.ffzl.utils.spring.SpringContextUtils;

import javax.sql.DataSource;

/**
 * Created by longfei on 16-6-19.
 */
public interface FFzlSqlFactory {
    DataSource dataSource = SpringContextUtils.getBean(DataSource.class);
    OperationEntry getSqlEntry(SqlEntry entry);
    SqlOperation getSqlSeed(OperationEntry entry);

}
