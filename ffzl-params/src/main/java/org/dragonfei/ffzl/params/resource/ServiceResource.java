package org.dragonfei.ffzl.params.resource;

import com.google.common.io.Files;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *Created by longfei on 16-4-29.
 */
public class ServiceResource {
    private ExecutorService ex;
    private List<File> files = Lists.newArrayList();
    private FileParse fileParse;
    private Map<String,Map<String,?>> serviceMap = Maps.newConcurrentHashMap();
    private CountDownLatch countDownLatch;
    private final Lock lock = new ReentrantLock();

    public ServiceResource(ExecutorService ex, FileParse fileParse) {
        this.ex = ex;
        this.fileParse = fileParse;
    }

    public void addFile(File file){
        files.add(file);
    }

    public void load(){
        if((countDownLatch == null || countDownLatch.getCount() == files.size()) && lock.tryLock()){
            countDownLatch = new CountDownLatch(files.size());
            lock.unlock();
        } else {
            await();
        }
        if(!ObjectUtils.isEmpty(files) && ObjectUtils.isEmpty(serviceMap)) {
            files.forEach(file -> ex.submit(() -> {
                try {
                    StringBuilder sb = new StringBuilder();
                    Files.readLines(file, StandardCharsets.UTF_8).forEach(sb::append);
                    serviceMap.putAll(fileParse.parse(sb.toString()));
                    countDownLatch.countDown();
                } catch (IOException e) {

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

    public void clear(){
        if(!ObjectUtils.isEmpty(serviceMap)) {
            await();
            synchronized (this) {
                if(!ObjectUtils.isEmpty(serviceMap)) {
                    await();
                    serviceMap.clear();
                    countDownLatch = new CountDownLatch(files.size());
                }
            }
        }
    }

    public void reload(){
        clear();
        load();
    }

    public Map<String,?> getResourceMap(String servicename){
        if(serviceMap.containsKey(servicename)){
            return serviceMap.get(servicename);
        } else {
            reload();
            return serviceMap.get(servicename);
        }
    }

}
