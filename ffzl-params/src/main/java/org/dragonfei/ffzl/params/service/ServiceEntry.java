package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;

/**
 * Created by longfei on 16-4-28.
 * 服务实体
 */
public interface ServiceEntry<T> {
    T execute(ParamWrap pw);
}
