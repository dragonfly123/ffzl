package org.dragonfei.ffzl.utils.string;


import com.google.common.base.Strings;
import org.dragonfei.ffzl.utils.collections.Lists;

import java.lang.annotation.Documented;
import java.util.Collection;
import java.util.List;

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

    /**
     * @see org.springframework.util.StringUtils#collectionToDelimitedString(Collection, String)
     * @param collection
     * @param commaa
     * @return
     */
    public static String toCommaDelimitedString(Collection collection,String commaa){
        return org.springframework.util.StringUtils.collectionToDelimitedString(collection,commaa);
    }

    /**
     * @see #toCommaDelimitedString(Collection, String)
     * @param collection
     * @return
     */
    public static String toCommaDelimitedString(Collection collection){
        return org.springframework.util.StringUtils.collectionToDelimitedString(collection,",");
    }

    /**
     * @see org.springframework.util.StringUtils#arrayToDelimitedString(Object[], String)
     * @param obj
     * @param comma
     * @return
     */
    public static String toCommaDelimitedString(Object[] obj,String comma){
        return org.springframework.util.StringUtils.arrayToDelimitedString(obj,comma);
    }

    /**
     * @see #toCommaDelimitedString(Object[])
     * @param obj
     * @return
     */
    public static String toCommaDelimitedString(Object[] obj){
        return org.springframework.util.StringUtils.arrayToDelimitedString(obj,",");
    }

    /**
     * repeat a string num time
     * @param str
     * @param comma
     * @param num
     * @return
     */
    public static String repeatString(String str,String comma,int num){
        List<String> list = Lists.newArrayList(num);
        for(int i = 0; i < num;i++){
            list.add(str);
        }
        return toCommaDelimitedString(list,comma);
    }

    /**
     * @see #repeatString(String, String, int)
     * @param str
     * @param num
     * @return
     */
    public static String repeatString(String str,int num){
        List<String> list = Lists.newArrayList(num);
        for(int i = 0; i < num;i++){
            list.add(str);
        }
        return toCommaDelimitedString(list,",");
    }
}
