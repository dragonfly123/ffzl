package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16/6/20.
 * 标记接口
 */
public interface UpdateDataService {

    List<Map<String,?>> queryByBk(ParamWrap pw);

    Long executeInsert(ParamWrap pw);

    Long executeModify(ParamWrap pw);

    boolean needInsert(ParamWrap pw);

}
