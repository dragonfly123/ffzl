package org.dragonfei.ffzl.params.sql.condition;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.utils.date.LocalDateUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.Map;


/**
 * Created by longfei on 16-5-4.
 * 时间between
 */
public class BTCondditionSql extends BConditionSql {

    @Override
    public String getAppendSql(String column, Map<String, String> input, ParamWrap pw) {
        StringBuilder sb = new StringBuilder();
        if(pw.containParam(input.get("name")+"b")){
            sb.append(StringUtils.BLANK).append("AND").append(StringUtils.BLANK).
                    append(column).append(StringUtils.BLANK).append(">= ?").append(StringUtils.BLANK);
        }
        if(pw.containParam(input.get("name")+"e")){
            sb.append(StringUtils.BLANK).append("AND").append(StringUtils.BLANK).
                    append(column).append(StringUtils.BLANK).append("< ?").append(StringUtils.BLANK);
        }

        return sb.toString();
    }
    @Override
    public String getParamValues(String parameterName, ParamWrap pw) {
        if(parameterName.endsWith("e")){
            String value = pw.getParam(parameterName+"e");
            return LocalDateUtils.addDay(value,1).toString();
        } else {
            return super.getParamValues(parameterName,pw);
        }
    }
}
