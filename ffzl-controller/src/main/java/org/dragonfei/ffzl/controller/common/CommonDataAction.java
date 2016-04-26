package org.dragonfei.ffzl.controller.common;

import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by longfei on 16-4-24.
 */
@Controller
@RequestMapping("common")
public class CommonDataAction {

    @RequestMapping("execute")
    public RecordSet execute(HttpServletRequest request, HttpServletResponse response, ParamWrap pw){
        String servicename = pw.getServicename();
        return null;
    }
}
