package org.dragonfei.ffzl.params.sql.condition;

import org.dragonfei.ffzl.params.ParamWrap;


/**
 * Created by longfei on 16-5-4.
 */
public class BTCondditionSql extends BConditionSql {
    @Override
    public String getParamValues(String parameterName, ParamWrap pw) {
        if(parameterName.endsWith("e")){
            return "";
        } else {
            return super.getParamValues(parameterName,pw);
        }
    }
}
