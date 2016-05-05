package org.dragonfei.ffzl.params.sql.condition;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-5-4.
 */
public interface ConditionSql {
    String getAppendSql(String column, Map<String,String> input, ParamWrap pw);
    List<ParameterEntry> getParamEntrys(String column, Map<String,String> input, ParamWrap pw);
    default String getParamValues(String parameterName,ParamWrap pw){
        if(pw.containParam(parameterName)){
            return pw.getParam(parameterName);
        }
        return StringUtils.EMTY;
    }
}
