package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.resource.ResourceLoader;
import org.dragonfei.ffzl.params.resource.ResourceLoaderFactory;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 *Created by longfei on 16-4-28.
 */
public abstract class AbstractRsEntry implements ServiceEntry<RecordSet> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public RecordSet execute(ParamWrap pw) {
        logger.debug("parameter is {}",pw.toString());
        RecordSet rs = new RecordSet();
        ServiceResource serviceResource = getServiceResource(pw,"serviceinterface");
        ServiceResource sqlResource = null;
        if(isNeedLoadSqlResouce(pw,serviceResource)){
            sqlResource = getServiceResource(pw,"sql");
        }

        try {
            wrapPage(rs, pw);
            wrapColumn(rs, pw, serviceResource);
            DataService dataService = buildDataService(rs,pw,serviceResource,sqlResource);
            logger.debug("dataservice is {}",dataService);
            wrapData(rs, pw, serviceResource,sqlResource,dataService);
            wrapTotalRecords(rs, pw, serviceResource,sqlResource);
            wrapTotal(rs, pw, serviceResource,sqlResource,dataService);
            rs.setCode(RecordSet.SUCCE_CODE);
        } catch (Exception e){
            rs.setE(e);
            rs.setCode(RecordSet.FAIL_CODE);
            wrapMessage(rs,pw,serviceResource,sqlResource,e);
            logger.error(e.getMessage());
        }

        return rs;
    }

    protected DataService buildDataService(RecordSet rs, ParamWrap pw, ServiceResource serviceResource, ServiceResource sqlresource){

        return null;
    }

    public String buildPageSql(String preSql){
        return preSql + StringUtils.BLANK + "limit ?,?";
    }

    public String  buildTotalSql(String preSql){
        return "Select count(*) from ("+preSql+")t";
    }


    protected boolean isNeedLoadSqlResouce(ParamWrap pw,ServiceResource serviceinterfaceResource){
        String servicename = pw.getServicename();
        if(serviceinterfaceResource != null){
            Map<String,?> map =serviceinterfaceResource.getResourceMap(servicename);
            if(map != null){
                return !ObjectUtils.isEmpty(map.get("sqlsource"));
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
            logger.trace("serviceinterface :{}",map.toString());
            if(!ObjectUtils.isEmpty(map)){
                list = (List)map.get("output");
            }
        }
        rs.setColumns(Lists.nvl(list,Lists.newArrayList()));
    }

    protected void wrapData(RecordSet rs,ParamWrap pw,ServiceResource serviceResource,ServiceResource sqlresource,DataService dataService){
        List<Map<String,String>> list  =Lists.newArrayList(pw.getPageSize());
        if(dataService != null){
            list.addAll(Lists.nvl(dataService.executeQuery(pw),Lists.newArrayList()));
        }
        logger.info("then result is {}",list);
        rs.setData(list);
    }

    protected void wrapTotalRecords(RecordSet rs,ParamWrap pw,ServiceResource serviceResource,ServiceResource sqlresource){
        rs.setTotalRecords(rs.getData().size());
    }
    protected void wrapTotal(RecordSet rs,ParamWrap pw,ServiceResource serviceResource,ServiceResource sqlresource,DataService dataService){
        int total = 0;
        if(dataService != null){
            total = dataService.executeTotal(pw);
        }
        rs.setTotal(total);
    }

    private ServiceResource getServiceResource(ParamWrap pw,String resourceName){
        String servicename  = pw.getFullservicename();
        String[] paths = StringUtils.split(servicename,"_");
        String namespace = StringUtils.toCommaDelimitedString(paths,".");
        String reallyNamespace = namespace.substring(0,namespace.lastIndexOf("."));
        ResourceLoader resourceLoader = ResourceLoaderFactory.getResourceLoader("json",resourceName);
        return resourceLoader.load(reallyNamespace);

    }

}
