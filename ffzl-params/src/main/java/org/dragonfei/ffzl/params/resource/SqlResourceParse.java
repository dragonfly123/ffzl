package org.dragonfei.ffzl.params.resource;



import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;

import java.util.Map;

/**
 * Created by longfei on 16-4-25.
 */
public class SqlResourceParse extends AbstractFileParse {

    private FileParse fileParse;
    public SqlResourceParse (FileParse fileParse){
        this.fileParse = fileParse;
    }

    @Override
    public Object parse(Object object) {
        return super.parse(this.fileParse.parse(object));
    }

    @Override
    Map<String,Map<String,?>> supportedParse(Object object) {
        return ObjectUtils.nvl((Map)object,Maps.newHashMap());
    }

    @Override
    public <E> boolean supported(E object) {
        if(object instanceof Map){
            return true;
        }
        return false;
    }
}
