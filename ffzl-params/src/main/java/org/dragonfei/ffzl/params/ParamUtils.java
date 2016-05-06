package org.dragonfei.ffzl.params;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Created by longfei on 16-4-23.
 */
public abstract class ParamUtils {

    public static ParamWrap buildParams(HttpServletRequest request){
        Enumeration<String>  enumeration = request.getParameterNames();
        ParamWrap.Builder pbuilder= ParamWrap.newInstance().new Builder();

        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value =  request.getParameter(name);
            pbuilder.param(name,value);
        }

        return pbuilder.build();
    }
}
