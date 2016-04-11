package org.dragonfei.ffzl.utils.logs;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by longfei on 16-4-10.
 */
public class RecordLog implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before:"+method);
        Object object=methodProxy.invokeSuper(o, objects);
        System.out.println("After:"+method);
        return object;
    }
}
