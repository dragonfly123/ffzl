package org.dragonfei.common.impl;

import org.dragonfei.common.CommonDataService;
import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.sql.SqlBuilder;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *Created by longfei on 16-4-24.
 */
@Service
public class CommonDataServiceImpl implements CommonDataService {
    @Override
    public RecordSet execute(ParamWrap pw) {
        /*String servicename  = pw.getServicename();
        String[] paths = StringUtils.split(servicename,"_");
        String namespace = StringUtils.toCommaDelimitedString(paths,".");
        String reallyNamespace = namespace.substring(0,namespace.lastIndexOf("."));
        ServiceContext serviceContext = ResourceLoader.load(reallyNamespace);

        List<Map<String,String>> inputs = getInputs(serviceContext,servicename);
        List<Map<String,String>> outputs = getOutputs(serviceContext,servicename);
        String orderBy =  getOrderBy(serviceContext,servicename);
        Map<String,?> sqlSource = getSqlSource(serviceContext,servicename);
        SqlBuilder.instance().buildInputs(inputs).buildOutputs(outputs).
                buildOrderBy(orderBy).buildSqlMap(sqlSource).build(pw);*/
        return null;
    }


}
