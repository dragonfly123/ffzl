package org.dragonfei.ffzl.params.resource;


/**
 * Created by longfei on 16-4-25.
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

    abstract  Object supportedParse(Object object);
}
