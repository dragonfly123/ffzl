package org.dragonfei.ffzl.params.resource;

import org.dragonfei.ffzl.utils.collections.Maps;

import java.util.Map;

/**
 * Created by longfei on 16-4-29.
 * 包含多个上下文,每个命名空间都包含一个
 */
public class ResourceContexts {
    private ResourceContexts(){
    }
    private static ResourceContexts instance;
    public static ResourceContexts getInstance(){
        if(instance == null){
            synchronized (ResourceContexts.class){
                if(instance == null){
                    instance = new ResourceContexts();
                }
            }
        }
        return instance;
    }
    private Map<String,ResourceContext> resourceContexts = Maps.newConcurrentHashMap();
    public void add(ResourceContext resourceContext){
        resourceContexts.put(resourceContext.getNamespace(),resourceContext);
    }

    public ResourceContext get(String namespace){
        return resourceContexts.get(namespace);
    }

}
