package org.dragonfei.ffzl.params.sql.query;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.spring.SpringContextUtils;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by longfei on 16-5-2.
 */
public abstract class FFzlSqlQueryFactory {
    private static DataSource dataSource = SpringContextUtils.getBean(DataSource.class);
    public static class QueryEntry{
        public FfzlSqlQuery ffzlSqlQuery;
        public FfzlSqlQueryTotal ffzlSqlQueryTotal;
    }

    private static Map<String,QueryEntry> map = Maps.newHashMap();

    public static QueryEntry getQueryEntry(ParamWrap pw, SqlSeed.Entry entry){
        String key = entry.buildKey(pw);
        if(map.containsKey(key)){
            return map.get(key);
        }
        QueryEntry queryEntry = new QueryEntry();
        queryEntry.ffzlSqlQuery = new FfzlSqlQuery(dataSource,entry.querySql,entry.outputs);
        queryEntry.ffzlSqlQueryTotal = new FfzlSqlQueryTotal(dataSource,entry.totalSql);
        map.put(key,queryEntry);
        return queryEntry;
    }
}
