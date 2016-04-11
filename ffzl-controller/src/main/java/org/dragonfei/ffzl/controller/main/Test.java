package org.dragonfei.ffzl.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by longfei on 16-4-9.
 */
@Controller
@RequestMapping("/test")
public class Test {

    @RequestMapping("/bb")
    public String  test(){
        return "test1";
    }
}
