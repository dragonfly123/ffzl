package org.dragonfei.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by longfei on 16-3-6.
 */
@Controller
@RequestMapping("/home")
public class LoginLogoutController {

    @RequestMapping(method = RequestMethod.GET,value = "/login.do")
    public String home(){
        return "login";
    }
}
