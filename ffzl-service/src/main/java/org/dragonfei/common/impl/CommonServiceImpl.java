package org.dragonfei.common.impl;

import org.dragonfei.common.CommonMetaDataService;
import org.dragonfei.common.CommonService;
import org.dragonfei.ffzl.annotation.domain.SqlParam;
import org.dragonfei.ffzl.annotation.parse.MetaData;
import org.dragonfei.ffzl.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-4-10.
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired private CommonMetaDataService commonMetaDataService;

    @Autowired private CommonDao commonDao;
    @Override
    public <T> void insert(T object) {
        MetaData metaData = commonMetaDataService.getMetaData(object.getClass());

    }

    public <T>List<Map<String,String>> select(T object){
        MetaData  metaData = commonMetaDataService.getMetaData(object.getClass());
        SqlParam sqlParam = commonMetaDataService.getSqlParam(object,metaData, CommonMetaDataService.SqlType.SELECT);
        return commonDao.select(sqlParam.sql,sqlParam.params);
    }
}
