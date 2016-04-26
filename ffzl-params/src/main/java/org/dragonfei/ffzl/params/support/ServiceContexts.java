package org.dragonfei.ffzl.params.support;

import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-4-24.
 */
public class ServiceContexts {
    private static ServiceContexts instance  =  new ServiceContexts();
    private ServiceContexts(){

    }

    public ServiceContexts getInstance() {
        return instance;
    }

    private Map<String,ServiceContext> serviceContes = Maps.newHashMap();

    public static  void add(ServiceContext serviceContext){
        instance.serviceContes.put(serviceContext.getNamespace(),serviceContext);
    }

    public static void remove(String namespace){
        instance.serviceContes.remove(namespace);
    }

    public static  void remove(ServiceContext serviceContext){
        instance.remove(serviceContext.getNamespace());
    }

    public static ServiceContext getServiceContext(String namespace){
        return instance.serviceContes.get(namespace);
    }

}
