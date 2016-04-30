package org.dragonfei.ffzl.params.resource;

import org.dragonfei.ffzl.utils.collections.Maps;

import java.util.Map;

/**
 * Created by longfei on 16-4-29.
 */
public class ResourceContexts {
    private static Map<String,ResourceContext> resourceContexts = Maps.newConcurrentHashMap();
    public static void add(ResourceContext resourceContext){
        resourceContexts.put(resourceContext.getNamespace(),resourceContext);
    }

    public static ResourceContext get(String namespace){
        return resourceContexts.get(namespace);
    }

}
