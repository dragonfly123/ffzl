package org.dragonfei.ffzl.params.sql.common;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.sql.common.inter.OperationEntry;
import org.dragonfei.ffzl.params.sql.common.inter.SqlEntry;
import org.dragonfei.ffzl.params.sql.query.operation.QuerySqlOperation;
import org.dragonfei.ffzl.utils.collections.Maps;

import java.util.Map;

/**
 * Created by longfei on 16-5-2.
 * 编译后的sql语句保存池
 */
public class SqlPool {
    private static SqlPool sqlPool;
    private SqlPool(){}
    public static SqlPool getInstance(){
        if(sqlPool == null){
            synchronized (SqlPool.class){
                if(sqlPool == null){
                    sqlPool = new SqlPool();
                }
            }
        }
        return sqlPool;
    }
    private Map<String,QuerySqlOperation> queryPools = Maps.newHashMap();

    public QuerySqlOperation getQuerySqlSeed(ParamWrap pw, SqlEntry entry){
        String key = entry.buildKey(pw);
        if(!queryPools.containsKey(key)){
            synchronized (this){
                if(!queryPools.containsKey(key)){
                    OperationEntry queryEntry = SqlFactoryUtils.getFactory(entry).getSqlEntry(entry);
                    QuerySqlOperation sqlSeed = (QuerySqlOperation) SqlFactoryUtils.getFactory(entry).getSqlSeed(queryEntry);
                    queryPools.put(key,sqlSeed);
                    return sqlSeed;
                }
            }
        }
        return queryPools.get(key);
    }

}
