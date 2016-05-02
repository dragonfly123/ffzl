package org.dragonfei.ffzl.params.sql;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.string.StringHandle;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-5-2.
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

    public SqlSeed getByPw(ParamWrap pw,List<Map<String,String>> resultInputs){
        return pools.get(buildKey(pw,resultInputs));
    }

    public SqlSeed getSqlSeed(ParamWrap pw,List<Map<String,String>> resultInputs,List<String> params,String querysql,String totalsql){
        String key = buildKey(pw,resultInputs);
        if(!pools.containsKey(key)){
            synchronized (this){
                if(!pools.containsKey(key)){
                    FFzlSqlQueryFactory.QueryEntry  queryEntry  = FFzlSqlQueryFactory.getQueryEntry(key,querysql,totalsql);
                    SqlSeed sqlSeed = new SqlSeed(queryEntry);
                    sqlSeed.setParams(params);
                    pools.put(key,sqlSeed);
                    return sqlSeed;
                }
            }

        }
        return pools.get(key);
    }
    private String buildKey(ParamWrap pw, List<Map<String,String>> inputs){
        String fullservicename = pw.getFullservicename();
        int page = pw.getPage();
        int pageSize = pw.getPageSize();
        boolean ignorePage = pw.isIgnore_page();
        StringBuilder sb = new StringBuilder();
        sb.append("fullservicename:").append(fullservicename).
                append(",page:").append(page).append(",pageSize").
                append(pageSize).append(",ignorepage").append(ignorePage);
        for(Map<String,String> input:inputs){
            sb.append("#");
            if(pw.containParam(input.get("name"))){
                sb.append(input.get("name")).append(":").append("true#");
            }
        }
        sb.append(StringUtils.toCommaDelimitedString(pw.getParams(), ",", new StringHandle() {
            @Override
            public <T> String handle(T obj) {
                if(obj instanceof Map.Entry){
                    Map.Entry entry = (Map.Entry)obj;
                    return entry.getKey()+":"+entry.getValue();
                }
                return obj.toString();
            }
        }));
        return sb.toString();
    }
    //public static SqlSeed
}
