package org.dragonfei.ffzl.params.sql;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.utils.collections.ArrayUtils;
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

    public List<Map<String,String>> executeQuery(ParamWrap pw,Map<String,String> columns){
        Object[] objects = caculateParams(pw);
        int page = pw.getPage();
        int pageSize = pw.getPageSize();
        return pageSqlQuery.execute(ArrayUtils.copof(objects,(page-1)*pageSize,pageSize),columns);
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
}
