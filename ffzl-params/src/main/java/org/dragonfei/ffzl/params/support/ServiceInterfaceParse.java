package org.dragonfei.ffzl.params.support;

import java.util.Map;

/**
 * Created by longfei on 16-4-25.
 */
public class ServiceInterfaceParse extends AbstractFileParse{

    private FileParse fileParse;
    public  ServiceInterfaceParse(FileParse fileParse){
        this.fileParse = fileParse;
    }

    @Override
    public <T, E> T parse(E object, String namespace) {
        this.fileParse.parse(object,namespace);
        return super.parse(this.fileParse.parse(object,namespace),namespace);
    }

    @Override
    <T, E> T supportedParse(E object,String namespace) {
        return null;
    }

    @Override
    public <E> boolean supported(E object) {
        if(object instanceof Map){
            return true;
        }
        return false;
    }
}
