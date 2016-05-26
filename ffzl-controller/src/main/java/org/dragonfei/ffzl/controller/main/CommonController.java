package org.dragonfei.ffzl.controller.main;

import com.alibaba.fastjson.JSON;
import org.dragonfei.common.impl.ServiceDelegate;
import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by longfei on 16-5-24.
 */
@Controller
public class CommonController {

    @Autowired ServiceDelegate serviceDelegate;

    @RequestMapping("/**/main.jsp")
    public Map<String,String> main(@RequestParam Map<String,String> map, Model model){
        ParamWrap pw = new ParamWrap();
        pw.setServicename("ffzl_base_menu");
        RecordSet rs = (RecordSet) serviceDelegate.execute(pw);
        String menuId = map.get("menuId");
        if(ObjectUtils.isEmpty(menuId)) {
            if (!ObjectUtils.isEmpty(rs.getData())) {
                model.addAttribute("children", JSON.toJSON(rs.getData().get(0)));
            }
        } else {
            ServiceResource sr = ServiceResource.getServiceResource("ffzl_base_menu", "treeservice", "");
            String id = "id";
            if (sr.getResourceMap("menu") != null) {
                id = (String) sr.getResourceMap("menu").get("idKey");
            }
            if (!ObjectUtils.isEmpty(rs.getData())) {
                for (Map<String, ?> item : rs.getData()) {
                    if (StringUtils.equals((String) item.get(id), menuId)) {
                        model.addAttribute("children", item);
                        break;
                    }
                }
            }
        }
        return map;
    }
}
