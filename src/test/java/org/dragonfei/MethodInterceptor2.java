package org.dragonfei;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Created by longfei on 16-4-13.
 */
public class MethodInterceptor2 implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before2");
        Object obj  = invocation.proceed();
        System.out.println("after2");
        return obj;

    }
}
