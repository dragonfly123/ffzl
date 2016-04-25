package org.dragonfei.ffzl.utils.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by longfei on 16/4/25.
 */
public abstract class ThreadPoolUtils {

    private static ExecutorService executorService ;
    static{
        executorService = Executors.newFixedThreadPool(50);
    }

    private static void createIfeed(){
        if(executorService == null || executorService.isShutdown() || executorService.isTerminated()){
            executorService = Executors.newFixedThreadPool(50);
        }
    }
    public static void submit(Object callable,ReportException re){
        createIfeed();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    if(callable instanceof Callable){

                        ((Callable)callable).call();
                    } else if (callable instanceof Runnable){
                        ((Runnable)callable).run();
                    } else {
                        throw new RuntimeException("不支持");
                    }

                } catch (Exception e){
                    re.reportException(e);
                }
            }
        });
    }

    public static void submit(Object callable){
        submit(callable,e -> e.printStackTrace());
    }
}

