package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.EmpModel;
import com.hassdata.hserp.po.*;
import com.hassdata.hserp.service.*;
import com.hassdata.hserp.utils.DateUtils;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WTF
 * @DESCRIPTION 职员管理
 * @create 2018-02-03 14:58
 **/
@Controller
@RequestMapping("system/human/api")
public class HumanEmpController {

    @Autowired
    private HumanEmpService humanEmpService;
    @Autowired
    private HumanDeptService humanDeptService;
    @Autowired
    private HumanOutEmpService humanOutEmpService;
    @Autowired
    private HumanTreatService humanTreatService;
    @Autowired
    private HumanAddressBookService humanAddressBookService;

    //获取职员列表
    @RequestMapping("getEmpList")
    @ResponseBody
    public ServerResponse  getEmpList(HumanEmp emp, @RequestParam(required = false, defaultValue = "1", value = "page") Integer fromIndex, @RequestParam(required = false,defaultValue = "10", value = "limit") Integer pageSize){
        List<HumanEmp> humanEmps = null;
        List<EmpModel> empModels = null;
        long count = 0;
        try {
            if (!StringUtils.isEmpty(emp.getName())) {
                emp.setName("%" + emp.getName() + "%");
            }
            emp.setStatus(1);
            humanEmps = humanEmpService.listLikePage(emp, "id ASC", fromIndex, pageSize);
            if (humanEmps.size() > 0){
                Integer index = 0;
                List<HumanDept> humanDepts = (List<HumanDept>)humanDeptService.list(null, null);
                empModels = new ArrayList<>();
                for (HumanEmp he: humanEmps) {
                    if (he.getStatus() != null && he.getStatus() == 1) {
                        index++;
                    }
                    //封装雇员信息
                    EmpModel empModel = this.getEmpModel(he, index, humanDepts);
                    empModels.add(empModel);
                }
            }
            count = humanEmpService.countLike(emp);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccessForLayuiTable("成功!", empModels, count);
    }

    //根据ID获取职员信息
    @RequestMapping("getEmpById")
    @ResponseBody
    public ServerResponse  getEmpById(Integer id){
        if (id == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        HumanEmp emp = null;
        EmpModel empModel = null;
        try {
            emp = humanEmpService.getById(id);
            List<HumanDept> humanDepts = (List<HumanDept>)humanDeptService.list(null, null);
            empModel = this.getEmpModel(emp, 1, humanDepts);

        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccess(empModel);
    }

    //更新职员信息
    @RequestMapping("updateEmp")
    @ResponseBody
    public ServerResponse updateEmp(HumanEmp emp){
        if (emp.getId() == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            humanEmpService.update(emp);

            //级联更新员工待遇
            HumanTreat treat = new HumanTreat();
            treat.setEid(emp.getId());
            treat = humanTreatService.getOne(treat);
            if (treat != null) {
                treat.setDeptId(emp.getDeptId());
                treat.setName(emp.getName());
                humanTreatService.update(treat);
            }
            //级联更新通讯录信息
            HumanAddressBook book = new HumanAddressBook();
            book.setEid(emp.getId());
            book = humanAddressBookService.getOne(book);
            if (book != null){
                book.setName(emp.getName());
                book.setTel(emp.getTel());
                book.setDeptId(emp.getDeptId());
                book.setSex(emp.getSex());
                humanAddressBookService.update(book);
            }
         if (!StringUtils.isEmpty(emp.getStatus())) {
                if (emp.getStatus() == 0){
                    ///////////////////////////
                    HumanOutEmp outEmp = new HumanOutEmp();
                    outEmp.setDeptId(emp.getDeptId());
                    outEmp.setEid(emp.getId());
                    outEmp.setSex(emp.getSex());
                    outEmp.setName(emp.getName());
                    outEmp.setTel(emp.getTel());
                    outEmp.setIdCard(emp.getIdCard());
                    outEmp.setSalary(emp.getSalary());
                    outEmp.setEducation(emp.getEducation());
                    outEmp.setOutTime(new Date());
                    humanOutEmpService.save(outEmp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("修改失败!");
        }
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

    //添加职员信息
    @RequestMapping("addEmp")
    @ResponseBody
    public ServerResponse addEmp(HumanEmp emp){
        if (emp.getName() == null || emp.getSex()==null || emp.getDeptId() == null || emp.getIdCard() == null || emp.getTel() == null || emp.getPracticeTime() == null || emp.getRegulTime() == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            if (emp.getSalary() == null){
                emp.setSalary(new BigDecimal(0));
            }
            HumanEmp he = new HumanEmp();
            he.setIdCard(emp.getIdCard());
            he = humanEmpService.getOne(he);
            if (he != null){
                return ServerResponse.createByErrorMessage("身份证号已存在!");
            }
            if (emp.getStatus() == null) {
                emp.setStatus(1);//0:离职 1:在职(默认)
            }
            humanEmpService.save(emp);

            // 级联添加员工薪资待遇
            HumanTreat treat = new HumanTreat();
            treat.setEid(emp.getId());
            treat.setName(emp.getName());
            treat.setDeptId(emp.getDeptId());
            humanTreatService.save(treat);

            //级联添加通讯录信息
            HumanAddressBook book = new HumanAddressBook();
            book.setEid(emp.getId());
            book.setDeptId(emp.getDeptId());
            book.setName(emp.getName());
            book.setSex(emp.getSex());
            book.setTel(emp.getTel());
            humanAddressBookService.save(book);
            //返回ID
            HumanEmp humanEmp = humanEmpService.getOne(emp);
            return ServerResponse.createBySuccessMessage("" + humanEmp.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("添加失败!");
        }

    }

    //删除职员信息
    @RequestMapping("deleteEmp")
    @ResponseBody
    public ServerResponse deleteEmp(Integer id){
        if (id == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            HumanEmp emp = humanEmpService.getById(id);
            if (emp == null) {
                return  ServerResponse.createByErrorMessage("删除职员不存在!");
            }
            emp.setStatus(0);//删除改为离职
            humanEmpService.update(emp);
            //级联删除员工待遇
            /*HumanTreat treat = new HumanTreat();
            treat.setEid(id);
            treat = humanTreatService.getOne(treat);
            if (treat != null){
                humanTreatService.delById(treat.getId());
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("删除失败!");
        }
        return ServerResponse.createBySuccessMessage("删除成功!");
    }

    //封装实体
    private EmpModel getEmpModel(HumanEmp emp, Integer index, List<HumanDept> humanDepts){
        EmpModel empModel = new EmpModel();
        empModel.setIndex(index);
        empModel.setId(emp.getId());
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
        empModel.setTel(emp.getTel());
        empModel.setPracticeTime(DateUtils.dateFormatYMD(emp.getPracticeTime()));//实习时间
        empModel.setRegulTime(DateUtils.dateFormatYMD(emp.getRegulTime()));//转正时间
        empModel.setSalary(emp.getSalary());
        if (emp.getSex()){
            empModel.setSex("男");
        }else {
            empModel.setSex("女");
        }
        if (emp.getStatus() != null){
            if (emp.getStatus() == 1){
                empModel.setStatus("在职");
            }
        }
        return empModel;
    }

}
