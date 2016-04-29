package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.sql.SqlParam;
import org.dragonfei.ffzl.params.support.ResourceLoader;
import org.dragonfei.ffzl.params.support.ServiceContext;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 *Created by longfei on 16-4-28.
 */
public abstract class AbstractRsEntry implements ServiceEntry<RecordSet> {
    @Override
    public RecordSet execute(ParamWrap pw) {
        ServiceContext serviceContext = getServiceContext(pw);
        RecordSet rs = new RecordSet();
        try {
            wrapPage(rs, pw, serviceContext);
            wrapColumn(rs, pw, serviceContext);
            wrapData(rs, pw, serviceContext);
            wrapTotalRecordds(rs, pw, serviceContext);
            wrapTotal(rs, pw, serviceContext);
        } catch (Exception e){
            rs.setE(e);
            rs.setCode(RecordSet.FAIL_CODE);
        }
        rs.setCode(RecordSet.SUCCE_CODE);
        return rs;
    }

    protected void wrapMessage(RecordSet rs,ParamWrap pw,ServiceContext serviceContext,Exception e){
        rs.setMsg(e.getMessage());
    }
    protected void wrapPage(RecordSet rs,ParamWrap pw,ServiceContext serviceContext){
        if(pw.isIgnore_page()){
            rs.setPage(1);
            rs.setPageSize(10000);
        } else {
            rs.setPage(pw.getPage());
            rs.setPageSize(pw.getPageSize());
        }
    }
    protected void wrapColumn(RecordSet rs,ParamWrap pw,ServiceContext serviceContext){
        rs.setColumns(getOutputs(serviceContext,pw.getServicename()));
    }

    protected void wrapData(RecordSet rs,ParamWrap pw,ServiceContext serviceContext){

    }

    protected void wrapTotalRecordds(RecordSet rs,ParamWrap pw,ServiceContext serviceContext){
        rs.setTotalRecords(rs.getData().size());
    }
    protected void wrapTotal(RecordSet rs,ParamWrap pw,ServiceContext serviceContext){
        SqlParam sqlParam = rs.getSqlParam();
        sqlParam.sql = "select count(*) from (" + sqlParam.sql + ")t";
    }
    private ServiceContext getServiceContext(ParamWrap pw){
        String servicename  = pw.getServicename();
        String[] paths = StringUtils.split(servicename,"_");
        String namespace = StringUtils.toCommaDelimitedString(paths,".");
        String reallyNamespace = namespace.substring(0,namespace.lastIndexOf("."));
        return ResourceLoader.load(reallyNamespace);
    }

    private List<Map<String,String>> getOutputs(ServiceContext serviceContext, String servicename){
        if(serviceContext != null){
            return Lists.nvl((List<Map<String,String>>) serviceContext.getServiceInterface(servicename).get("input"),Lists.newArrayList());
        } else {
            return Lists.newArrayList();
        }
    }
}
