package org.dragonfei.ffzl.params.sql.condition;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.sql.condition.ConditionSql;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-5-4.
 * like处理
 */
public class LkConditionSql implements ConditionSql {
    @Override
    public String getAppendSql(String column, Map<String, String> input, ParamWrap pw) {
        if (pw.containParam(input.get("name"))) {
            StringBuilder sb = new StringBuilder();
            sb.append(StringUtils.BLANK).append("AND").append(StringUtils.BLANK).
                    append(column).append(StringUtils.BLANK).append("LIKE %?%").append(StringUtils.BLANK);
            return sb.toString();
        }
        return StringUtils.EMTY;
    }


    @Override
    public List<ParameterEntry> getParamEntrys(String column, Map<String, String> input, ParamWrap pw) {
        List<ParameterEntry> list = Lists.newArrayList();
        if (pw.containParam(input.get("name"))) {
            list.add(new ParameterEntry(input.get("name"),this));
        }
        return list;
    }
}
