package org.dragonfei.ffzl.utils.collections;

import java.util.Arrays;

/**
 * Created by longfei on 16-4-26.
 */
public class ArrayUtils {

    public static <T> T[] nvl(T[] array,T[] defaults){
        if(array == null){
            array = defaults;
        }
        return  array;
    }

}
