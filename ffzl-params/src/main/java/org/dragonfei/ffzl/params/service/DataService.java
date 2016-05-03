package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16/5/3.
 */
public interface DataService {
    List<Map<String,String>> executeQuery(ParamWrap pw);
    Integer executeTotal(ParamWrap pw);
}
