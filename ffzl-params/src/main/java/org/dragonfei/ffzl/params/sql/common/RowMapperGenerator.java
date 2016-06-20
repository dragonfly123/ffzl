package org.dragonfei.ffzl.params.sql.common;

import org.dragonfei.ffzl.utils.collections.Maps;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by longfei on 16-6-19.
 */
public class RowMapperGenerator {
    public static RowMapper<Map<String, ?>> commonRowMapper(Map<String,String> columMaps){
        return new RowMapper<Map<String, ?>>() {
            private ResultSetMetaData metaData;
            private Map<String,String> columnMap;
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                if(rowNum == 0){
                    metaData = rs.getMetaData();
                    columnMap = columMaps;
                }
                Map<String,Object> result = Maps.newHashMap();
                for(int i=0,j = metaData.getColumnCount();i < j;i++){
                    String columName = metaData.getColumnName(i+1);
                    result.put(String.valueOf(columnMap.get(columName.toUpperCase())),rs.getString(i+1));
                }
                return result;
            }
        };
    }
}
