package org.dragonfei.ffzl.params.sql;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.SqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by longfei on 16-5-2.
 */
public class FfzlSqlQueryTotal extends SqlQuery<Integer> {

    public FfzlSqlQueryTotal(DataSource dataSource,String sql){
        super(dataSource,sql);
    }
    @Override
    protected RowMapper<Integer> newRowMapper(Object[] parameters, Map<?, ?> context) {
        return (rs, rowNum)->rs.getInt(1);
    }
}
