package com.hassdata.hserp.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope("prototype")
@Controller
@RequestMapping("system/administration")
public class ChapterViewController {

    /*公章管理首页*/
    @RequestMapping(value = "chapterList",method = RequestMethod.GET)
    public String chapterList(){
        return "chapterList";
    }

    /*公章管理修改页面*/
    @RequestMapping(value = "chapterEdit",method = RequestMethod.GET)
    public String chapterEdit(){
        return "chapterEdit";
    }

    /*公章管理添加页面*/
    @RequestMapping(value = "chapterAdd",method = RequestMethod.GET)
    public String chapterAdd(){
        return "reimbursement/chapterAdd";
    }



    /*公章管理页面*/
    @RequestMapping(value = "reimbursementList", method = RequestMethod.GET)
    public String reimbursementList(){
        return "reimbursement/reimbursementList";
    }

    /*公章管理修改页面*/
    @RequestMapping(value = "reimbursementEdit", method = RequestMethod.GET)
    public String reimbursementEdit(){
        return "reimbursement/reimbursementEdit";
    }

    /*公章管理添加页面*/
    @RequestMapping(value = "reimbursementAdd", method = RequestMethod.GET)
    public String reimbursementAdd(){
        return "reimbursement/reimbursementAdd";
    }
}
