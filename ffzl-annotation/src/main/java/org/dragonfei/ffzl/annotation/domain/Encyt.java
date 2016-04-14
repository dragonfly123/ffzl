package org.dragonfei.ffzl.annotation.domain;

import java.lang.annotation.*;
import java.util.Optional;

/**
 * Created by longfei on 16-4-10.
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encyt {
    boolean value() default false;
    Class clazz() default String.class;
    String function() default "execute";
}
