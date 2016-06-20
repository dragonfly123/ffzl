package org.dragonfei.ffzl.params.sql.query.entry;

import org.dragonfei.ffzl.params.sql.common.inter.OperationEntry;
import org.dragonfei.ffzl.params.sql.query.operation.FfzlSqlQuery;
import org.dragonfei.ffzl.params.sql.query.operation.FfzlSqlQueryTotal;

/**
 * Created by longfei on 16-6-19.
 */
public class QueryOperationEntry implements OperationEntry {
    public FfzlSqlQuery ffzlSqlQuery;
    public FfzlSqlQueryTotal ffzlSqlQueryTotal;
}
