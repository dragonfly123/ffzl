package org.dragonfei.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by longfei on 16-3-5.
 */
@Controller
@RequestMapping("/home")
public class Home {
    @RequestMapping("index")
    public String index(){
        return "test2";
    }
}
