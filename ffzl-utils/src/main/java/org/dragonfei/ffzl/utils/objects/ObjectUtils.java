package org.dragonfei.ffzl.utils.objects;

/**
 * Created by admin on 16/4/19.
 */
public abstract class ObjectUtils {
    /**
     * 判断对象是否为空
     * @param object
     * @param <T>
     * @return
     */
    public final static <T> boolean isEmpty(T object){
        return org.springframework.util.ObjectUtils.isEmpty(object);
    }
}
