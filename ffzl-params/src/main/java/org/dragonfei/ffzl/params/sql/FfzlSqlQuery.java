package org.dragonfei.ffzl.params.sql;

import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.SqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-5-2.
 */
public class FfzlSqlQuery extends SqlQuery<Map<String,String>> {

    private List<Map<String,String>> outputs = Lists.newArrayList();
    public FfzlSqlQuery(DataSource ds, String sql,List<Map<String,String>> outputs){
        super(ds,sql);
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
    @Override
    protected RowMapper<Map<String, String>> newRowMapper(Object[] parameters, Map<?, ?> context) {
        return new RowMapper<Map<String, String>>() {
            private ResultSetMetaData metaData;
            private Map<String,String> columnMap;
            @Override
            public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                if(rowNum == 1){
                    metaData = rs.getMetaData();
                    columnMap = buildColumMap();
                }
                Map<String,String> result = Maps.newHashMap();
                for(int i=0,j = metaData.getColumnCount();i < j;i++){
                    String columName = metaData.getColumnName(i+1);
                    result.put(String.valueOf(columnMap.get(columName.toUpperCase())),rs.getString(1));
                }
                return result;
            }
        };

    }

}
