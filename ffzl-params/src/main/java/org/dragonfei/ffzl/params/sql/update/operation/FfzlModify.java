package org.dragonfei.ffzl.params.sql.update.operation;

import org.dragonfei.ffzl.params.sql.common.TypeGenerator;
import org.dragonfei.ffzl.utils.string.StringHandle;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by longfei on 16-6-19.
 */
public class FfzlModify extends SqlUpdate {

    private boolean haveSetType = false;

    public FfzlModify(DataSource ds, String table,String pkColumn, List<String> columns){
        setDataSource(ds);
        List<String> noPkList = columns.stream().filter(column->!column.equals(pkColumn)).collect(Collectors.toList());
        String sql = "UPDATE"+ StringUtils.BLANK+ table+StringUtils.BLANK+"SET"+StringUtils.BLANK+
                StringUtils.toCommaDelimitedString(noPkList, new StringHandle() {
                    @Override
                    public <T> String handle(T obj) {
                        String str = (String)obj;
                        return "`"+str.trim()+"` = ?";

                    }
                })+StringUtils.BLANK+"WHERE `"+pkColumn+"` = ?";

        setSql(sql);
        setMaxRowsAffected(1);
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
