package org.dragonfei.ffzl.utils.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by longfei on 16-4-10.
 */
public class AnnotationUtils {
    /**
     * @see org.springframework.core.annotation.AnnotationUtils#findAnnotation(Class, Class)
     * @param clazz the class to look for annotations on
     * @param annotationType the type of annotation to look for
     * @return the first matching annotation, or {@code null} if not found
     */
    public static <A extends Annotation> A findAnnotation(Class<?> clazz, Class<A> annotationType) {

        return org.springframework.core.annotation.AnnotationUtils.findAnnotation(clazz,annotationType);
    }

    /**
     * @see org.springframework.core.annotation.AnnotationUtils#findAnnotation(Method, Class)
     * @param method the method to look for annotations on
     * @param annotationType the annotation type to look for
     * @return the first matching annotation, or {@code null} if not found
     */
    public static <A extends Annotation> A findAnnotation(Method method, Class<A> annotationType){

        return org.springframework.core.annotation.AnnotationUtils.findAnnotation(method,annotationType);
    }
}
