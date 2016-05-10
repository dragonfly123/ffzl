package org.dragonfei.ffzl.controller.main;

import org.dragonfei.common.CommonDataService;
import org.dragonfei.common.CommonService;
import org.dragonfei.common.impl.ServiceDelegate;
import org.dragonfei.ffzl.domain.Menu;
import org.dragonfei.ffzl.params.ParamUtils;
import org.dragonfei.ffzl.params.ParamWrap;
import org.dragonfei.ffzl.params.RecordSet;
import org.dragonfei.ffzl.params.resource.ServiceResource;
import org.dragonfei.ffzl.utils.collections.Lists;
import org.dragonfei.ffzl.utils.collections.Maps;
import org.dragonfei.ffzl.utils.objects.ObjectUtils;
import org.dragonfei.ffzl.utils.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired private ServiceDelegate serviceDelegate;
    @Autowired private CommonService commonService;
    @RequestMapping("/menu")
    public String main(@RequestParam Map<String,String> map, Model model){
        ParamWrap pw = new ParamWrap();
        pw.setServicename("ffzl_base_menu");
        RecordSet rs = (RecordSet) serviceDelegate.execute(pw);
        if(!ObjectUtils.isEmpty(rs.getData())){
            model.addAttribute("children",rs.getData().get(0));
        }
        model.addAttribute("menu",rs);
        return "main";
    }

    @RequestMapping("/menu/{menuId}")
    public String menu(@RequestParam Map<String,String> map, Model model, @PathVariable("menuId")String menuId){

        ParamWrap pw = new ParamWrap();
        pw.setServicename("ffzl_base_menu");
        RecordSet rs = (RecordSet) serviceDelegate.execute(pw);
        ServiceResource sr = ServiceResource.getServiceResource("ffzl_base_menu","treeservice","");
        String id = "id";
        if(sr.getResourceMap("menu")!= null){
            id = (String) sr.getResourceMap("menu").get("idKey");
        }
        if(!ObjectUtils.isEmpty(rs.getData())){
            for(Map<String,?> item:rs.getData()){
                if(StringUtils.equals((String)item.get(id),menuId)){
                    model.addAttribute("children",ObjectUtils.nvl(item.get("children"), Lists.newArrayList()));
                    break;
                }
            }
        }
        return "main";
    }


    @ResponseBody
    @RequestMapping("/menujson")
    public List<Map<String,String>> menu(){
        return commonService.select(new Menu());
    }

    @ResponseBody
    @RequestMapping("execute")
    public <T> T execute(HttpServletRequest request){
        ParamWrap pw = ParamUtils.buildParams(request);

        return (T)serviceDelegate.execute(pw);
    }
}
