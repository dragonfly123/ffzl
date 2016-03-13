package org.dragonfei.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by longfei on 16-3-5.
 */
@Controller
@RequestMapping("/home")
public class Home {
    @Autowired private UserDetailsManager userDetailsManager;
    @RequestMapping("index2")
    public String index(){
        return "test2";
    }
    @RequestMapping(value = "changepassword",method = RequestMethod.GET)
    public String changepassword(){
        return "changepassword";
    }
    @RequestMapping(value = "changepassword",method = RequestMethod.POST)
    public String submitChangePasswordPage(@RequestParam("oldpassword") String oldPassword,
    @RequestParam("password") String newpassword){
        userDetailsManager.changePassword(oldPassword,newpassword);
        SecurityContextHolder.clearContext();
        return "redirect:/home/login.do";
    }
}
