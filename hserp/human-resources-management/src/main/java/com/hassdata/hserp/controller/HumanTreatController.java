package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.TreatModel;
import com.hassdata.hserp.po.HumanDept;
import com.hassdata.hserp.po.HumanEmp;
import com.hassdata.hserp.po.HumanTreat;
import com.hassdata.hserp.service.HumanDeptService;
import com.hassdata.hserp.service.HumanEmpService;
import com.hassdata.hserp.service.HumanTreatService;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WTF
 * @DESCRIPTION 待遇管理
 * @create 2018-02-03 14:58
 **/
@Controller
@RequestMapping("system/human/api")
public class HumanTreatController {

    @Autowired
    private HumanTreatService humanTreatService;
    @Autowired
    private HumanDeptService humanDeptService;
    @Autowired
    private HumanEmpService humanEmpService;
    //获取储备人员列表
    @RequestMapping("getTreatList")
    @ResponseBody
    public ServerResponse  getTreatList(HumanTreat treat, @RequestParam(required = false, defaultValue = "1", value = "page") Integer fromIndex, @RequestParam(required = false,defaultValue = "10", value = "limit") Integer pageSize){
        List<HumanTreat> humanTreats = null;
        List<TreatModel> treatModels = null;
        long count = 0;
        try {
            if (!StringUtils.isEmpty(treat.getName())) {
                treat.setName("%" + treat.getName() + "%");
            }
            humanTreats = humanTreatService.listLikePage(treat, "id ASC", fromIndex, pageSize);
            if (humanTreats.size() > 0){
                List<HumanDept> humanDepts = (List<HumanDept>)humanDeptService.list(null, null);
                treatModels = new ArrayList<>();
                Integer index = 0;
                for (HumanTreat ht: humanTreats) {
                    index++;
                    treatModels.add(this.getTreatModel(ht, index, humanDepts));
                }
            }
            count = humanTreatService.countLike(treat);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccessForLayuiTable("成功!", treatModels, count);
    }

    //根据ID获取待遇信息
    @RequestMapping("getTreatById")
    @ResponseBody
    public ServerResponse  getTreatById(Integer id){
        if (id == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        HumanTreat treat = null;
        TreatModel treatModel = null;
        try {
            treat = humanTreatService.getById(id);
            List<HumanDept> humanDepts = (List<HumanDept>)humanDeptService.list(null, null);
            treatModel = this.getTreatModel(treat, 0, humanDepts);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccess(treatModel);
    }

    //更新待遇信息
    @RequestMapping("updateTreat")
    @ResponseBody
    public ServerResponse updateTreat(HumanTreat treat){
        if (treat.getId() == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            humanTreatService.update(treat);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("修改失败!");
        }
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

    //添加储备人员
    @RequestMapping("addTreat")
    @ResponseBody
    public ServerResponse addTreat(HumanTreat treat){
        try {
            //根据待遇姓名、工资、部门获取唯一职员信息
            HumanEmp emp = new HumanEmp();
            emp.setName(treat.getName());
            emp.setSalary(treat.getSalary());
            emp.setDeptId(treat.getDeptId());
            emp = humanEmpService.getOne(emp);
            if (emp == null){
                return  ServerResponse.createByErrorMessage("添加职员信息不存在!，请核实职员信息");
            }
            treat.setEid(emp.getId());
            humanTreatService.save(treat);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("添加失败!");
        }
        return ServerResponse.createBySuccessMessage("添加成功!");
    }
    //删除待遇信息
    @RequestMapping("deleteTreat")
    @ResponseBody
    public ServerResponse deleteTreat(Integer id){
        if (id == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            HumanTreat treat = humanTreatService.getById(id);
            if (treat == null) {
                return  ServerResponse.createByErrorMessage("删除待遇信息不存在!");
            }
            humanTreatService.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("删除失败!");
        }
        return ServerResponse.createBySuccessMessage("删除成功!");
    }

    //封装实体
    private TreatModel getTreatModel(HumanTreat emp, Integer index, List<HumanDept> humanDepts){
        TreatModel empModel = new TreatModel();
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
        empModel.setSalary(emp.getSalary());
        return empModel;
    }
}
