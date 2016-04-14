package org.dragonfei;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * Created by longfei on 16-4-14.
 */
public class TestPointCut implements Pointcut {
    @Override
    public ClassFilter getClassFilter() {
        return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MyMethodMatcher();
    }
}

class MyMethodMatcher implements MethodMatcher{
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        Class<?> reallyClass = targetClass;
        if(reallyClass  == null){
            reallyClass = method.getDeclaringClass();
        }
        if(HelloAop.class.isAssignableFrom(reallyClass)){
            return true;
        }
        return false;

    }

    @Override
    public boolean isRuntime() {
        return true;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return true;
    }
}
