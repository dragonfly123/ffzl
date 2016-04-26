package org.dragonfei.ffzl.params.support;

/**
 * Created by longfei on 16-4-25.
 */
public abstract class AbstractFileParse implements FileParse {
    @Override
    public <T, E> T parse(E object,String namespace) {
        if(supported(object)){
            return supportedParse(object,namespace);
        }
        return null;
    }

    @Override
    public <E> boolean supported(E object) {
        return false;
    }

    abstract  <T,E> T supportedParse(E object,String namespace);
}
