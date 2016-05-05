package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.params.sql.condition.ConditionSql;
import org.dragonfei.ffzl.params.sql.condition.ConditionSqlFactory;
import org.dragonfei.ffzl.params.sql.condition.ParameterEntry;
import org.dragonfei.ffzl.params.sql.query.SqlPool;
import org.dragonfei.ffzl.params.sql.query.SqlSeed;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringHandle;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by longfei on 16-4-30.
 */
public class CommonRsEntry extends AbstractRsEntry {

    /**
     * 数据库的DataService 对象构建
     * @param rs
     * @param pw
     * @param serviceResource
     * @param sqlresource
     * @return
     */
    @Override
    public DataService buildDataService(RecordSet rs, ParamWrap pw, ServiceResource serviceResource, ServiceResource sqlresource){
        String serviceName = pw.getServicename();
        Map<String,?> mapServiceInterface = ObjectUtils.nvl(serviceResource.getResourceMap(serviceName),Maps.newHashMap());
        List<Map<String,String>> inputs = Lists.newArrayList();
        if(mapServiceInterface.containsKey("input")){
            inputs.addAll(ObjectUtils.nvl((List)mapServiceInterface.get("input"),Lists.newArrayList()));
        }
        List<Map<String,String>> resultInputs =  getRealyInputs(pw,inputs);
        SqlSeed.Entry entry = SqlSeed.getEntry();
        entry.inputs = resultInputs;
        if(SqlPool.getInstance().getSqlSeed(pw,null,entry) != null){
            return SqlPool.getInstance().getSqlSeed(pw,null,entry);
        }
        entry.outputs = getColumns(mapServiceInterface);
        Map<String,?> mapSqlResource  =  ObjectUtils.nvl(sqlresource.getResourceMap(serviceName),Maps.newHashMap());
        String preSql = ObjectUtils.nvl(String.valueOf(mapSqlResource.get("sql")),StringUtils.EMTY);
        preSql =  preSql.replace("@column",StringUtils.BLANK+buildColumn(mapServiceInterface)+StringUtils.BLANK);
        Map<String,Map<String,Object>> sqlParam = builSqlParam(ObjectUtils.nvl((Map)mapSqlResource.get("param"),Maps.newHashMap()),resultInputs,pw);
        List<ParameterEntry> paramsNames = Lists.newArrayList();
        Pattern p = Pattern.compile("@[a-zA-Z]+");
        Matcher m = p.matcher(preSql);
        while(m.find()){
            preSql  = preSql.replaceFirst(m.group(),String.valueOf(sqlParam.get(m.group()).get("sqlappend")));
            paramsNames.addAll(ObjectUtils.nvl((List<ParameterEntry>) sqlParam.get(m.group()).get("sqlparams"),Lists.newArrayList(0)));

        }
        final String pageSql = buildPageSql(preSql);
        final String totalSql = buildTotalSql(preSql);

        entry.querySql = pageSql;
        entry.totalSql = totalSql;

        return SqlPool.getInstance().getSqlSeed(pw,paramsNames,entry);

    }

    private List<Map<String,String>> getRealyInputs(ParamWrap pw,List<Map<String,String>> inputs){
        return ObjectUtils.nvl(inputs.stream().
                filter(input->pw.containParam(input.get("name"))).
                collect(Collectors.toList()),Lists.newArrayList());
    }

    private  String  buildColumn(Map<String,?> mapServiceInterface){
        List<Map<String,String>> list  = getColumns(mapServiceInterface);
        return StringUtils.toCommaDelimitedString(list, StringUtils.COMMA, new StringHandle() {
            @Override
            public <T> String handle(T obj) {
                if(obj instanceof Map){
                    return ObjectUtils.nvl(((Map<String,String>)obj).get("column"),((Map<String,String>)obj).get("name").toUpperCase());
                }
                return StringUtils.EMTY;
            }
        });
    }

    private List<Map<String,String>> getColumns(Map<String,?> mapServiceInterface){
        return ObjectUtils.nvl((List<Map<String,String>>)mapServiceInterface.get("output"),Lists.newArrayList());
    }

    /**
     *
     * @param sqlParam
     * @param inputs
     * @return
     * {
     *     @A:{
     *         "sqlappend":"",
     *         "sqlparams":""
     *     }
     * }
     */
    private Map<String,Map<String,Object>> builSqlParam(Map<String,String> sqlParam,List<Map<String,String>>  inputs,ParamWrap pw){
        Map<String,Map<String,Object>> result= Maps.newHashMap();
        for(Map.Entry<String,String> entry:sqlParam.entrySet()){
            String value = entry.getValue();
            String[] values = StringUtils.split(ObjectUtils.nvl(value,StringUtils.EMTY),StringUtils.COMMA);
            StringBuilder appenSql = new StringBuilder();
            List<ParameterEntry> preParamName = Lists.newArrayList();
            for(String val :  values){
                for(Map<String,String> input:inputs){
                    if(input.containsKey("column")){
                        if(StringUtils.equalsIngoreCase(val,input.get("column"))){
                            ConditionSql conditionSql  = ConditionSqlFactory.getByType(input.get("cond"));
                            appenSql.append(conditionSql.getAppendSql(val,input,pw));
                            preParamName.addAll(conditionSql.getParamEntrys(val,input,pw));
                        }
                    } else {
                        if(StringUtils.equalsIngoreCase(val,input.get("name"))){
                            ConditionSql conditionSql  = ConditionSqlFactory.getByType(input.get("cond"));
                            appenSql.append(conditionSql.getAppendSql(val,input,pw));
                            preParamName.addAll(conditionSql.getParamEntrys(val,input,pw));
                        }
                    }
                }
            }
            Map<String,Object> innerMap = Maps.newHashMap();
            innerMap.put("sqlappend",appenSql.toString());
            innerMap.put("sqlparams",preParamName);
            result.put(entry.getKey(),innerMap);
        }
        return result;
    }
}
