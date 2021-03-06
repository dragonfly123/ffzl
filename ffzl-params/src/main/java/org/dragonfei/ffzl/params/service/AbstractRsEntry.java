package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.utils.collections.Lists;
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
        ServiceResource serviceResource = ServiceResource.getServiceResource(pw.getFullservicename(),"serviceinterface",StringUtils.EMTY);
        ServiceResource sqlResource = null;
        if(isNeedLoadSqlResouce(pw,serviceResource)){
            sqlResource = ServiceResource.getServiceResource((String) serviceResource.getResourceMap(pw.getServicename()).get("sqlsource"),"sql",pw.getFullservicename());
        }

        try {
            wrapPage(rs, pw);
            wrapColumn(rs, pw, serviceResource);
            QueryDataService queryDataService = buildDataService(rs,pw,serviceResource,sqlResource);
            logger.debug("dataservice is {}",queryDataService);
            wrapData(rs, pw, serviceResource,sqlResource,queryDataService);
            wrapTotalRecords(rs, pw, serviceResource,sqlResource);
            wrapTotal(rs, pw, serviceResource,sqlResource,queryDataService);
            rs.setCode(RecordSet.SUCCE_CODE);
        } catch (Exception e){
            rs.setE(e);
            rs.setCode(RecordSet.FAIL_CODE);
            wrapMessage(rs,pw,serviceResource,sqlResource,e);
            logger.error("",e);
        }

        return rs;
    }

    /**
     *
     * @param rs
     * @param pw
     * @param serviceResource
     * @param sqlresource
     * @return
     * 数据服务
     */
    protected QueryDataService buildDataService(RecordSet rs, ParamWrap pw, ServiceResource serviceResource, ServiceResource sqlresource){

        return null;
    }

    /**
     * 查询数据接口
     * @param preSql
     * @return
     */
    public String buildPageSql(String preSql,Map<String,?> mapServiceInterface,ParamWrap pw){
        String column = pw.getOrder();
        boolean isAsc = pw.isAsc();
        if(!ObjectUtils.isEmpty(column)) {
            List<Map<String, String>> list = (List<Map<String, String>>) mapServiceInterface.get("output");
            for (Map<String, String> map : list) {
                if (StringUtils.equals(column, map.get("name"))) {
                    column = ObjectUtils.nvl(map.get("column"), column.toUpperCase());
                    break;
                }
            }
            return preSql + StringUtils.BLANK +"order by"+StringUtils.BLANK
                    +column+ StringUtils.BLANK+ (isAsc?"desc":"asc") + StringUtils.BLANK + "limit ?,?";
        }

        return preSql +  StringUtils.BLANK + "limit ?,?";
    }

    /**
     * 查询总记录数
     * @param preSql
     * @return
     */
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

    /**
     * 页码
     * @param rs
     * @param pw
     */
    protected void wrapPage(RecordSet rs,ParamWrap pw){
        if(pw.isIgnore_page()){
            rs.setPage(1);
            rs.setPageSize(10000);
        } else {
            rs.setPage(pw.getPage());
            rs.setPageSize(pw.getPageSize());
        }
    }

    /**
     * 列名称
     * @param rs
     * @param pw
     * @param serviceResource
     */
    protected void wrapColumn(RecordSet rs,ParamWrap pw,ServiceResource serviceResource){
        List<Map<String,String>> list = Lists.newArrayList();
        if(serviceResource != null) {
            Map<String,?> map =serviceResource.getResourceMap(pw.getServicename());
            logger.trace("serviceinterface :{}",map.toString());
            if(!ObjectUtils.isEmpty(map)){
                list = (List)map.get("output");
            }
        }
        rs.setColumns((List<Map<String,String>>) ObjectUtils.nvl(list, Lists.newArrayList()));
    }

    /**
     * 包装数据
     * @param rs
     * @param pw
     * @param serviceResource
     * @param sqlresource
     * @param queryDataService
     */
    protected void wrapData(RecordSet rs,ParamWrap pw,ServiceResource serviceResource,ServiceResource sqlresource,QueryDataService queryDataService){
        List<Map<String,?>> list  =Lists.newArrayList(pw.getPageSize());
        if(queryDataService != null){
            list.addAll(ObjectUtils.nvl(queryDataService.executeQuery(pw),Lists.newArrayList()));
        }
        logger.info("then result is {}",list);

        rs.setData(handleData(pw,serviceResource,list));
    }

    protected List<Map<String,?>> handleData(ParamWrap pw, ServiceResource serviceResource,List<Map<String,?>> list){
        return list;
    }
    protected void wrapTotalRecords(RecordSet rs,ParamWrap pw,ServiceResource serviceResource,ServiceResource sqlresource){
        rs.setTotalRecords(rs.getData().size());
    }
    protected void wrapTotal(RecordSet rs,ParamWrap pw,ServiceResource serviceResource,ServiceResource sqlresource,QueryDataService queryDataService){
        int total = 0;
        if(queryDataService != null){
            total = queryDataService.executeTotal(pw);
        }
        rs.setTotal(total);
    }


}
