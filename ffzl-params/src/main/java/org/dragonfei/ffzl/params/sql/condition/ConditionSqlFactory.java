package org.dragonfei.ffzl.params.sql.condition;

import org.dragonfei.ffzl.utils.collections.Maps;

import java.util.Map;
import java.util.Optional;

/**
 * Created by longfei on 16-5-4.
 * 注册cond类型
 */
public class ConditionSqlFactory {
    private static Map<String,ConditionSql> conditionMap = Maps.newHashMap();
    static {
        registerConditionSqlHandle("=",new EqualConditionSql());
        registerConditionSqlHandle("equal",new EqualConditionSql());
        registerConditionSqlHandle("b",new BConditionSql());
        registerConditionSqlHandle("bt",new BTCondditionSql());
        registerConditionSqlHandle("lrLike",new LkConditionSql());
    }



    private static void registerConditionSqlHandle(String type,ConditionSql conditionSql){
        conditionMap.put(type,conditionSql);
    }

    public static ConditionSql getByType(String type){
        Optional<ConditionSql> optional = Optional.ofNullable(conditionMap.get(type));
        return optional.orElseGet(()->new EqualConditionSql());
    }
}
