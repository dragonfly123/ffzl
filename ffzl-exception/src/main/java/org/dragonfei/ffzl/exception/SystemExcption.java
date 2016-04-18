package org.dragonfei.ffzl.exception;

/**
 * Created by longfei on 16-4-10.
 */
public class SystemExcption extends FfzlException {

    public SystemExcption(Type type) {
        super(type);
    }

    public SystemExcption(String message, Type type) {
        super(message, type);
    }

    public SystemExcption(String message, Throwable cause, Type type) {
        super(message, cause, type);
    }

    public SystemExcption(Throwable cause, Type type) {
        super(cause, type);
    }

    public SystemExcption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Type type) {
        super(message, cause, enableSuppression, writableStackTrace, type);
    }
}
