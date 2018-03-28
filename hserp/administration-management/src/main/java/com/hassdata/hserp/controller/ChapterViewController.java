package com.hassdata.hserp.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope("prototype")
@Controller
@RequestMapping("system/administration")
public class ChapterViewController {

    //考勤管理
    @RequestMapping(value = "attendanceList", method = RequestMethod.GET)
    public String attendanceList(){
        return "attendance/attendanceList";
    }
    @RequestMapping(value = "attendanceEdit", method = RequestMethod.GET)
    public String attendanceEdit(){
        return "attendance/attendanceEdit";
    }


    /*报销管理*/
    @RequestMapping(value = "cachetList", method = RequestMethod.GET)
    public String cachetList(){
        return "cachet/cachetList";
    }
    @RequestMapping(value = "cachetAdd", method = RequestMethod.GET)
    public String cachetAdd(){
        return "cachet/cachetAdd";
    }
    @RequestMapping(value = "cachetEdit", method = RequestMethod.GET)
    public String cachetEdit(){
        return "cachet/cachetEdit";
    }

    /*注册资料管理*/
    @RequestMapping(value = "meansList", method = RequestMethod.GET)
    public String meansList(){
        return "means/meansList";
    }
    @RequestMapping(value = "meansEdit", method = RequestMethod.GET)
    public String meansEdit(){
        return "means/meansEdit";
    }
    @RequestMapping(value = "meansAdd", method = RequestMethod.GET)
    public String meansAdd(){
        return "means/meansAdd";
    }

    /*会议管理*/
    @RequestMapping(value = "meetList", method = RequestMethod.GET)
    public String meetList(){
        return "meet/meetList";
    }
    @RequestMapping(value = "meetAdd", method = RequestMethod.GET)
    public String meetAdd(){
        return "meet/meetAdd";
    }
    @RequestMapping(value = "meetEdit", method = RequestMethod.GET)
    public String meetEdit(){
        return "meet/meetEdit";
    }




    @RequestMapping(value = "chapterList",method = RequestMethod.GET)
    public String chapterList(){
        return "chapter/chapterList";
    }


    @RequestMapping(value = "chapterEdit",method = RequestMethod.GET)
    public String chapterEdit(){
        return "chapter/chapterEdit";
    }


    @RequestMapping(value = "chapterAdd",method = RequestMethod.GET)
    public String chapterAdd(){
        return "chapter/chapterAdd";
    }



    //公章管理页面
    @RequestMapping(value = "reimbursementList", method = RequestMethod.GET)
    public String reimbursementList(){
        return "reimbursement/reimbursementList";
    }

    //公章管理修改页面
    @RequestMapping(value = "reimbursementEdit", method = RequestMethod.GET)
    public String reimbursementEdit(){
        return "reimbursement/reimbursementEdit";
    }

    //公章管理添加页面
    @RequestMapping(value = "reimbursementAdd", method = RequestMethod.GET)
    public String reimbursementAdd(){
        return "reimbursement/reimbursementAdd";
    }
}
