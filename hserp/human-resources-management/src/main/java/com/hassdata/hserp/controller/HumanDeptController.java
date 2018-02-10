package com.hassdata.hserp.controller;

import com.hassdata.hserp.po.HumanDept;
import com.hassdata.hserp.service.HumanDeptService;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author WTF
 * @DESCRIPTION 部门管理
 * @create 2018-02-03 14:58
 **/
@Controller
@RequestMapping("system/human/api")
public class HumanDeptController {

    @Autowired
    private HumanDeptService humanDeptService;

    //获取部门分页列表
    @RequestMapping("getDeptListForPage")
    @ResponseBody
    public ServerResponse  getDeptListForPage(HumanDept dept, @RequestParam(required = false, defaultValue = "1", value = "page") Integer fromIndex, @RequestParam(required = false,defaultValue = "10", value = "limit") Integer pageSize){
        List<HumanDept> humanDepts = null;
        long count = 0;
        try {
            if (!StringUtils.isEmpty(dept.getName())) {
                dept.setName("%" + dept.getName() + "%");
            }
            humanDepts = humanDeptService.listLikePage(dept, "id ASC", fromIndex, pageSize);
            Integer index = 0;
            for (HumanDept hd: humanDepts) {
                hd.setIndex(++index);
            }
            count = humanDeptService.countLike(dept);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccessForLayuiTable("成功!", humanDepts, count);
    }
    //获取部门列表
    @RequestMapping("getDeptList")
    @ResponseBody
    public ServerResponse  getDeptList(HumanDept dept){
        List<HumanDept> humanDepts = null;
        long count = 0;
        try {
            humanDepts = (List<HumanDept>)humanDeptService.list(dept, null);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccess("成功!", humanDepts);
    }

    //根据ID获取部门信息
    @RequestMapping("getDeptById")
    @ResponseBody
    public ServerResponse  getDeptById(Integer id){
        if (id == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        HumanDept dept = null;
        try {
            dept = humanDeptService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccess(dept);
    }

    //更新部门信息
    @RequestMapping("updateDept")
    @ResponseBody
    public ServerResponse updateDept(HumanDept dept){
        if (dept.getId() == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            humanDeptService.update(dept);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("修改失败!");
        }
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

    //添加储备人员
    @RequestMapping("addDept")
    @ResponseBody
    public ServerResponse addDept(HumanDept dept){
        try {
            humanDeptService.save(dept);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("添加失败!");
        }
        return ServerResponse.createBySuccessMessage("添加成功!");
    }
    //删除部门信息
    @RequestMapping("deleteDept")
    @ResponseBody
    public ServerResponse deleteDept(Integer id){
        if (id == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            humanDeptService.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("删除失败!");
        }
        return ServerResponse.createBySuccessMessage("删除成功!");
    }
}
