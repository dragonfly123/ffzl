package org.dragonfei.ffzl.dao.impl;

import org.dragonfei.ffzl.dao.CommonDao;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-4-20.
 */
@Repository
public class CommonDaoImpl implements CommonDao {
    @Qualifier("ffzltemplate")
    @Autowired private JdbcTemplate jdbcTemplate;
    @Override
    public List<Map<String,String>> select(String sql, List params) {
        return jdbcTemplate.query(sql, params.toArray(), rs-> {
            List<Map<String, String>> resultList = Lists.newArrayList();
            Map<Integer, String> indexMap = Maps.newHashMap();
            int row = 0;
            int columnCount = 0;
            while (rs.next()) {
                Map<String, String> resultMap = Maps.newHashMap();
                if (row == 0) {
                    columnCount = rs.getMetaData().getColumnCount();
                    for (int i = 0; i < columnCount; i++) {
                        indexMap.put(i + 1, rs.getMetaData().getColumnName(i + 1));
                    }
                }
                for (int i = 0; i < columnCount; i++) {
                    resultMap.put(indexMap.get(i + 1), rs.getString(i + 1));
                }
                resultList.add(resultMap);

            }
            return resultList;
        });
    }
}
