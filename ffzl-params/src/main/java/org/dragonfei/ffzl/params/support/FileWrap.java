package org.dragonfei.ffzl.params.support;

import org.dragonfei.ffzl.utils.threadpool.ReportException;

import java.util.Map;
import java.util.concurrent.FutureTask;

/**
 * Created by longfei on 16-4-25.
 */
public class FileWrap {
    private FileParse fileParse;
    private FutureTask<String> futureTask;
    private String namespace;

    public FileWrap(FileParse fileParse, FutureTask<String> futureTask,String namespace) {
        this.fileParse = fileParse;
        this.futureTask = futureTask;
        this.namespace = namespace;
    }

    public FutureTask<String> getFutureTask() {
        return futureTask;
    }


    public <T> T parse(ReportException re){
        try {
            return fileParse.parse(futureTask.get(), this.namespace);
        } catch (Exception e){
            re.reportException(e);
        }
        return null;
    }
    public <T> T parse(){
        return parse(e -> e.printStackTrace());
    }


}
