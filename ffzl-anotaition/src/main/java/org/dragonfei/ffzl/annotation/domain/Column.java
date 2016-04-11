package org.dragonfei.ffzl.annotation.domain;

import java.lang.annotation.*;


/**
 * Created by longfei on 16-4-10.
 * the column
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

    /**
     *
     * @return the column name
     */
    String value() default "";

    /**
     * the describle of column
     * @return
     */
    String desc() default "";

    boolean required() default false;


    int minLength() default 0;

    int maxLength() default 1000;

    boolean unique() default false;

    boolean  notNull() default true;

    /**
     * 业务主健
     * @return
     */
    boolean bk() default false;

    /**
     * 逻辑主健
     */
    boolean pk() default false;

}
