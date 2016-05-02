package org.dragonfei.ffzl.utils.collections;

import java.util.Arrays;

/**
 * Created by longfei on 16-4-26.
 */
public class ArrayUtils {
    public static<T> boolean isEmpty(T[] array){
        if(array == null || array.length == 0){
            return true;
        }
        return false;
    }

    public static <T> T[] nvl(T[] array,T[] defaults){
        if(array == null){
            array = defaults;
        }
        return  array;
    }

    public static <T> T[] copof(T... source){
        return Arrays.copyOf(source,source.length);
    }
}
