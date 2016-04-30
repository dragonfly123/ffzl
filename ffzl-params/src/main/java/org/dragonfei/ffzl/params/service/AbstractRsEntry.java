package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.resource.ResourceLoader;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.params.sql.SqlParam;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 *Created by longfei on 16-4-28.
 */
public abstract class AbstractRsEntry implements ServiceEntry<RecordSet> {
    @Override
    public RecordSet execute(ParamWrap pw) {
        RecordSet rs = new RecordSet();
        try {
            wrapPage(rs, pw);
            ServiceResource serviceResource = getServiceResource(pw,"serviceinterface");
            ServiceResource sqlResource = null;
            if(isNeedLoadSqlResouce(pw,serviceResource)){
                sqlResource = getServiceResource(pw,"sql");
            }
            wrapColumn(rs, pw, serviceResource);
            wrapData(rs, pw, serviceResource,sqlResource);
            wrapTotalRecordds(rs, pw, serviceResource,sqlResource);
            wrapTotal(rs, pw, serviceResource,sqlResource);
        } catch (Exception e){
            rs.setE(e);
            rs.setCode(RecordSet.FAIL_CODE);
        }
        rs.setCode(RecordSet.SUCCE_CODE);
        return rs;
    }

    protected boolean isNeedLoadSqlResouce(ParamWrap pw,ServiceResource serviceinterfaceResource){
        String servicename = pw.getServicename();
        if(serviceinterfaceResource != null){
            Map<String,?> map =serviceinterfaceResource.getResourceMap(servicename);
            if(map != null){
                return StringUtils.isNullOrEmpty(String.valueOf(map.get("sqlsource")));
            }
        }
        return false;
    }
    protected void wrapMessage(RecordSet rs,ParamWrap pw,ServiceResource serviceResource,
                               ServiceResource sqlresource,Exception e){
        rs.setMsg(e.getMessage());
    }
    protected void wrapPage(RecordSet rs,ParamWrap pw){
        if(pw.isIgnore_page()){
            rs.setPage(1);
            rs.setPageSize(10000);
        } else {
            rs.setPage(pw.getPage());
            rs.setPageSize(pw.getPageSize());
        }
    }
    protected void wrapColumn(RecordSet rs,ParamWrap pw,ServiceResource serviceResource){
        List<Map<String,String>> list = Lists.newArrayList();
        if(serviceResource != null) {
            Map<String,?> map =serviceResource.getResourceMap(pw.getServicename());
            if(Maps.isEmpty(map)){
                list = (List)map.get("output");
            }
        }
        rs.setColumns(Lists.nvl(list,Lists.newArrayList()));
    }

    protected void wrapData(RecordSet rs,ParamWrap pw,ServiceResource serviceResource,ServiceResource sqlresource){

    }

    protected void wrapTotalRecordds(RecordSet rs,ParamWrap pw,ServiceResource serviceResource,ServiceResource sqlresource){
        rs.setTotalRecords(rs.getData().size());
    }
    protected void wrapTotal(RecordSet rs,ParamWrap pw,ServiceResource serviceResource,ServiceResource sqlresource){
        SqlParam sqlParam = rs.getSqlParam();
        sqlParam.sql = "select count(*) from (" + sqlParam.sql + ")t";
    }

    private ServiceResource getServiceResource(ParamWrap pw,String resourceName){
        String servicename  = pw.getServicename();
        String[] paths = StringUtils.split(servicename,"_");
        String namespace = StringUtils.toCommaDelimitedString(paths,".");
        String reallyNamespace = namespace.substring(0,namespace.lastIndexOf("."));
        ResourceLoader resourceLoader = new ResourceLoader.Builder().type("json").name(resourceName).build();
        return resourceLoader.load(reallyNamespace);

    }


}
