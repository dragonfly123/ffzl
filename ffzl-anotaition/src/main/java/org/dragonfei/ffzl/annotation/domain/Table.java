package org.dragonfei.ffzl.annotation.domain;

import java.lang.annotation.*;

/**
 * Created by longfei on 16-4-9.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
    /**
     * the name of table
     */
    String value() default "";

    /**
     * the descible of table
     */
    String desc() default "";
}
