package org.dragonfei.ffzl.params.support;

import java.util.Map;

/**
 * Created by longfei on 16-4-24.
 */
public class ServiceContext {

    private String namespace;
    private Map<String,Object> serviceinterfaces;
    private Map<String,Object>  sqlResources;

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
