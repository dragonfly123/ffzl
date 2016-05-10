package org.dragonfei.common.impl;


import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.params.service.ServiceEntry;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.spring.SpringContextUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by longfei on 16/5/9.
 */
@Service
public class ServiceDelegate<T> implements ServiceEntry<T> {
    @Override
    public T execute(ParamWrap pw) {
        ServiceResource sr = ServiceResource.getServiceResource(pw.getFullservicename(),"servicemap", StringUtils.EMTY);
        Map<String,?> map =  sr.getResourceMap(pw.getServicename());
        String serviceclass = "commonRsEntry";
        if(!ObjectUtils.isEmpty(map)){
            serviceclass = ObjectUtils.nvl((String)map.get("serviceclass"),"commonRsEntry");
        }
        ServiceEntry serviceEntry =  SpringContextUtils.getBean(serviceclass,ServiceEntry.class);
        if(serviceEntry == null){
            throw new RuntimeException(String.format("the bean %s does not exist",serviceclass));
        }
        return (T)serviceEntry.execute(pw);
    }
}
