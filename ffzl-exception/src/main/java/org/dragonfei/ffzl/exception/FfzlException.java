package org.dragonfei.ffzl.exception;

/**
 * Created by longfei on 16-4-10.
 */
public abstract class FfzlException extends RuntimeException {
    private Type type = Type.SYSTEM;

    public FfzlException(Type type) {
        super();
        this.type=type;
    }


    public FfzlException(String message,Type type) {

        super(message);
        this.type  = type;
    }


    public FfzlException(String message, Throwable cause,Type type) {
        super(message, cause);
        this.type = type;
    }


    public FfzlException(Throwable cause,Type type) {
        super(cause);
        this.type = type;
    }


    protected FfzlException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace,Type type) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.type = type;
    }

}
