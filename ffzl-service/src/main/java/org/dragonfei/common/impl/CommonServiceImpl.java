package org.dragonfei.common.impl;

import org.dragonfei.common.CommonMetaDataService;
import org.dragonfei.common.CommonService;
import org.dragonfei.ffzl.annotation.parse.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by longfei on 16-4-10.
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired private CommonMetaDataService commonMetaDataService;
    @Override
    public <T> void insert(T object) {
        MetaData metaData = commonMetaDataService.getMetaData(object);
    }
}
