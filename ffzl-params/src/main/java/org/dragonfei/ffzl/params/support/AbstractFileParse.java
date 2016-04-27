package org.dragonfei.ffzl.params.support;

/**
 * Created by longfei on 16-4-25.
 */
public abstract class AbstractFileParse implements FileParse {
    @Override
    public Object parse(Object object,String namespace) {
        if(supported(object)){
            return supportedParse(object,namespace);
        }
        return object;
    }

    @Override
    public <E> boolean supported(E object) {
        return false;
    }

    abstract  Object supportedParse(Object object,String namespace);
}
