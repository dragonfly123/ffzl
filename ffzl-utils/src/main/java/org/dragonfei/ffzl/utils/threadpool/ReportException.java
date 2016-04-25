package org.dragonfei.ffzl.utils.threadpool;

/**
 * Created by longfei on 16/4/25.
 */
@FunctionalInterface
public interface ReportException {
    void reportException(Throwable e);
}
