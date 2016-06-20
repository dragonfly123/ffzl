package org.dragonfei.ffzl.params.sql.query.entry;

/**
 * Created by longfei on 16-6-19.
 */

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.sql.common.inter.SqlEntry;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringHandle;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 包装相关参数
 */
public class QuerySqlEntry implements SqlEntry {
    public List<Map<String,String>> inputs;
    public List<Map<String,String>> outputs;
    public String querySql;
    public String totalSql;
    public QuerySqlEntry(){

    }
    public String buildKey(ParamWrap pw){
        String fullservicename = pw.getFullservicename();
        int page = pw.getPage();
        int pageSize = pw.getPageSize();
        boolean ignorePage = pw.isIgnore_page();
        StringBuilder sb = new StringBuilder();
        sb.append("fullservicename:").append(fullservicename).
                append(",page:").append(page).append(",pageSize").
                append(pageSize).append(",ignorepage").append(ignorePage);
        if(!ObjectUtils.isEmpty(pw.getOrder())){
            sb.append(",order:").append(pw.getOrder()).append(",reverse").append(pw.isAsc());
        }
        for(Map<String,String> input:inputs){
            sb.append("#");
            if(pw.containParam(input.get("name"))){
                sb.append(input.get("name")).append(":").append("true#");
            }
        }
        sb.append(StringUtils.toCommaDelimitedString(pw.getParams(), ",", new StringHandle() {
            @Override
            public <T> String handle(T obj) {
                if(obj instanceof Map.Entry){
                    Map.Entry entry = (Map.Entry)obj;
                    return entry.getKey()+":"+entry.getValue();
                }
                return obj.toString();
            }
        }));
        return sb.toString();
    }
}
