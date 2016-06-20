package org.dragonfei.ffzl.params.sql.update.operation;

import org.dragonfei.ffzl.params.sql.common.TypeGenerator;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by longfei on 16-6-19.
 */
public class FfzlInsert extends SqlUpdate {

    private boolean haveSetType = false;
    public FfzlInsert(DataSource ds, String table, List<String> columns){
        setDataSource(ds);
        String sql = "INSERT INTO"+ StringUtils.BLANK+ table+
                "("+StringUtils.toCommaDelimitedString(columns,"`","`")+
                ")VALUES("+StringUtils.repeatString("?",columns.size())+")";
        setSql(sql);
        setMaxRowsAffected(1);
    }

    public Long getLastInsert(){
        String sql = "SELECT LAST_INSERT_ID()";
        return getJdbcTemplate().queryForObject(sql,Long.TYPE);
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
}
