package org.dragonfei.ffzl.params.resource;

import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.json.JsonUtils;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;

import java.util.Map;

/**
 * Created by longfei on 16-4-25.
 */
public class JsonFileParse extends AbstractFileParse {
    public JsonFileParse(){

    }

    @Override
    Map<String,?> supportedParse(Object object) {
        if(ObjectUtils.isEmpty(object)){
            return Maps.newConcurrentHashMap();
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
