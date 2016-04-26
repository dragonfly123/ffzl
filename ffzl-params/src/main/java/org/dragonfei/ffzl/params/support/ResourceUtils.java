package org.dragonfei.ffzl.params.support;


import com.google.common.io.Files;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.json.JsonUtils;
import org.dragonfei.ffzl.utils.spring.SpringContextUtils;
import org.springframework.core.io.Resource;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static java.nio.charset.StandardCharsets.UTF_8;


/**
 * Created by longfei on 16-4-24.
 */
public class ResourceUtils {
    private static Map<String,FileParse> fileParseMap = Maps.newHashMap();
    static {

    }
    private static ExecutorService ex = SpringContextUtils.getBean("taskpThreadPool",ExecutorService.class);
    public static ServiceContext  loadResource(String reallyNamespace){

        if(ServiceContexts.getServiceContext(reallyNamespace) != null){
            return ServiceContexts.getServiceContext(reallyNamespace);
        }
        try {
            Resource[] resources = org.dragonfei.ffzl.utils.resource.ResourceUtils.getResource(reallyNamespace.replaceAll("\\.", "/"));
            FutureTask<Map<String, Object>> serviceinterfacetask = null;
            FutureTask<Map<String, Object>> sqlTask = null;
            List<FileWrap> fileWraps = Lists.newArrayList();
            for (Resource resource : resources) {
                if (resource.getFile().isDirectory() && resource.getFile().listFiles().length > 0) {
                    for (File file : resource.getFile().listFiles()) {
                        FileParse fileParse = getFileParse(file.getName());
                        FileWrap fileWrap = new FileWrap(fileParse, new FutureTask<String>(new Callable<String>() {
                            @Override
                            public String call() throws Exception {
                                List<String> list = Files.readLines(file, UTF_8);
                                StringBuilder sb = new StringBuilder();
                                for (String str : list) {
                                    sb.append(str);
                                }
                                return sb.toString();
                            }
                        }),reallyNamespace);
                        ex.submit(fileWrap.getFutureTask());
                        fileWraps.add(fileWrap);
                    }
                }
            }
            for (FileWrap fileWrap : fileWraps) {
                fileWrap.parse(e -> e.printStackTrace());
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e2){

        }
        return ServiceContexts.getServiceContext(reallyNamespace);
    }
    private static FileParse getFileParse(String name){
        FileParse fileParse = null;
        if(name.endsWith(".json")){
            fileParse = new JsonFileParse();
        } else if(name.endsWith(".xml")){
            fileParse = new XmlFileParse();
        } else {
            fileParse = new JsonFileParse();
        }

        if("serviceinterface".equals(name.substring(0,name.lastIndexOf(".")))){
            fileParse  = new ServiceInterfaceParse(fileParse);
        } else if("sql".equals(name.substring(0,name.lastIndexOf(".")))){
            fileParse = new SqlResourceParse(fileParse);
        }
        return fileParse;

    }

}
