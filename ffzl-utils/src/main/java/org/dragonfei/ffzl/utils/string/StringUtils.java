package org.dragonfei.ffzl.utils.string;


import com.google.common.base.Strings;

import java.lang.annotation.Documented;

/**
 * the utils of String
 * Created by longfei on 16-4-10.
 */
public abstract class StringUtils {

    /**
     * @see Strings#isNullOrEmpty(String)
     * @param str
     * @param defalts
     * @return
     */
    public static String nvl(String str,String defalts){
        return Strings.isNullOrEmpty(str)?defalts:str;
    }

    /**
     * @see Strings#isNullOrEmpty(String)
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String  str){

        return Strings.isNullOrEmpty(str);
    }
}
