package com.hassdata.hserp.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("prototype")
@RequestMapping("performance")
public class ViewController {

    @RequestMapping(value = "evaluation",method = RequestMethod.GET)
    public String evaluation(){
        return "performance/evaluation";
    }

    @RequestMapping(value = "evaluationEdit",method = RequestMethod.GET)
    public String evaluationEdit(){
        return "performance/evaluationEdit";
    }
}
