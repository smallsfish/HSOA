package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.OutEmpModel;
import com.hassdata.hserp.po.HumanDept;
import com.hassdata.hserp.po.HumanEmp;
import com.hassdata.hserp.po.HumanOutEmp;
import com.hassdata.hserp.service.HumanDeptService;
import com.hassdata.hserp.service.HumanEmpService;
import com.hassdata.hserp.service.HumanOutEmpService;
import com.hassdata.hserp.utils.DateUtils;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WTF
 * @DESCRIPTION 离职人员管理
 * @create 2018-02-03 14:58
 **/
@Controller
@RequestMapping("system/human/api")
public class HumanOutEmpController {

    @Autowired
    private HumanOutEmpService humanOutEmpService;
    @Autowired
    private HumanEmpService humanEmpService;
    @Autowired
    private HumanDeptService humanDeptService;
    //获取离职人员列表
    @RequestMapping("getOutEmpList")
    @ResponseBody
    public ServerResponse  getOutEmpList(HumanOutEmp outEmp, @RequestParam(required = false, defaultValue = "1", value = "page") Integer fromIndex, @RequestParam(required = false,defaultValue = "10", value = "limit") Integer pageSize){
        List<HumanOutEmp> humanOutEmps = null;
        List<OutEmpModel> outEmpModels = null;
        long count = 0;
        try {
            if (!StringUtils.isEmpty(outEmp.getName())) {
                outEmp.setName("%" + outEmp.getName() + "%");
            }
            humanOutEmps = humanOutEmpService.listLikePage(outEmp, "id ASC", fromIndex, pageSize);
            if (humanOutEmps.size() > 0){
                List<HumanDept> humanDepts = (List<HumanDept>)humanDeptService.list(null, null);
                outEmpModels = new ArrayList<>();
                Integer index = 0;
                for (HumanOutEmp he: humanOutEmps) {
                    index++;
                    OutEmpModel outEmpModel = this.getOutEmpModel(he, index, humanDepts);
                    outEmpModels.add(outEmpModel);
                }
            }
            count = humanOutEmpService.countLike(outEmp);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccessForLayuiTable("成功!", outEmpModels, count);
    }

    //根据ID获取离职人员信息
    @RequestMapping("getOutEmpById")
    @ResponseBody
    public ServerResponse  getOutEmpById(Integer id){
        if (id == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        HumanOutEmp outEmp = null;
        OutEmpModel outEmpModel = null;
        try {
            outEmp = humanOutEmpService.getById(id);
            List<HumanDept> humanDepts = (List<HumanDept>) humanDeptService.list(null, null);
            if (outEmp != null) {
                outEmpModel = this.getOutEmpModel(outEmp, 0, humanDepts);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccess(outEmpModel);
    }

    //更新离职人员信息
    @RequestMapping("updateOutEmp")
    @ResponseBody
    public ServerResponse updateOutEmp(HumanOutEmp outEmp){
        if (outEmp.getId() == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            humanOutEmpService.update(outEmp);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("修改失败!");
        }
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

    //添加离职人员
    @RequestMapping("addOutEmp")
    @ResponseBody
    public ServerResponse addOutEmp(HumanOutEmp outEmp){
        if (outEmp.getName() == null || outEmp.getSex()==null || outEmp.getDeptId() == null || outEmp.getIdCard() == null || outEmp.getSalary() == null || outEmp.getTel() == null ||  outEmp.getOutTime() == null || outEmp.getReason() == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            //根据离职人员姓名、身份证获取唯一职员信息
            HumanEmp emp = new HumanEmp();
            emp.setName(outEmp.getName());
            emp.setIdCard(emp.getIdCard());
            emp = humanEmpService.getOne(emp);
            if (emp == null){
                return  ServerResponse.createByErrorMessage("添加职员信息不存在!，请核实职员信息");
            }
            HumanOutEmp hoe = new HumanOutEmp();
            hoe.setIdCard(outEmp.getIdCard());
            hoe = humanOutEmpService.getOne(hoe);
            if (hoe != null){
                return ServerResponse.createByErrorMessage("身份证号已存在!");
            }
            outEmp.setEid(emp.getId());
            humanOutEmpService.save(outEmp);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("添加失败!");
        }
        return ServerResponse.createBySuccessMessage("添加成功!");
    }
    //删除离职人员信息
    @RequestMapping("deleteOutEmp")
    @ResponseBody
    public ServerResponse deleteOutEmp(Integer id){
        if (id == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            HumanOutEmp outEmp = humanOutEmpService.getById(id);
            if (outEmp == null) {
                return  ServerResponse.createByErrorMessage("删除职员不存在!");
            }
            humanOutEmpService.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("删除失败!");
        }
        return ServerResponse.createBySuccessMessage("删除成功!");
    }
    //封装实体
    private OutEmpModel getOutEmpModel(HumanOutEmp emp, Integer index, List<HumanDept> humanDepts){
        OutEmpModel empModel = new OutEmpModel();
        empModel.setIndex(index);
        empModel.setId(emp.getId());
        empModel.setEid(emp.getEid());
        if (humanDepts.size() >0){
            for (HumanDept dept: humanDepts) {
                if (dept.getId() == emp.getDeptId()){
                    empModel.setDeptName(dept.getName());
                    break;
                }
            }
        }
        empModel.setName(emp.getName());
        empModel.setHeadimage(emp.getHeadimage());
        empModel.setIdCard(emp.getIdCard());
        empModel.setEducation(emp.getEducation());
        empModel.setReason(emp.getReason());
        empModel.setTel(emp.getTel());
        empModel.setSalary(emp.getSalary());
        if (emp.getOutTime() != null) {
            empModel.setOutTime(DateUtils.dateFormatYMD(emp.getOutTime()));
        }
        if (emp.getSex() != null){
            if (emp.getSex()){
                empModel.setSex("男");
            }else {
                empModel.setSex("女");
            }
        }

        return empModel;
    }
}
