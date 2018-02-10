package com.hassdata.hserp.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("prototype")
@RequestMapping("system")
public class IndexController {

    @RequestMapping(value = "getIndex",method = RequestMethod.GET)
    public String getIndex(){
        return "index";
    }
}
