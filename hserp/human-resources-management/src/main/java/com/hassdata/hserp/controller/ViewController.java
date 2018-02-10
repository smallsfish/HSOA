package com.hassdata.hserp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author WTF
 * @DESCRIPTION 获取页面
 * @create 2018-02-03 11:26
 **/
@Controller
public class ViewController {

    @RequestMapping("human/store")
    public String store(){
        return "store/store";
    }
    @RequestMapping("human/addStore")
    public String addStore(){
        return "store/addStore";
    }
    @RequestMapping("human/updateStore")
    public String updateStore(){
        return "store/updateStore";
    }

    @RequestMapping("human/human")
    public String human(){
        return "human/human";
    }
    @RequestMapping("human/humanAdd")
    public String humanAdd(){
        return "human/humanAdd";
    }
    @RequestMapping("human/humanEdit")
    public String humanEdit(){
        return "human/humanEdit";
    }

    @RequestMapping("human/dept")
    public String dept(){
        return "human/dept";
    }
    @RequestMapping("human/deptAdd")
    public String deptAdd(){
        return "human/deptAdd";
    }
    @RequestMapping("human/deptEdit")
    public String deptEdit(){
        return "human/deptEdit";
    }

    @RequestMapping("reserve/reserve")
    public String reserve(){
        return "reserve/reserve";
    }
    @RequestMapping("reserve/reserveAdd")
    public String reserveAdd(){
        return "reserve/reserveAdd";
    }
    @RequestMapping("reserve/reserveEdit")
    public String reserveEdit(){
        return "reserve/reserveEdit";
    }

    @RequestMapping("out/outList")
    public String outList(){
        return "out/outList";
    }
    @RequestMapping("out/outAdd")
    public String outAdd(){ return "out/outAdd";}
    @RequestMapping("out/outEdit")
    public String outEdit(){
        return "out/outEdit";
    }

    @RequestMapping("mail/mailList")
    public String mailList(){ return "mail/mailList"; }
    @RequestMapping("mail/mailAdd")
    public String mailAdd(){ return "mail/mailAdd";}
    @RequestMapping("mail/mailEdit")
    public String mailEdit(){
        return "mail/mailEdit";
    }

    @RequestMapping("treat/treatList")
    public String treatList(){ return "treat/treatList"; }
    @RequestMapping("treat/treatAdd")
    public String treatAdd(){ return "treat/treatAdd";}
    @RequestMapping("treat/treatEdit")
    public String treatEdit(){ return "treat/treatEdit";}


    @RequestMapping("system/index")
    public String index(){
        return "index";
    }
}
