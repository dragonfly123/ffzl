package org.dragonfei.ffzl.controller.common;

import org.dragonfei.common.impl.ServiceDelegate;
import org.dragonfei.ffzl.params.ParamUtils;
import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.utils.collections.CollectionUtils;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-4-24.
 */
@Controller
@RequestMapping("common")
public class CommonDataAction {

    @Autowired ServiceDelegate serviceDelegate;
    @ResponseBody
    @RequestMapping("execute")
    public <T> T execute(@RequestParam Map<String,String> map){
        ParamWrap pw = ParamUtils.buildParams(map);
        return (T)serviceDelegate.execute(pw);
    }

    @ResponseBody
    @RequestMapping("layout")
    public Map<String,?> layout(@RequestParam Map<String,String> map){
        ParamWrap pw  =  ParamUtils.buildParams(map);
        ServiceResource layoutSr = ServiceResource.getServiceResource(pw.getFullservicename(),"layout", StringUtils.BLANK);
        Map<String,?> result = layoutSr.getResourceMap(pw.getServicename());
        Map<String,?> buttonsMap = (Map<String,?>)result.get("buttons");
        if(!ObjectUtils.isEmpty(buttonsMap)){
            List<Map<String,?>> listTop = (List<Map<String,?>>) buttonsMap.get("top");
            parseButton(listTop,pw);
            List<Map<String,?>> listBottom = (List<Map<String,?>>) buttonsMap.get("bottom");
            parseButton(listBottom,pw);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("component")
    public Map<String,?> component(@RequestParam Map<String,String> map){
        ParamWrap pw  =  ParamUtils.buildParams(map);
        ServiceResource layoutSr = ServiceResource.getServiceResource(pw.getFullservicename(),"component", StringUtils.BLANK);
        Map<String,?> result = layoutSr.getResourceMap(pw.getServicename());

        Map<String,Object> operationMap = (Map<String, Object>) result.get("operation");

        if(!ObjectUtils.isEmpty(operationMap)) {
            List<Map<String, ?>> listGlobal = (List<Map<String, ?>>) operationMap.get("global");
            parseButton(listGlobal,pw);

            List<Map<String, ?>> listRow = (List<Map<String, ?>>) operationMap.get("row");
            parseButton(listRow,pw);
        }

        List<Map<String,?>> listTop = (List<Map<String,?>>) result.get("top");
        parseButton(listTop,pw);


        return result;
    }

    @ResponseBody
    @RequestMapping("table")
    public Map<String,?> tablecolumns(@RequestParam Map<String,String> map){
        ParamWrap pw  =  ParamUtils.buildParams(map);
        ServiceResource sr = ServiceResource.getServiceResource(pw.getFullservicename(),"servicemap", StringUtils.BLANK);
        Map<String,?> serviceMap = sr.getResourceMap(pw.getServicename());
        String table = (String)serviceMap.get("table");
        String namespace = (String)serviceMap.get("namespace");
        ServiceResource tablesResource = ServiceResource.getServiceResource(namespace,"tablecolumns");
        return tablesResource.getResourceMap(table);
    }

    private void parseButton(List<Map<String,?>> list,ParamWrap pw){
        if(!ObjectUtils.isEmpty(list)){
            Iterator<Map<String,?>> iterator = list.iterator();
            while (iterator.hasNext()){
                Map<String,Object> map = (Map<String, Object>) iterator.next();
                String name = (String) map.get("name");
                if(ObjectUtils.isEmpty(name)){
                    iterator.remove();
                    continue;
                } else {
                    ServiceResource buttonsResource = ServiceResource.getServiceResource(name,"buttons",pw.getFullservicename());
                    Map<String,?> button =  buttonsResource.getResourceMap(name);
                    button.forEach((k,v)->{
                        if(!map.containsKey(k) || ObjectUtils.isEmpty(map.get(k))){
                            map.put(k,v);
                        }
                    });
                }
            }
        }
    }
}
