package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.params.sql.common.SqlPool;
import org.dragonfei.ffzl.params.sql.common.inter.SqlOperation;
import org.dragonfei.ffzl.params.sql.query.operation.FfzlSqlQuery;
import org.dragonfei.ffzl.params.sql.query.operation.QuerySqlOperation;
import org.dragonfei.ffzl.params.sql.update.entry.UpdateSqlEntry;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-6-18.
 */

@Service("commonupdateentry")
public class CommonUpdateEntry implements ServiceEntry<RecordSet> {
    @Override
    public RecordSet execute(ParamWrap pw) {
        RecordSet recordSet = new RecordSet();
        try{
            ServiceResource serviceResource = ServiceResource.getServiceResource(pw.getFullservicename(),"servicemap", StringUtils.EMTY);
            Map<String,?> map =  serviceResource.getResourceMap(pw.getServicename());
            String namespace = (String)map.get("namespace");
            String table = (String)map.get("table");
            if(!(ObjectUtils.isEmpty(namespace) || ObjectUtils.isEmpty(table))){
                ServiceResource tableresource = ServiceResource.getServiceResource(namespace,"tablecolumns");
                Map<String,?> tablemap =  tableresource.getResourceMap(table);
                List<Map<String,String>> columnList = (List<Map<String,String>>) tablemap.get("columns");
                UpdateDataService dataService = builSqlOperation(columnList,pw,table);
                Map<String,String> result = Maps.newHashMap();
                if(dataService.needInsert(pw)){
                    result.put("execute","save");
                    result.put("pk",String.valueOf(dataService.executeInsert(pw)));
                } else {
                    result.put("execute","update");
                    result.put("pk",String.valueOf(dataService.executeModify(pw)));
                }
                recordSet.getData().add(result);
            }
        } catch (Exception e){
            recordSet.setCode(-1);
            recordSet.setMsg(e.getMessage());
            recordSet.setE(e);
        }

        return recordSet;
    }

    public UpdateDataService builSqlOperation(List<Map<String,String>> columnList,ParamWrap pw,String table){

        UpdateSqlEntry updateSqlEntry = new UpdateSqlEntry();
        SqlOperation sqlOperation =  SqlPool.getInstance().getSqlOperation(pw,updateSqlEntry,false);
        if(sqlOperation != null){
            return (UpdateDataService) sqlOperation;
        }

        updateSqlEntry.outputs = columnList;
        updateSqlEntry.table = table;
        for(Map<String,String> column:columnList){
            if(pw.containParam(column.get("name"))){
                updateSqlEntry.columnList.add(column.get("name"));
                updateSqlEntry.columnNameList.add(column.get("column"));
            } else {
                if(StringUtils.equals(column.get("required"),"1")){
                    throw new RuntimeException(String.format("%s is required",column.get("name")));
                }
            }

            if(StringUtils.equals(column.get("pk"),"1")){
                updateSqlEntry.pk = column.get("name");
                updateSqlEntry.pkColumn = column.get("column");
            }
            if(StringUtils.equals(column.get("bk"),"1")){
                updateSqlEntry.bkList.add(column.get("name"));
                updateSqlEntry.bkColumnList.add(column.get("column"));
            }
        }

        return (UpdateDataService) SqlPool.getInstance().getSqlOperation(pw,updateSqlEntry,true);
    }
}
