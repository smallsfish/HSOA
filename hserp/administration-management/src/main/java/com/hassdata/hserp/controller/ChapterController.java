package com.hassdata.hserp.controller;

import com.hassdata.hserp.po.AdministrativeChapter;
import com.hassdata.hserp.service.chapter.ChapterService;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 公章管理
 */
@Controller
@Scope("prototype")
@RequestMapping("system/administration/api")
@ResponseBody
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    /*公章管理列表*/
    @RequestMapping(value = "chapterList",method = RequestMethod.GET)
    public ServerResponse chapterList(AdministrativeChapter administrativeChapter, Integer page, Integer limit, String orderBy){
        if (page == null || limit == null){
            return ServerResponse.createByErrorMessage("请求失败");
        }

        /*模糊查询*/
        if (administrativeChapter.getUser() != null){
            administrativeChapter.setUser("%" + administrativeChapter.getUser() + "%");
        }
        List<AdministrativeChapter> chapters = null;
        chapters = chapterService.listLikePage(administrativeChapter,"id desc", page, limit);

        long count=chapterService.countLike(administrativeChapter);
        return ServerResponse.createBySuccessForLayuiTable("请求成功",chapters,count);
    }

    /*删除*/
    @RequestMapping(value = "chapterDelete", method = RequestMethod.POST)
    public ServerResponse chapterDelete(Integer id){
        if(null == id || id == 0){
            return ServerResponse.createByErrorMessage("参数不得为空");
        }
        try {
            chapterService.delById(id);
        } catch (Exception e){
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("删除失败!");
        }
        return ServerResponse.createBySuccessMessage("删除成功!");
    }


    /*id查询*/
    @RequestMapping(value = "chapterEditById", method = RequestMethod.POST)
    public ServerResponse chapterEditById(Integer id){
        if(null == id || id == 0){
            return ServerResponse.createByErrorMessage("必填项不得为空");
        }

        AdministrativeChapter chapters = (AdministrativeChapter) chapterService.getById(id);

        return ServerResponse.createBySuccess(chapters);
    }


    /*保存修改*/
    @RequestMapping(value = "chapterSave", method = RequestMethod.POST)
    public ServerResponse chapterSave(AdministrativeChapter administrativeChapter){
        if (administrativeChapter.getId() == null) {
            return ServerResponse.createByErrorMessage("必须参数不为空!");
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            administrativeChapter.setUsetime(df.format(new Date()));
            chapterService.update(administrativeChapter);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("修改失败!");
        }
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

    /*添加*/
    @RequestMapping(value = "chapterAdd", method = RequestMethod.POST)
    public ServerResponse chapterAdd(AdministrativeChapter administrativeChapter){
        if (administrativeChapter.getUser() == null && administrativeChapter.getUsedetails() == null) {
            return ServerResponse.createByErrorMessage("参数不能为空!");
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            administrativeChapter.setUsetime(df.format(new Date()));
            chapterService.save(administrativeChapter);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("新增失败!");
        }
        return ServerResponse.createBySuccessMessage("新增成功!");
    }

}
