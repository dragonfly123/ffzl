package org.dragonfei.ffzl.params.sql.common;

import org.dragonfei.ffzl.params.sql.common.inter.FFzlSqlFactory;
import org.dragonfei.ffzl.params.sql.common.inter.OperationEntry;
import org.dragonfei.ffzl.params.sql.common.inter.SqlEntry;
import org.dragonfei.ffzl.params.sql.common.inter.SqlOperation;
import org.dragonfei.ffzl.utils.collections.Maps;

import java.util.Map;

/**
 * Created by longfei on 16-6-19.
 */
public abstract class AbstractFFzlSqlFactory  implements FFzlSqlFactory {
    @Override
    public OperationEntry getSqlEntry(SqlEntry entry) {
        if(support(entry)){
            return getSupportSqlEntry(entry);
        }
        throw new RuntimeException("不支持");
    }

    @Override
    public SqlOperation getSqlOperation(OperationEntry entry,SqlEntry sqlEntry) {
        if(support(entry,sqlEntry)){
            return getSupportSqlSeed(entry,sqlEntry);
        }
        throw new RuntimeException("不支持");
    }

    abstract protected boolean support(SqlEntry entry);
    abstract protected OperationEntry getSupportSqlEntry(SqlEntry sqlEntry);
    abstract protected boolean support(OperationEntry entry,SqlEntry sqlEntry);
    abstract protected SqlOperation getSupportSqlSeed(OperationEntry entry,SqlEntry sqlEntry);
}
