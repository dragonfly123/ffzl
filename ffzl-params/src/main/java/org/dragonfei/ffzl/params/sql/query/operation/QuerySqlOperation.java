package org.dragonfei.ffzl.params.sql.query.operation;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.service.QueryDataService;
import org.dragonfei.ffzl.params.sql.common.inter.SqlOperation;
import org.dragonfei.ffzl.params.sql.condition.ParameterEntry;
import org.dragonfei.ffzl.params.sql.query.entry.QueryOperationEntry;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-5-2.
 * sql语句与参数包装
 */
public class QuerySqlOperation implements QueryDataService,SqlOperation {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<ParameterEntry> params = Lists.newArrayList();
    private QueryOperationEntry entry;

    public QuerySqlOperation(QueryOperationEntry entry){
        this.entry = entry;
    }
    public void setParams(List<ParameterEntry> params) {
        this.params = params;
    }

    public List<Map<String,?>> executeQuery(ParamWrap pw){
        List list = caculateParams(pw);
        int page = pw.getPage();
        int pageSize = pw.getPageSize();
        list.add((page-1)*pageSize);
        list.add(pageSize);
        this.entry.ffzlSqlQuery.setType(list);
        logger.info("page param {}",list.toString());
        return this.entry.ffzlSqlQuery.execute(list.toArray());
    }

    public Integer executeTotal(ParamWrap pw){
        List list = caculateParams(pw);
        this.entry.ffzlSqlQueryTotal.setType(list);
        logger.info("total param {}",list.toString());
        return this.entry.ffzlSqlQueryTotal.findObject(list.toArray());
    }

    private List<String> caculateParams(ParamWrap pw){
        List<String> list = Lists.newArrayList(params.size());
        for(int i = 0; i < params.size();i++){
            ParameterEntry parameterEntry = params.get(i);
            list.add(parameterEntry.conditionSql.getParamValues(parameterEntry.prameterName,pw));
        }
        logger.info("parameter is {}",list);
        return list;
    }


}
