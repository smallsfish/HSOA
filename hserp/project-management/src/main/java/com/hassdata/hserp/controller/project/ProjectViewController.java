package com.hassdata.hserp.controller.project;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("prototype")
@RequestMapping("system/project")
public class ProjectViewController {

    @RequestMapping(value = "getView",method = RequestMethod.GET)
    public String getIndex(String viewPage, @RequestParam(required = false) String ki, ModelMap map){
        if(ki!=null)
        map.addAttribute("ki",ki);
        return viewPage;
    }

}
