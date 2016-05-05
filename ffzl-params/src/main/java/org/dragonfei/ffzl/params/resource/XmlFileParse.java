package org.dragonfei.ffzl.params.resource;


/**
 * Created by longfei on 16-4-25.
 * xml格式文件解析,未实现
 */
public class XmlFileParse extends AbstractFileParse {
    @Override
    Object supportedParse(Object object) {
        //test
        return null;
    }

    @Override
    public <E> boolean supported(E object) {
        if(object instanceof String){
            return true;
        }
        return false;
    }
}
