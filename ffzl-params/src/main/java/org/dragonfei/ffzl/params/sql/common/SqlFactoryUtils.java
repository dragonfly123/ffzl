package org.dragonfei.ffzl.params.sql.common;

import org.dragonfei.ffzl.params.sql.common.inter.FFzlSqlFactory;
import org.dragonfei.ffzl.params.sql.common.inter.SqlEntry;
import org.dragonfei.ffzl.params.sql.query.FFzlSqlQueryFactory;
import org.dragonfei.ffzl.params.sql.query.entry.QuerySqlEntry;
import org.dragonfei.ffzl.params.sql.update.FFzlSqlUpdateFactory;
import org.dragonfei.ffzl.params.sql.update.entry.UpdateSqlEntry;

/**
 * Created by longfei on 16-6-19.
 */
public class SqlFactoryUtils {
    public static <T extends FFzlSqlFactory>  T getFactory(SqlEntry entry){
        if(entry instanceof QuerySqlEntry){
            return (T)FFzlSqlQueryFactory.getInstance();
        } else if(entry instanceof UpdateSqlEntry){
            return (T)FFzlSqlUpdateFactory.getInstance();
        }
        return null;
    }
}
