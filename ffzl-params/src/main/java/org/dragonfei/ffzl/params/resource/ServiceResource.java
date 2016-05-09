package org.dragonfei.ffzl.params.resource;

import com.google.common.base.Throwables;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 *Created by longfei on 16-4-29.
 * 加载配置文件的类,支持按需加载
 */
public class ServiceResource {
    private ExecutorService ex;
    private List<Resource> resources = Lists.newArrayList();
    private FileParse fileParse;
    private Map<String,Map<String,?>> serviceMap = Maps.newConcurrentHashMap();
    private CountDownLatch countDownLatch;
    private final Lock lock = new ReentrantLock();

    public ServiceResource(ExecutorService ex, FileParse fileParse) {
        this.ex = ex;
        this.fileParse = fileParse;
    }

    public void addResource(Resource file){
        resources.add(file);
    }

    /**
     * 具体加载资源文件
     */
    public void load(){
        if((countDownLatch == null || countDownLatch.getCount() == resources.size()) && lock.tryLock()){
            countDownLatch = new CountDownLatch(resources.size());
            lock.unlock();
        } else {
            await();
        }
        if(!ObjectUtils.isEmpty(resources) && ObjectUtils.isEmpty(serviceMap)) {
            resources.forEach(resource -> ex.submit(() -> {
                try {
                    InputStream inputStream = resource.getInputStream();
                    serviceMap.putAll(fileParse.parse(CharStreams.toString(new InputStreamReader(inputStream,UTF_8))));
                    countDownLatch.countDown();
                } catch (IOException e) {
                    Throwables.propagate(e);
                }
            }));
            await();
        }
    }

    private void await(){
        if(countDownLatch != null){
            try {
                countDownLatch.await(2,TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 清除缓存
     */
    public void clear(){
        if(!ObjectUtils.isEmpty(serviceMap)) {
            await();
            synchronized (this) {
                if(!ObjectUtils.isEmpty(serviceMap)) {
                    await();
                    serviceMap.clear();
                    countDownLatch = new CountDownLatch(resources.size());
                }
            }
        }
    }

    public void reload(){
        clear();
        load();
    }

    /**
     * @param servicename
     * @return
     * 获取资源文件下对应servicename下配置
     */
    public Map<String,?> getResourceMap(String servicename){
        if(serviceMap.containsKey(servicename)){
            return serviceMap.get(servicename);
        } else {
            reload();
            return serviceMap.get(servicename);
        }
    }


    public static ServiceResource getServiceResource(String servicename,String resourceName,String relative){
        if(!servicename.contains("_")){
            servicename = relative.substring(0,relative.lastIndexOf("_")+1)+servicename;
        }

        String[] paths = StringUtils.split(servicename, "_");
        String namespace = StringUtils.toCommaDelimitedString(paths, ".");
        String reallyNamespace = namespace.substring(0, namespace.lastIndexOf("."));
        ResourceLoader resourceLoader = ResourceLoaderFactory.getResourceLoader("json", resourceName);
        return resourceLoader.load(reallyNamespace);
    }

}
