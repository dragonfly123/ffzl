package org.dragonfei.ffzl.controller.main;

import com.alibaba.fastjson.JSON;
import org.dragonfei.common.impl.ServiceDelegate;
import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by longfei on 16-5-24.
 */
@Controller
public class CommonController {

    @Autowired ServiceDelegate serviceDelegate;

    @RequestMapping("/**/main.jsp")
    public ModelAndView main(Model model){
        ParamWrap pw = new ParamWrap();
        pw.setServicename("ffzl_base_menu");
        RecordSet rs = (RecordSet) serviceDelegate.execute(pw);
        if(!ObjectUtils.isEmpty(rs.getData())){
            model.addAttribute("children",rs.getData().get(0));
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("menu", JSON.toJSON(rs.getData()));
        return mv;
    }
}
