package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16/5/9.
 * data转成树形结构
 */

@Service("treeRsEntry")
public class TreeRsEntry extends CommonRsEntry {

    @Override
    protected List<Map<String, ?>> handleData(ParamWrap pw,ServiceResource serviceResource, List<Map<String, ?>> list) {

        ServiceResource sr = ServiceResource.getServiceResource(pw.getFullservicename(), "treeservice", StringUtils.EMTY);
        Map<String, ?> map = sr.getResourceMap(pw.getServicename());
        if (ObjectUtils.isEmpty(map)) {
            throw new RuntimeException(String.format("the treeservice contain %s", pw.getFullservicename()));
        }
        String id = (String) map.get("idKey");
        String pid = (String) map.get("pIdKey");
        Map<String, Map<String, ?>> temp = Maps.newHashMap();
        list.forEach(item -> temp.put((String) item.get(id), item));
        Map<String, Map<String, ?>> result = Maps.newHashMap();
        list.forEach(item -> {
            if (!ObjectUtils.isEmpty(item.get(pid))) {
                Map<String, List<Object>> pmap = (Map<String, List<Object>>) temp.get(item.get(pid));
                if (!ObjectUtils.isEmpty(pmap)) {
                    if (!pmap.containsKey("children")) {
                        pmap.put("children", Lists.newArrayList());
                    }
                    pmap.get("children").add(item);
                } else {
                    result.put((String) item.get(id), item);
                }
            } else {
                result.put((String) item.get(id), item);
            }
        });
        List<Map<String, ?>> resultList = Lists.newArrayList();
        resultList.addAll(result.values());
        resultList.sort(new Comparator<Map<String, ?>>() {
            @Override
            public int compare(Map<String, ?> o1, Map<String, ?> o2) {
                return ((String)o1.get(id)).compareTo((String)o2.get(id));
            }
        });
        return resultList;
    }
}
