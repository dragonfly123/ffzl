package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.params.sql.SqlSeed;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-4-30.
 */
public class CommonRecordSetEntry extends AbstractRsEntry {
    @Override
    void wrapData(RecordSet rs, ParamWrap pw, ServiceResource serviceResource, ServiceResource sqlresource, SqlSeed sqlSeed) {
        List<Map<String,String>> list  =Lists.newArrayList(pw.getPageSize());
        if(sqlSeed != null){
            list.addAll(Lists.nvl(sqlSeed.executeQuery(pw,getColumnMap(pw,serviceResource)),Lists.newArrayList()));
        }
        rs.setData(list);
    }

    private Map<String,String> getColumnMap(ParamWrap pw, ServiceResource serviceResource){
        Map<String,?> mapServiceInterface = Maps.nvl(serviceResource.getResourceMap(pw.getServicename()),Maps.newHashMap());
        List<Map<String,String>> list  =Lists.nvl((List<Map<String,String>>)mapServiceInterface.get("output"),Lists.newArrayList());
        Map<String,String> result = Maps.newHashMap();
        list.forEach(item ->{
            String name = item.get("name");
            String column = item.get("column");
            result.put(StringUtils.nvl(column,name).toUpperCase(),name);
        });
        return result;
    }

    @Override
    void wrapTotal(RecordSet rs, ParamWrap pw, ServiceResource serviceResource, ServiceResource sqlresource, SqlSeed sqlSeed) {
        int total = 0;
        if(sqlSeed != null){
            total = sqlSeed.executeTotal(pw);
        }
        rs.setTotal(total);
    }
}
