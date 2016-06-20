package org.dragonfei.ffzl.params.sql.query;

import org.dragonfei.ffzl.params.sql.query.operation.FfzlSqlQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.SqlParameter;

import javax.sql.DataSource;
import java.sql.JDBCType;
import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-6-18.
 */
public class SimpleSqlQuery extends FfzlSqlQuery {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public SimpleSqlQuery(DataSource ds, String sql,List<Map<String,String>> outputs){
        super(ds,sql,outputs);
        logger.info("execute query:{}",sql);

    }
    private boolean haveSetType = false;
    @Override
    public void setType(List parameters){
        if(!haveSetType) {
            synchronized (this) {
                if (!haveSetType) {
                    int[] types = new int[parameters.size()];
                    for (int i = 0; i < parameters.size(); i++) {
                        types[i] = JDBCType.VARCHAR.getVendorTypeNumber();
                    }
                    if (isCompiled()) {
                        throw new InvalidDataAccessApiUsageException("Cannot add parameters once query is compiled");
                    }
                    if (types != null) {
                        for (int type : types) {
                            declareParameter(new SqlParameter(type));
                        }
                    }
                    haveSetType =  true;
                }
            }
        }
    }
}
