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
     * @param <E>
     * @return
     */
    <T,E> E handle(T obj);
}
