package org.dragonfei.ffzl.controller.main;

import org.dragonfei.common.CommonDataService;
import org.dragonfei.common.CommonService;
import org.dragonfei.ffzl.domain.Menu;
import org.dragonfei.ffzl.params.ParamUtils;
import org.dragonfei.ffzl.params.ParamWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-4-9.
 * this is a controller redirect to main page
 */

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired private CommonDataService commonDataService;
    @Autowired private CommonService commonService;
    @RequestMapping("/index")
    public ModelAndView main(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main");
        mv.addObject("menu",commonService.select(new Menu()));
        return mv;
    }


    @ResponseBody
    @RequestMapping("/menu")
    public List<Map<String,String>> menu(){
        return commonService.select(new Menu());
    }

    @ResponseBody
    @RequestMapping("execute")
    public <T> T execute(HttpServletRequest request){
        ParamWrap pw = ParamUtils.buildParams(request);

        return (T)commonDataService.execute(pw);
    }
}
