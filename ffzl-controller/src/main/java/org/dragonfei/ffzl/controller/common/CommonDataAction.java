package org.dragonfei.ffzl.controller.common;

import org.dragonfei.common.impl.ServiceDelegate;
import org.dragonfei.ffzl.params.ParamUtils;
import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by longfei on 16-4-24.
 */
@Controller
@RequestMapping("common")
public class CommonDataAction {

    @Autowired ServiceDelegate serviceDelegate;
    @ResponseBody
    @RequestMapping("execute")
    public <T> T execute(HttpServletRequest request){
        ParamWrap pw = ParamUtils.buildParams(request);
        return (T)serviceDelegate.execute(pw);
    }

    @ResponseBody
    @RequestMapping("layout")
    public Map<String,?> layout(@RequestParam Map<String,String> map){
        ParamWrap pw  =  ParamUtils.buildParams(map);
        ServiceResource layoutSr = ServiceResource.getServiceResource(pw.getFullservicename(),"layout", StringUtils.BLANK);
        return layoutSr.getResourceMap(pw.getServicename());

    }
}
