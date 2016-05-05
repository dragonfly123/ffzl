package org.dragonfei.ffzl.params.sql.condition;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-5-4.
 * 处理普通between
 */
public class BConditionSql implements ConditionSql {
    @Override
    public String getAppendSql(String column, Map<String, String> input, ParamWrap pw) {
        StringBuilder sb = new StringBuilder();
        if(pw.containParam(input.get("name")+"b")){
            sb.append(StringUtils.BLANK).append("AND").append(StringUtils.BLANK).
                    append(column).append(StringUtils.BLANK).append(">= ?").append(StringUtils.BLANK);
        }
        if(pw.containParam(input.get("name")+"e")){
            sb.append(StringUtils.BLANK).append("AND").append(StringUtils.BLANK).
                    append(column).append(StringUtils.BLANK).append("<= ?").append(StringUtils.BLANK);
        }

        return sb.toString();
    }

    @Override
    public List<ParameterEntry> getParamEntrys(String column, Map<String, String> input, ParamWrap pw) {
        List<ParameterEntry> list  = Lists.newArrayList();
        if(pw.containParam(input.get("name")+"b")){
            list.add(new ParameterEntry(column+"b",this));
        }
        if(pw.containParam(input.get("name")+"e")){
            list.add(new ParameterEntry(column+"e",this));
        }
        return list;
    }
}
