package org.dragonfei.ffzl.utils.string;


import com.google.common.base.Strings;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;
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
     * @see org.springframework.util.StringUtils#collectionToDelimitedString(Collection, String, String, String)
     * @param collection
     * @param commaa
     * @param prefix
     * @param sufferix
     * @return
     */
    public static String toCommaDelimitedString(Collection collection,String commaa,String prefix,String sufferix){
        return org.springframework.util.StringUtils.collectionToDelimitedString(collection,commaa,prefix,sufferix);
    }

    /**
     * @see #toCommaDelimitedString(Collection, String, String, String)
     * @param collection
     * @param prefix
     * @param sufferix
     * @return
     */
    public static String toCommaDelimitedString(Collection collection,String prefix,String sufferix){
        return org.springframework.util.StringUtils.collectionToDelimitedString(collection,",",prefix,sufferix);
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
     * 自定义集合的每一项处理
     * @param collection
     * @param handle
     * @return
     */
    public static String toCommaDelimitedString(Collection collection,StringHandle handle){
        if (CollectionUtils.isEmpty(collection)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(handle.handle(it.next()).toString());
        }
        return sb.toString();
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


    public static String[] split(String str,String comma){

        return org.springframework.util.StringUtils.tokenizeToStringArray(str,comma);
    }


}
