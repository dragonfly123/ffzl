package org.dragonfei.ffzl.params.service;

import org.dragonfei.ffzl.params.ParamWrap;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16/5/3.
 * 数据服务
 */
public interface DataService {
    /**
     * 查询数据
     * @param pw
     * @return
     */
    List<Map<String,String>> executeQuery(ParamWrap pw);

    /**
     * 查询总记录数
     * @param pw
     * @return
     */
    Integer executeTotal(ParamWrap pw);
}
