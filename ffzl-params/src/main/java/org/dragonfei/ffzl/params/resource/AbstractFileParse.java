package org.dragonfei.ffzl.params.resource;


/**
 * Created by longfei on 16-4-25.
 * 简单处理解析,之类只需要实现此方法
 */
public abstract class AbstractFileParse implements FileParse {
    @Override
    public Object parse(Object object) {
        if(supported(object)){
            return supportedParse(object);
        }
        return object;
    }


    @Override
    public <E> boolean supported(E object) {
        return false;
    }

    /**
     * @param object
     * @return
     * 真实的解析方法,执行此方法,必须是 @see #supported(Object) 为true
     */
    abstract  Object supportedParse(Object object);
}
