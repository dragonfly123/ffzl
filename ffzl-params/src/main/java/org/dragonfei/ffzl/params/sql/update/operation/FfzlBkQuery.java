package org.dragonfei.ffzl.params.sql.update.operation;

import org.dragonfei.ffzl.params.sql.common.RowMapperGenerator;
import org.dragonfei.ffzl.params.sql.common.TypeGenerator;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.string.StringHandle;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.SqlQuery;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-6-19.
 */
public class FfzlBkQuery extends SqlQuery<Map<String,?>> {
    private boolean haveSetType = false;
    private List<Map<String,String>> outputs = Lists.newArrayList();
    public FfzlBkQuery(DataSource ds, String table, String pkColumn, List<String> bkColumnList,List<Map<String,String>> outputs){

        List<String> columns = Lists.newArrayList();
        columns.add(pkColumn);
        columns.addAll(bkColumnList);
        String sql = "SELECT"+ StringUtils.BLANK+StringUtils.toCommaDelimitedString(columns,"`","`")+StringUtils.BLANK+
                "FROM"+StringUtils.BLANK+table+StringUtils.BLANK+"WHERE"+
                StringUtils.toCommaDelimitedString(bkColumnList, " AND ", new StringHandle() {
                    @Override
                    public <T> String handle(T obj) {
                        String str = (String)obj;
                        return "`"+str.trim()+"` = ? ";
                    }
                });
        setDataSource(ds);
        setSql(sql);
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
                    super.setTypes(TypeGenerator.commonTypes(parameters));
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
