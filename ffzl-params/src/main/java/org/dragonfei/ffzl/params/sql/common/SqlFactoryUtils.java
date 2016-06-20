package org.dragonfei.ffzl.params.sql.common;

import org.dragonfei.ffzl.params.sql.common.inter.FFzlSqlFactory;
import org.dragonfei.ffzl.params.sql.common.inter.SqlEntry;
import org.dragonfei.ffzl.params.sql.query.FFzlSqlQueryFactory;
import org.dragonfei.ffzl.params.sql.query.entry.QuerySqlEntry;

/**
 * Created by longfei on 16-6-19.
 */
public class SqlFactoryUtils {
    public static <T extends FFzlSqlFactory>  T getFactory(SqlEntry entry){
        if(entry instanceof QuerySqlEntry){
            return (T)AbstractFFzlSqlFactory.getInstance(FFzlSqlQueryFactory.class);
        }
        return null;
    }
}
