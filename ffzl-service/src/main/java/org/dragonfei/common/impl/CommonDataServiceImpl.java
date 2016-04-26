package org.dragonfei.common.impl;

import org.dragonfei.common.CommonDataService;
import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.support.ResourceUtils;
import org.dragonfei.ffzl.params.support.ServiceContext;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by longfei on 16-4-24.
 */
@Service
public class CommonDataServiceImpl implements CommonDataService {
    @Override
    public RecordSet execute(ParamWrap pw) {
        String servicename  = pw.getServicename();
        String[] paths = StringUtils.split(servicename,"_");
        String namespace = StringUtils.toCommaDelimitedString(paths,".");
        String reallyNamespace = namespace.substring(0,namespace.lastIndexOf("."));
        ServiceContext serviceContext = ResourceUtils.loadResource(reallyNamespace);

        return null;
    }
}
