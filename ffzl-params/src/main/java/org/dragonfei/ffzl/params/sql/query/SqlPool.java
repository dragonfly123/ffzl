package org.dragonfei.ffzl.params.sql.query;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.sql.condition.ParameterEntry;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;

import java.util.List;
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
    private Map<String,SqlSeed> pools = Maps.newHashMap();

    public SqlSeed getSqlSeed(ParamWrap pw, List<ParameterEntry> params, SqlSeed.Entry entry){
        String key = entry.buildKey(pw);
        if(entry.querySql == null && entry.totalSql == null){
            return null;
        }
        if(!pools.containsKey(key)){
            synchronized (this){
                if(!pools.containsKey(key)){
                    FFzlSqlQueryFactory.QueryEntry  queryEntry  = FFzlSqlQueryFactory.getQueryEntry(pw,entry);
                    SqlSeed sqlSeed = new SqlSeed(queryEntry);
                    if(!ObjectUtils.isEmpty(params)) {
                        sqlSeed.setParams(params);
                    }
                    pools.put(key,sqlSeed);
                    return sqlSeed;
                }
            }
        }
        return pools.get(key);
    }


    //public static SqlSeed
}
