package org.dragonfei.ffzl.params.sql;

import org.dragonfei.ffzl.utils.collections.Maps;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.SqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSetMetaData;
import java.util.Map;

/**
 * Created by longfei on 16-5-2.
 */
public class FfzlSqlQuery extends SqlQuery<Map<String,String>> {

    public FfzlSqlQuery(DataSource ds, String sql){
        super(ds,sql);
    }
    @Override
    protected RowMapper<Map<String, String>> newRowMapper(Object[] parameters, Map<?, ?> context) {

        return (rs, rowNum)->{
            ResultSetMetaData metaData = null;
            if(rowNum == 1) {
                metaData =rs.getMetaData();
            }
            Map<String,String> result = Maps.newHashMap();
            for(int i =0,j = metaData.getColumnCount();i < j;i++){
                String columName = metaData.getColumnName(i+1);
                result.put(String.valueOf(context.get(columName.toUpperCase())),rs.getString(i+1));
            }
            return result;
        };
    }
}
