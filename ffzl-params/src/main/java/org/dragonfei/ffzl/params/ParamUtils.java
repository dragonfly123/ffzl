package org.dragonfei.ffzl.params;

import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by longfei on 16-4-23.
 */
public abstract class ParamUtils {

    public static ParamWrap buildParams(HttpServletRequest request){
        Enumeration<String>  enumeration = request.getParameterNames();
        ParamWrap pw = new ParamWrap();

        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value =  request.getParameter(name);
            pw.param(name,value);
        }

        return pw;
    }
    public static ParamWrap buildParams(Map<String,String> map){
        ParamWrap pw = new ParamWrap();
        map.forEach((k,v)->pw.param(k,v));
        return pw;
    }
}
