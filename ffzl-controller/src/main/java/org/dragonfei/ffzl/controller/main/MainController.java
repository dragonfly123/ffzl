package org.dragonfei.ffzl.controller.main;

import org.dragonfei.common.CommonService;
import org.dragonfei.ffzl.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by longfei on 16-4-9.
 * this is a controller redirect to main page
 */

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired private CommonService commonService;
    @RequestMapping("/index")
    public String main(){
        return "main";
    }


    @ResponseBody
    @RequestMapping("/menu")
    public List<Map<String,String>> menu(){
        return commonService.select(new Menu());
    }
}
