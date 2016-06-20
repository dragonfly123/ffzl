package org.dragonfei.ffzl.params.sql.query;

import org.dragonfei.ffzl.params.sql.common.AbstractFFzlSqlFactory;
import org.dragonfei.ffzl.params.sql.common.inter.OperationEntry;
import org.dragonfei.ffzl.params.sql.common.inter.SqlEntry;
import org.dragonfei.ffzl.params.sql.common.inter.SqlOperation;
import org.dragonfei.ffzl.params.sql.query.entry.QueryOperationEntry;
import org.dragonfei.ffzl.params.sql.query.entry.QuerySqlEntry;
import org.dragonfei.ffzl.params.sql.query.operation.FfzlSqlQuery;
import org.dragonfei.ffzl.params.sql.query.operation.FfzlSqlQueryTotal;
import org.dragonfei.ffzl.params.sql.query.operation.QuerySqlOperation;

/**
 * Created by longfei on 16-5-2.
 */
public class FFzlSqlQueryFactory extends AbstractFFzlSqlFactory {

    private FFzlSqlQueryFactory(){}

    static {
        register(FFzlSqlQueryFactory.class,new FFzlSqlQueryFactory());
    }

    @Override
    protected boolean support(SqlEntry entry) {
        return entry instanceof QuerySqlEntry;
    }

    @Override
    protected OperationEntry getSupportSqlEntry(SqlEntry entry) {
        QuerySqlEntry sqlEntry = (QuerySqlEntry) entry;
        QueryOperationEntry queryEntry = new QueryOperationEntry();
        queryEntry.ffzlSqlQuery = new FfzlSqlQuery(dataSource,sqlEntry.querySql,sqlEntry.outputs);
        queryEntry.ffzlSqlQueryTotal = new FfzlSqlQueryTotal(dataSource,sqlEntry.totalSql);
        return queryEntry;
    }

    @Override
    protected boolean support(OperationEntry entry,SqlEntry sqlEntry) {
        return entry instanceof QueryOperationEntry;
    }

    @Override
    protected SqlOperation getSupportSqlSeed(OperationEntry entry,SqlEntry sqlEntry) {
        return new QuerySqlOperation((QueryOperationEntry) entry);
    }


}
