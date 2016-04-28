package org.dragonfei.ffzl.params.support;


import com.google.common.io.Files;
import org.dragonfei.ffzl.utils.collections.ArrayUtils;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.resource.ResourceUtils;
import org.dragonfei.ffzl.utils.spring.SpringContextUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.core.io.Resource;

import java.io.File;


import java.io.IOException;
import java.util.List;

import java.util.concurrent.*;

import static java.nio.charset.StandardCharsets.UTF_8;


/**
 *Created by longfei on 16-4-24.
 */
public class ResourceLoader {
    private static ExecutorService ex = SpringContextUtils.getBean("taskpThreadPool",ExecutorService.class);
    public static ServiceContext load(String reallyNamespace){
        reallyNamespace = StringUtils.nvl(reallyNamespace,"");
        if (ServiceContexts.getServiceContext(reallyNamespace) != null) {
            return ServiceContexts.getServiceContext(reallyNamespace);
        }
        //双端检测,itern确保来自同一个对象
        synchronized (reallyNamespace.intern()) {
            if (ServiceContexts.getServiceContext(reallyNamespace) != null) {
                return ServiceContexts.getServiceContext(reallyNamespace);
            }
            try {
                Resource[] resources = ResourceUtils.getResource(reallyNamespace.replaceAll("\\.", "/"));
                List<FileWrap> fileWraps = Lists.newArrayList();
                for (Resource resource : resources) {
                    if (resource.getFile().isDirectory() && !ArrayUtils.isEmpty(resource.getFile().listFiles())) {
                        for (File file : ArrayUtils.nvl(resource.getFile().listFiles(),new File[0])) {
                            FileParse fileParse = getFileParse(file.getName());
                            if(fileParse != null) {
                                FileWrap fileWrap = new FileWrap(fileParse, new FutureTask<>(() -> {
                                    StringBuilder sb = new StringBuilder();
                                    Files.readLines(file, UTF_8).forEach(sb::append);
                                    return sb.toString();
                                }), reallyNamespace);
                                ex.submit(fileWrap.getFutureTask());
                                fileWraps.add(fileWrap);
                            }
                        }
                    }
                }
                for(FileWrap  fileWrap:fileWraps){
                    fileWrap.parse(Throwable::printStackTrace);
                }

            }  catch (IOException e) {
                e.printStackTrace();
            }
            return ServiceContexts.getServiceContext(reallyNamespace);
        }
    }
    private static FileParse getFileParse(String name){
        FileParse fileParse;
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
        } else {
            fileParse = null;
        }
        return fileParse;

    }

}
