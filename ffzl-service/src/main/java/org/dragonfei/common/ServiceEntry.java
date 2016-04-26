package org.dragonfei.common;

import org.dragonfei.ffzl.params.ParamWrap;

/**
 * Created by longfei on 16-4-24.
 */
public interface ServiceEntry<T> {
    T execute(ParamWrap pw);
}
