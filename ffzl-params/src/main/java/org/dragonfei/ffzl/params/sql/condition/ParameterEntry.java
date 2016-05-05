package org.dragonfei.ffzl.params.sql.condition;

/**
 * Created by longfei on 16-5-5.
 */
public class ParameterEntry {
    public String prameterName;
    public ConditionSql conditionSql;

    public ParameterEntry(String prameterName, ConditionSql conditionSql) {
        this.prameterName = prameterName;
        this.conditionSql = conditionSql;
    }
}
