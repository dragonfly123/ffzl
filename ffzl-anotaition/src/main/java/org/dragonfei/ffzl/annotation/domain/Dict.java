package org.dragonfei.ffzl.annotation.domain;

import java.lang.annotation.*;

/**
 * Created by longfei on 16-4-10.
 */

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dict {

}
