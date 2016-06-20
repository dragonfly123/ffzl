package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.params.sql.common.SqlPool;
import org.dragonfei.ffzl.params.sql.query.operation.FfzlSqlQuery;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-6-18.
 */
public class CommonUpdateEntry implements ServiceEntry<RecordSet> {
    @Override
    public RecordSet execute(ParamWrap pw) {
        ServiceResource serviceResource = ServiceResource.getServiceResource(pw.getFullservicename(),"servicemap", StringUtils.EMTY);
        Map<String,?> map =  serviceResource.getResourceMap(pw.getServicename());
        String namespace = (String)map.get("namespace");
        String table = (String)map.get("table");
        if(!(ObjectUtils.isEmpty(namespace) || ObjectUtils.isEmpty(table))){
            ServiceResource tableresource = ServiceResource.getServiceResource(namespace,"tablecolumns");
            Map<String,?> tablemap =  tableresource.getResourceMap(table);
            List<Map<String,String>> columnList = (List<Map<String,String>>) tablemap.get("columns");
        }

        return null;
    }

    public Map<String,String> buildSql(List<Map<String,String>> columnList,ParamWrap pw,String table){
        List<String> columns = Lists.newArrayList();
        List<String> params = Lists.newArrayList();
        String pk = "id";
        String pkColumn = "ID";
        List<String> bklist = Lists.newArrayList();
        List<String> bkColumnlist = Lists.newArrayList();
        for(Map<String,String> column:columnList){
            if(pw.containParam(column.get("name"))){
                columns.add(column.get("column"));
                params.add(pw.getParam(column.get("name")));
            } else {
                if(StringUtils.equals(column.get("required"),"1")){
                    throw new RuntimeException(String.format("%s is required",column.get("name")));
                }
            }

            if(!ObjectUtils.isEmpty(column.get("pk"))){
                pk = column.get("name");
                pkColumn = column.get("column");
            }

            if(!ObjectUtils.isEmpty(column.get("bk"))){
                bklist.add(column.get("name"));
                bkColumnlist.add(column.get("column"));
            }
        }

        if(ObjectUtils.isEmpty(pw.getParam(pk))){
            //添加
            String sql = "SELECT"+StringUtils.BLANK+pkColumn+StringUtils.COMMA+StringUtils.toCommaDelimitedString(bkColumnlist)
                    +StringUtils.BLANK+"FROM"+StringUtils.BLANK+table+StringUtils.BLANK+"WHERE"+StringUtils.BLANK+StringUtils.toCommaDelimitedString(bkColumnlist,"AND"," =? ");
            FfzlSqlQuery sqlQuery  ;/*= SqlPool.getInstance().getBkSqlQuery(columnList,sql);*/
            List<String> paramValues = Lists.newArrayList();
            bklist.forEach(name->paramValues.add(pw.getParam(name)));
            //sqlQuery.setType(bklist);
            //List<Map<String,?>> list = sqlQuery.execute(bklist);
            /*if(!ObjectUtils.isEmpty(list)){
                throw new RuntimeException(String.format("%s已存在",StringUtils.toCommaDelimitedString(bkColumnlist)));
            } else {

            }*/
        } else {
            //修改
        }
    }
}
