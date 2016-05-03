package org.dragonfei.ffzl.params.sql;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.service.DataService;
import org.dragonfei.ffzl.utils.collections.ArrayUtils;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.string.StringHandle;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.object.SqlQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by longfei on 16-5-2.
 */
public class SqlSeed implements DataService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<String> params = Lists.newArrayList();
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
        List list = caculateParams(pw);
        int page = pw.getPage();
        int pageSize = pw.getPageSize();
        list.add((page-1)*pageSize);
        list.add(pageSize);
        pageSqlQuery.setType(list);
        logger.info("page param {}",list.toString());
        return pageSqlQuery.execute(list.toArray());
    }

    public Integer executeTotal(ParamWrap pw){
        List list = caculateParams(pw);
        totalSqlQuery.setType(list);
        logger.info("total param {}",list.toString());
        return totalSqlQuery.findObject(list.toArray());
    }

    private List caculateParams(ParamWrap pw){
        List list = Lists.newArrayList(params.size());
        for(int i = 0; i < params.size();i++){
            list.add(pw.getParam(params.get(i)));
        }
        logger.info("parameter is {}",list);
        return list;
    }

    public static Entry getEntry(){
        return new Entry();
    }

    /**
     * 包装相关参数
     */
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
