package org.dragonfei.ffzl.params.sql.update;

import org.dragonfei.ffzl.params.sql.common.AbstractFFzlSqlFactory;
import org.dragonfei.ffzl.params.sql.common.inter.OperationEntry;
import org.dragonfei.ffzl.params.sql.common.inter.SqlEntry;
import org.dragonfei.ffzl.params.sql.common.inter.SqlOperation;
import org.dragonfei.ffzl.params.sql.query.FFzlSqlQueryFactory;

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
        return false;
    }

    @Override
    protected OperationEntry getSupportSqlEntry(SqlEntry sqlEntry) {
        return null;
    }

    @Override
    protected boolean support(OperationEntry entry) {
        return false;
    }

    @Override
    protected SqlOperation getSupportSqlSeed(OperationEntry entry) {
        return null;
    }
}
