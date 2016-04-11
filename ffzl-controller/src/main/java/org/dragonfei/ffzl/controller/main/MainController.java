package org.dragonfei.ffzl.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by longfei on 16-4-9.
 * this is a controller redirect to main page
 */

@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping("/index")
    public String main(){
        return "main";
    }
}
