package org.dragonfei.common.impl;

import org.dragonfei.common.CommonDataService;
import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.service.CommonRsEntry;
import org.dragonfei.ffzl.params.sql.SqlBuilder;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *Created by longfei on 16-4-24.
 */
@Service
public class CommonDataServiceImpl implements CommonDataService {
    @Autowired private CommonRsEntry commonRsEntry;
    @Override
    public RecordSet execute(ParamWrap pw) {
        return commonRsEntry.execute(pw);
    }


}
