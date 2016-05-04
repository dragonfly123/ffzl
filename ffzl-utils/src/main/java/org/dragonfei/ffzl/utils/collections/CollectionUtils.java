package org.dragonfei.ffzl.utils.collections;

import java.util.Collection;
import java.util.List;

/**
 * Created by longfei on 16-4-24.
 */
public abstract class CollectionUtils {
    public static <T> List<T> array2List(T [] objects){
        return org.springframework.util.CollectionUtils.arrayToList(objects);
    }

}
