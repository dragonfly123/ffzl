package org.dragonfei.ffzl.params.sql.condition;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-5-4.
 * sql拼装解析,根据serviceresource资源文件的input->cond获取
 */
public interface ConditionSql {
    /**
     * 获取附加的sql
     * @param column
     * @param input
     * @param pw
     * @return
     */
    String getAppendSql(String column, Map<String,String> input, ParamWrap pw);

    /**
     * 获取参数实体
     * @param column
     * @param input
     * @param pw
     * @return
     */
    List<ParameterEntry> getParamEntrys(String column, Map<String,String> input, ParamWrap pw);

    /**
     * 获取参数值
     * @param parameterName
     * @param pw
     * @return
     */
    default String getParamValues(String parameterName,ParamWrap pw){
        if(pw.containParam(parameterName)){
            return pw.getParam(parameterName);
        }
        return StringUtils.EMTY;
    }

    boolean shouldSkip(ParamWrap pw,Map<String,String> input);
}
