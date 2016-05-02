package org.dragonfei.ffzl.utils.string;

import java.util.Collection;

/**
 * Created by admin on 16/4/20.
 */
@FunctionalInterface
public interface StringHandle {
    /**
     * @see StringUtils#toCommaDelimitedString(Collection, StringHandle)
     * @param obj
     * @param <T>
     * @return
     */
    <T> String handle(T obj);
}
