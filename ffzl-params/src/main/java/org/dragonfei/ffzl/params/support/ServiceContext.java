package org.dragonfei.ffzl.params.support;

import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.Map;

/**
 * Created by longfei on 16-4-24.
 */
public class ServiceContext {

    private String namespace = "";
    private Map<String,Object> serviceinterfaces = Maps.newConcurrentHashMap();
    private Map<String,Object>  sqlResources = Maps.newConcurrentHashMap();

    public Map<String, Object> getServiceinterfaces() {
        return serviceinterfaces;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Map<String, Object> getSqlResources() {
        return sqlResources;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setServiceinterfaces(Map<String, Object> serviceinterfaces) {
        this.serviceinterfaces = serviceinterfaces;
    }

    public void setSqlResources(Map<String, Object> sqlResources) {
        this.sqlResources = sqlResources;
    }

    public Map<String,?> getServiceInterface(String servicename){
        return (Map<String, ?>) serviceinterfaces.get(StringUtils.nvl(servicename,""));
    }

    public Map<String,?> getSqlResource(String servicename){
        return (Map<String, ?>) sqlResources.get(StringUtils.nvl(servicename,""));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceContext that = (ServiceContext) o;

        return namespace.equals(that.namespace);

    }

    @Override
    public int hashCode() {
        return namespace.hashCode();
    }

    @Override
    public String toString() {
        return "ServiceContext{" +
                "namespace='" + namespace + '\'' +
                ", serviceinterfaces=" + serviceinterfaces +
                ", sqlResources=" + sqlResources +
                '}';
    }
}
