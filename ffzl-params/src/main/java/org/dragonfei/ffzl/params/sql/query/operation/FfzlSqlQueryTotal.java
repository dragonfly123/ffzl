package org.dragonfei.ffzl.params.sql.query.operation;

import org.dragonfei.ffzl.params.sql.common.TypeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.SqlQuery;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-5-2.
 * 查询total的sql引
 */
public class FfzlSqlQueryTotal extends SqlQuery<Integer> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private boolean haveSetType = false;
    public FfzlSqlQueryTotal(DataSource dataSource,String sql){
        super(dataSource,sql);
        logger.info("total sql:{}",sql);
    }
    @Override
    protected RowMapper<Integer> newRowMapper(Object[] parameters, Map<?, ?> context) {
        return (rs, rowNum)->rs.getInt(1);
    }

    public void setType(List parameters){
        if(!haveSetType) {
            synchronized (this) {
                if (!haveSetType) {
                    super.setTypes(TypeGenerator.commonTypes(parameters));
                    haveSetType =  true;
                }
            }
        }
    }
}
