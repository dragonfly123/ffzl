package org.dragonfei.ffzl.params.sql.update.entry;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.sql.common.inter.SqlEntry;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringHandle;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-6-20.
 */
public class UpdateSqlEntry implements SqlEntry {
    public String table;

    public List<String> bkList = Lists.newArrayList();
    public List<String> bkColumnList = Lists.newArrayList();

    public String pk = "id";
    public String pkColumn = "ID";

    public List<Map<String,String>> outputs = Lists.newArrayList();

    public List<String> columnNameList = Lists.newArrayList();
    public List<String> columnList = Lists.newArrayList();

    @Override
    public String buildKey(ParamWrap pw) {
        String fullservicename = pw.getFullservicename();
        StringBuilder sb = new StringBuilder();
        sb.append("fullservicename:").append(fullservicename);
        for(Map<String,String> input:outputs){
            sb.append("#");
            if(pw.containParam(input.get("name"))){
                sb.append(input.get("name")).append(":").append("true#");
            }
        }
        return sb.toString();
    }
}
