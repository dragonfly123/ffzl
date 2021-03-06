package org.dragonfei.ffzl.params.sql.condition;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.sql.condition.ConditionSql;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-5-4.
 * 默认的处理“=”
 */
public class EqualConditionSql implements ConditionSql {
    @Override
    public String getAppendSql(String column, Map<String, String> input, ParamWrap pw) {
        if(!shouldSkip(pw,input)) {
            StringBuilder sb = new StringBuilder();
            sb.append(StringUtils.BLANK).append("AND").append(StringUtils.BLANK).
                    append(column.toUpperCase()).append("= ?").append(StringUtils.BLANK);
            return sb.toString();
        }
        return StringUtils.EMTY;
    }

    @Override
    public List<ParameterEntry> getParamEntrys(String column, Map<String, String> input, ParamWrap pw) {
        List<ParameterEntry> list = Lists.newArrayList(1);
        if(!shouldSkip(pw,input)) {
            list.add(new ParameterEntry(input.get("name"),this));
        }
        return list;
    }

    @Override
    public boolean shouldSkip(ParamWrap pw, Map<String, String> input) {
        if(pw.containParam(input.get("name"))){
            return false;
        }
        return true;
    }
}
