package org.dragonfei.ffzl.params.support;

import org.dragonfei.ffzl.utils.collections.Maps;

import java.util.Map;

/**
 * Created by longfei on 16-4-25.
 */
public class SqlResourceParse extends AbstractFileParse {

    private FileParse fileParse;
    public SqlResourceParse (FileParse fileParse){
        this.fileParse = fileParse;
    }

    @Override
    public Object parse(Object object, String namespace) {
        this.fileParse.parse(object,namespace);
        return super.parse(this.fileParse.parse(object,namespace),namespace);
    }

    @Override
    ServiceContext supportedParse(Object object,String namespace) {
        ServiceContext sc = ServiceContexts.getServiceContext(namespace);
        if(sc == null){
            sc = new ServiceContext();
            sc.setNamespace(namespace);
            ServiceContexts.add(sc);
        }
        Map<String,Object> map = sc.getSqlResources();
        if(map == null){
            map = Maps.newConcurrentHashMap();
            sc.setSqlResources(map);
        }
        map.putAll((Map)object);
        return  sc;
    }

    @Override
    public <E> boolean supported(E object) {
        if(object instanceof Map){
            return true;
        }
        return false;
    }
}
