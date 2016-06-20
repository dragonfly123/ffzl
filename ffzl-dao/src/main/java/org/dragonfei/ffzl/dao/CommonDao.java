package org.dragonfei.ffzl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by longfei on 16-4-9.
 */

public interface CommonDao {
    <T> List<T> select(String sql,List params);

}
