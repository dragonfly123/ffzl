package org.dragonfei.ffzl.params.sql.query;

import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.SqlQuery;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-5-2.
 * 查询以前
 */
public class FfzlSqlQuery extends SqlQuery<Map<String,String>> {

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
                    int[] types = new int[parameters.size()];
                    for (int i = 0; i < parameters.size() - 2; i++) {
                        types[i] = JDBCType.VARCHAR.getVendorTypeNumber();
                    }
                    types[parameters.size() - 2] = JDBCType.INTEGER.getVendorTypeNumber();
                    types[parameters.size() - 1] = JDBCType.INTEGER.getVendorTypeNumber();
                    super.setTypes(types);
                    haveSetType =  true;
                }
            }
        }
    }
    @Override
    protected RowMapper<Map<String, String>> newRowMapper(Object[] parameters, Map<?, ?> context) {
        return new RowMapper<Map<String, String>>() {
            private ResultSetMetaData metaData;
            private Map<String,String> columnMap;
            @Override
            public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                if(rowNum == 0){
                    metaData = rs.getMetaData();
                    columnMap = buildColumMap();
                }
                Map<String,String> result = Maps.newHashMap();
                for(int i=0,j = metaData.getColumnCount();i < j;i++){
                    String columName = metaData.getColumnName(i+1);
                    result.put(String.valueOf(columnMap.get(columName.toUpperCase())),rs.getString(i+1));
                }
                return result;
            }
        };

    }

}
