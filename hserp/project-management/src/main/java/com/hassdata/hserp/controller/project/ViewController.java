package com.hassdata.hserp.controller.project;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("prototype")
@RequestMapping("system/project")
public class ViewController {

    @RequestMapping(value = "getView",method = RequestMethod.GET)
    public String getIndex(String viewPage){
        System.out.println(viewPage);
        return viewPage;
    }

}
