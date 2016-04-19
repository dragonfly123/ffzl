package org.dragonfei.ffzl.annotation.domain;

import org.dragonfei.ffzl.utils.collections.Lists;

import java.util.List;

/**
 * Created by admin on 16/4/19.
 */
public class SqlParam {
    public String sql = "";
    public List<String> params = Lists.newArrayList();

    @Override
    public String toString() {
        return "SqlParam{" +
                "sql='" + sql + '\'' +
                ", params=" + params +
                '}';
    }
}
