package org.dragonfei.ffzl.params.sql.update;

import org.dragonfei.ffzl.params.sql.common.AbstractFFzlSqlFactory;
import org.dragonfei.ffzl.params.sql.common.inter.OperationEntry;
import org.dragonfei.ffzl.params.sql.common.inter.SqlEntry;
import org.dragonfei.ffzl.params.sql.common.inter.SqlOperation;
import org.dragonfei.ffzl.params.sql.query.FFzlSqlQueryFactory;
import org.dragonfei.ffzl.params.sql.update.entry.UpdateOperationEntry;
import org.dragonfei.ffzl.params.sql.update.entry.UpdateSqlEntry;
import org.dragonfei.ffzl.params.sql.update.operation.FfzlBkQuery;
import org.dragonfei.ffzl.params.sql.update.operation.FfzlInsert;
import org.dragonfei.ffzl.params.sql.update.operation.FfzlModify;
import org.dragonfei.ffzl.params.sql.update.operation.UpdateSqlOperation;

/**
 * Created by longfei on 16-6-19.
 */
public class FFzlSqlUpdateFactory extends AbstractFFzlSqlFactory {

    private FFzlSqlUpdateFactory(){}

    static {
        register(FFzlSqlQueryFactory.class,new FFzlSqlUpdateFactory());
    }
    @Override
    protected boolean support(SqlEntry entry) {
        return entry instanceof UpdateSqlEntry;
    }

    @Override
    protected OperationEntry getSupportSqlEntry(SqlEntry sqlEntry) {
        UpdateSqlEntry updateSqlEntry = (UpdateSqlEntry)sqlEntry;
        UpdateOperationEntry updateOperationEntry = new UpdateOperationEntry();

        updateOperationEntry.ffzlBkQuery = new FfzlBkQuery(dataSource,
                updateSqlEntry.table,updateSqlEntry.pkColumn,updateSqlEntry.bkColumnList,updateSqlEntry.outputs);
        updateOperationEntry.ffzlInsert = new FfzlInsert(dataSource,updateSqlEntry.table,updateSqlEntry.columnNameList);
        updateOperationEntry.ffzlModify = new FfzlModify(dataSource,updateSqlEntry.table,updateSqlEntry.pkColumn,updateSqlEntry.columnNameList);

        return updateOperationEntry;
    }

    @Override
    protected boolean support(OperationEntry entry,SqlEntry sqlEntry) {
        return entry instanceof UpdateOperationEntry && sqlEntry instanceof UpdateSqlEntry;
    }

    @Override
    protected SqlOperation getSupportSqlSeed(OperationEntry entry,SqlEntry sqlEntry) {

        return new UpdateSqlOperation((UpdateOperationEntry) entry,(UpdateSqlEntry) sqlEntry);
    }
}
