package org.dragonfei.ffzl.params.sql.query.operation;

import org.dragonfei.ffzl.params.sql.common.RowMapperGenerator;
import org.dragonfei.ffzl.params.sql.common.TypeGenerator;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.SqlQuery;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-5-2.
 * 查询以前
 */
public class FfzlSqlQuery extends SqlQuery<Map<String,?>> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<Map<String,String>> outputs = Lists.newArrayList();
    private boolean haveSetType = false;
    public FfzlSqlQuery(DataSource ds, String sql,List<Map<String,String>> outputs){
        super(ds,sql);
        logger.info("execute query:{}",sql);
        this.outputs = outputs;
    }
    private Map<String,String> buildColumMap(){
        Map<String,String> result = Maps.newHashMap();
        for(Map<String,String> map : outputs){
            if(map.containsKey("column")){
                result.put(map.get("column").toUpperCase(),map.get("name"));
            } else {
                result.put(map.get("name").toUpperCase(),map.get("name"));
            }
        }
        return result;
    }

    public void setType(List parameters){
        if(!haveSetType) {
            synchronized (this) {
                if (!haveSetType) {
                    super.setTypes(TypeGenerator.pageTypes(parameters));
                    haveSetType =  true;
                }
            }
        }
    }
    @Override
    protected RowMapper<Map<String,?>> newRowMapper(Object[] parameters, Map<?, ?> context) {
        return RowMapperGenerator.commonRowMapper(buildColumMap());

    }

}
