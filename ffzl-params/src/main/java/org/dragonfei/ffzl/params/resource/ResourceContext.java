package org.dragonfei.ffzl.params.resource;

import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;

import java.util.Map;

/**
 * Created by longfei on 16-4-29.
 * 资源文件上下文
 */
public class ResourceContext {
    private String namespace;
    private Map<String,ServiceResource> serviceResourceMap = Maps.newConcurrentHashMap();

    public ResourceContext(String namespace) {
        this.namespace = namespace;
    }

    public String getNamespace() {
        return namespace;
    }

    public void addServiceResource(String name, ServiceResource serviceResource){
        serviceResourceMap.put(name,serviceResource);
    }

    public ServiceResource getServiceResource(String name){
        return serviceResourceMap.get(name);
    }

    public Map<String,?> getResourceMap(String name,String servicename) {
        ServiceResource serviceResource = getServiceResource(name);
        Map<String,?> map = null;
        if (serviceResource != null) {
            map = serviceResource.getResourceMap(servicename);
        }
        return ObjectUtils.nvl(map,Maps.newConcurrentHashMap());

    }
}
