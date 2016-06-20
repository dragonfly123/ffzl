package org.dragonfei.ffzl.params.sql.common;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.sql.common.inter.OperationEntry;
import org.dragonfei.ffzl.params.sql.common.inter.SqlEntry;
import org.dragonfei.ffzl.params.sql.common.inter.SqlOperation;
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
    private Map<String,SqlOperation> queryPools = Maps.newHashMap();

    public SqlOperation getSqlOperation(ParamWrap pw,SqlEntry entry,boolean create){
        String key = entry.buildKey(pw);
        if(create) {
            if (!queryPools.containsKey(key)) {
                synchronized (this) {
                    if (!queryPools.containsKey(key)) {
                        OperationEntry queryEntry = SqlFactoryUtils.getFactory(entry).getSqlEntry(entry);
                        SqlOperation sqlOperation =  SqlFactoryUtils.getFactory(entry).getSqlOperation(queryEntry,entry);
                        queryPools.put(key,sqlOperation);
                        return sqlOperation;
                    }
                }
            }
        }
        return queryPools.get(key);
    }

}
