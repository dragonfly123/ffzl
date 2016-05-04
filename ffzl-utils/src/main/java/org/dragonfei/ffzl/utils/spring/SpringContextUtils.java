package org.dragonfei.ffzl.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by longfei on 16-4-25.
 */

@Component
public class SpringContextUtils  implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context  =  applicationContext;
    }

    /**
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> T getBean(String name,Class<T> clazz){
        return  context.getBean(name,clazz);
    }

    /**
     *
     * @param clzz
     * @param <T>
     * @return
     */
    public static<T> T getBean(Class<T> clzz){
        return context.getBean(clzz);
    }
}
