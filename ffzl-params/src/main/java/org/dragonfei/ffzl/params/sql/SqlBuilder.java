package org.dragonfei.ffzl.params.sql;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-4-27.
 */
public class SqlBuilder {
    private List<Map<String,String>> inputs = Lists.newArrayList();
    private List<Map<String,String>> outputs = Lists.newArrayList();
    private String orderBy = StringUtils.EMTY;
    private Map<String,?> sqlMap = Maps.newHashMap();
    public static SqlBuilder instance(){
        return new SqlBuilder();
    }

    public SqlBuilder buildInputs(List<Map<String,String>> inputs){
        this.inputs.addAll(inputs);
        return this;
    }

    public SqlBuilder buildOutputs(List<Map<String,String>> outputs){
        this.outputs.addAll(outputs);
        return this;
    }

    public SqlBuilder buildOrderBy(String orderby){
        this.orderBy = orderby;
        return this;
    }

    public SqlBuilder buildSqlMap(Map sqlMap){
        this.sqlMap.putAll(sqlMap);
        return this;
    }

    public void build(ParamWrap pw){

    }
}
