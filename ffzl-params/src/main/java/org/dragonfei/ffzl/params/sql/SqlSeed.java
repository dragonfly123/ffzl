package org.dragonfei.ffzl.params.sql;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.utils.collections.ArrayUtils;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.string.StringHandle;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.jdbc.object.SqlQuery;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by longfei on 16-5-2.
 */
public class SqlSeed {
    private List<String> params;
    private FfzlSqlQuery pageSqlQuery;
    private FfzlSqlQueryTotal totalSqlQuery;

    public SqlSeed(FFzlSqlQueryFactory.QueryEntry entry){
        this.pageSqlQuery = entry.ffzlSqlQuery;
        this.totalSqlQuery = entry.ffzlSqlQueryTotal;
    }
    public void setParams(List<String> params) {
        this.params = params;
    }

    public List<Map<String,String>> executeQuery(ParamWrap pw){
        Object[] objects = caculateParams(pw);
        int page = pw.getPage();
        int pageSize = pw.getPageSize();
        return pageSqlQuery.execute(ArrayUtils.copof(objects,(page-1)*pageSize,pageSize));
    }

    public Integer executeTotal(ParamWrap pw){
        Object[] objects = caculateParams(pw);
        return totalSqlQuery.findObject(objects);
    }

    private Object[] caculateParams(ParamWrap pw){
        Object[] objects = new Object[params.size()];
        for(int i = 0; i < params.size();i++){
            objects[i] = pw.getParam(params.get(i));
        }
        return objects;
    }

    public static Entry getEntry(){
        return new Entry();
    }

    public static class Entry {
        public List<Map<String,String>> inputs;
        public List<Map<String,String>> outputs;
        public String querySql;
        public String totalSql;
        private Entry(){

        }
        public String buildKey(ParamWrap pw){
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
    }
}
