package org.dragonfei.ffzl.params.resource;

import org.dragonfei.ffzl.utils.collections.ArrayUtils;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.resource.ResourceUtils;
import org.dragonfei.ffzl.utils.spring.SpringContextUtils;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by longfei on 16-4-24.
 */
public class ResourceLoader {

    private String resourceType =  "";
    private FileParse fileParse;

    private ResourceLoader(String resourceType, FileParse fileParse) {
        this.resourceType = resourceType;
        this.fileParse = fileParse;
    }

    private static Map<String,ResourceLoader> map = Maps.newHashMap();
    public static ResourceLoader getInstance(String resourceType,FileParse fileParse){
        String key = resourceType+"#"+fileParse.getClass();
        if(map.containsKey(key)){
           return map.get(key);
        } else {
            synchronized (key.intern()){
                if(map.containsKey(key)){
                    return map.get(key);
                }
                ResourceLoader resourceLoader = new ResourceLoader(resourceType,fileParse);
                map.put(key,resourceLoader);
                return resourceLoader;
            }
        }
    }

    private ExecutorService ex = SpringContextUtils.getBean("taskpThreadPool",ExecutorService.class);

    public ServiceResource load(String namespace){
        synchronized (namespace.intern()) {
            if (ResourceContexts.getInstance().get(namespace) == null) {
                ResourceContext resourceContext = new ResourceContext(namespace);
                ResourceContexts.getInstance().add(resourceContext);
            }
            ResourceContext resourceContext = ResourceContexts.getInstance().get(namespace);
            if (resourceContext != null &&resourceContext.getServiceResource(resourceType) != null) {
                return resourceContext.getServiceResource(resourceType);
            } else {
                try {
                    Resource[] resources = ResourceUtils.getResource(namespace.replaceAll("\\.", "/"));
                    for (Resource resource : resources) {
                        if (resource.getFile().isDirectory() && !ObjectUtils.isEmpty(resource.getFile().listFiles())) {
                            for (File file : ArrayUtils.nvl(resource.getFile().listFiles(), new File[0])) {
                                if (resourceType.equals(file.getName().substring(0, file.getName().lastIndexOf(".")))) {
                                    if (resourceContext.getServiceResource(resourceType) == null) {
                                        ServiceResource sr = new ServiceResource(ex, fileParse);
                                        resourceContext.addServiceResource(resourceType, sr);
                                    }
                                    ServiceResource sr = resourceContext.getServiceResource(resourceType);
                                    sr.addFile(file);
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return resourceContext.getServiceResource(resourceType);
        }
    }

}
