package org.dragonfei.ffzl.params.support;

import org.dragonfei.ffzl.utils.json.JsonUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.Map;

/**
 * Created by longfei on 16-4-25.
 */
public class JsonFileParse extends AbstractFileParse {
    public JsonFileParse(){

    }

    @Override
    Map<String,?> supportedParse(Object object,String namespace) {
        if(StringUtils.isNullOrEmpty((String)object)){
            return null;
        }
        return JsonUtils.parse((String)object,Map.class);
    }

    @Override
    public <E> boolean supported(E object) {
        if(object instanceof String){
            return true;
        }
        return false;
    }
}
