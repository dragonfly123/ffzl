package org.dragonfei.ffzl.params.resource;

import org.dragonfei.ffzl.utils.collections.ArrayUtils;
import org.dragonfei.ffzl.utils.resource.ResourceUtils;
import org.dragonfei.ffzl.utils.spring.SpringContextUtils;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * Created by longfei on 16-4-24.
 */
public class ResourceLoader {

    private static ResourceLoader instance = new ResourceLoader();
    private String resourceType =  "";
    private FileParse fileParse;

    public String getResourceType() {
        return resourceType;
    }

    private ResourceLoader(){

    }
    public static class Builder{
        private ResourceLoader instance = new ResourceLoader();
        public Builder type(String type){

            if("json".equals(type)){
                instance.fileParse =  new JsonFileParse();
            } else if("xml".equals(type)){
                instance.fileParse = new XmlFileParse();
            } else {
                throw new IllegalArgumentException("不支持的文件类型:"+type);
            }
            return this;
        }

        public Builder name(String name){
            instance.resourceType = name;
            if("serviceinterface".equals(name)){
                instance.fileParse = new ServiceInterfaceParse(instance.fileParse);
            } else if("sql".equals(name)){
                instance.fileParse = new SqlResourceParse(instance.fileParse);
            } else {
                throw new IllegalArgumentException("不支持的文件名："+name);
            }
            return this;
        }

        public ResourceLoader build(){
            return instance;
        }
    }

    private ExecutorService ex = SpringContextUtils.getBean("taskpThreadPool",ExecutorService.class);

    public ServiceResource load(String namespace){
        synchronized (namespace.intern()) {
            if (ResourceContexts.get(namespace) == null) {
                ResourceContext resourceContext = new ResourceContext(namespace);
                ResourceContexts.add(resourceContext);
            }
            ResourceContext resourceContext = ResourceContexts.get(namespace);
            if (resourceContext != null &&resourceContext.getServiceResource(resourceType) != null) {
                return resourceContext.getServiceResource(resourceType);
            } else {
                try {
                    Resource[] resources = ResourceUtils.getResource(namespace.replaceAll("\\.", "/"));
                    for (Resource resource : resources) {
                        if (resource.getFile().isDirectory() && !ArrayUtils.isEmpty(resource.getFile().listFiles())) {
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
