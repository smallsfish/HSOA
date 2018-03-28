package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.StoreModel;
import com.hassdata.hserp.po.*;
import com.hassdata.hserp.service.HumanDeptService;
import com.hassdata.hserp.service.HumanEmpService;
import com.hassdata.hserp.service.HumanOutEmpService;
import com.hassdata.hserp.service.HumanStoreService;
import com.hassdata.hserp.utils.DateUtils;
import com.hassdata.hserp.utils.ServerResponse;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WTF
 * @DESCRIPTION 储备人员管理
 * @create 2018-02-03 12:09
 **/
@Controller
@Scope("prototype")
@RequestMapping("system/human/api")
public class HumanStoreController {

    @Autowired
    private HumanStoreService humanStoreService;
    @Autowired
    private HumanDeptService humanDeptService;
    @Autowired
    private HumanEmpService humanEmpService;
    @Autowired
    private HumanOutEmpService humanOutEmpService;

    //获取储备人员列表
    @RequestMapping(value = "getStoreList",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse  getStoreList(HumanStore store, @RequestParam(required = false, defaultValue = "1", value = "page") Integer fromIndex,@RequestParam(required = false,defaultValue = "10", value = "limit") Integer pageSize){
        List<HumanStore> humanStores = null;
        List<StoreModel> storeModels = null;
        long count = 0;
        try {
            if (!StringUtils.isEmpty(store.getName())) {
                store.setName("%" + store.getName() + "%");
            }
            List<HumanDept> humanDepts = humanDeptService.list(null, null);
            store.setStatus(0);//只显示实习员工
            humanStores = humanStoreService.listLikePage(store, "id ASC", fromIndex, pageSize);
            if (humanStores.size() > 0){
                Integer index = 0;
                storeModels = new ArrayList<>();
                for (HumanStore hs:  humanStores) {
                    index++;
                    storeModels.add(this.getStoreModel(hs, index, humanDepts));
                }
            }

            count = humanStoreService.countLike(store);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccessForLayuiTable("成功!", storeModels, count);
    }

    //根据ID获取储备人员
    @RequestMapping("getStoreById")
    @ResponseBody
    public ServerResponse  getStoreById(Integer id){
        if (id == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        HumanStore store = null;
        StoreModel storeModel = null;
        try {
            store = humanStoreService.getById(id);
            List<HumanDept> humanDepts = (List<HumanDept>)humanDeptService.list(null, null);
            storeModel = this.getStoreModel(store, 0, humanDepts);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccess(storeModel);
    }

    //更新储备人员信息
    @RequestMapping("updateStore")
    @ResponseBody
    public ServerResponse updateStore(HumanStore store) throws Exception{
        if (store.getId() == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        //表示储备人员已转正
        if (!StringUtils.isEmpty(store.getStatus())) {
            if (store.getStatus() == 1) {
                HumanEmp humanEmp = new HumanEmp();
                humanEmp.setName(store.getName());
                humanEmp.setHeadimage(store.getHeadimage());
                humanEmp.setIdCard(store.getIdCard());
                humanEmp.setSalary(store.getSalary());
                humanEmp.setEducation(store.getEducation());
                humanEmp.setSex(store.getSex());
                humanEmp.setDeptId(store.getDeptId());
                humanEmp.setRegulTime(new Date());
                humanEmp.setPracticeTime(store.getPracticeTime());

                humanEmpService.save(humanEmp);
            }
        }
        try {
            humanStoreService.update(store);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("修改失败!");
        }
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

    //添加储备人员
    @RequestMapping("addStore")
    @ResponseBody
    public ServerResponse addStore(HumanStore store, HttpServletRequest request){
        if (store.getName() == null || store.getSex() == null || store.getDeptId() == null || store.getIdCard() == null || store.getSalary() == null || store.getTel() == null || store.getPracticeTime() == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            //获取创建人ID
            AdminUser adminUser = (AdminUser)SecurityUtils.getSubject().getSession(true).getAttribute("CurrentAdminUser");
            ////////////////////////////
            ////////////////////////////
            ///////////////////////////
            if (store.getStatus() == null){
                store.setStatus(0);//0:实习 1:转正
            }
            HumanOutEmp hoe = new HumanOutEmp();
            hoe.setIdCard(store.getIdCard());
            hoe = humanOutEmpService.getOne(hoe);
            if (hoe != null){
                return ServerResponse.createByErrorMessage("身份证号已存在!");
            }
            if (adminUser != null && adminUser.getId() != null){
                store.setCreateBy(adminUser.getId());
            }
            humanStoreService.save(store);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("添加失败!");
        }
        return ServerResponse.createBySuccessMessage("添加成功!");
    }
    //删除储备人员信息
    @RequestMapping("deleteStore")
    @ResponseBody
    public ServerResponse deleteStore(Integer id){
        if (id == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            HumanStore store = humanStoreService.getById(id);
            if (store == null) {
                return  ServerResponse.createByErrorMessage("删除职员不存在!");
            }
            humanStoreService.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("删除失败!");
        }
        return ServerResponse.createBySuccessMessage("删除成功!");
    }

    //封装实体
    private StoreModel getStoreModel(HumanStore store, Integer index, List<HumanDept> humanDepts){
        StoreModel empModel = new StoreModel();
        empModel.setIndex(index);
        empModel.setId(store.getId());
        empModel.setCreateBy(store.getCreateBy());
        if (humanDepts.size() >0){
            for (HumanDept dept: humanDepts) {
                if (dept.getId() == store.getDeptId()){
                    empModel.setDeptName(dept.getName());
                    break;
                }
            }
        }
        empModel.setName(store.getName());
        empModel.setHeadimage(store.getHeadimage());
        empModel.setIdCard(store.getIdCard());
        empModel.setEducation(store.getEducation());
        empModel.setTel(store.getTel());
        empModel.setPracticeTime(DateUtils.dateFormatYMD(store.getPracticeTime()));//实习时间
        empModel.setSalary(store.getSalary());
        if (store.getSex() != null){
            if (store.getSex()){
                empModel.setSex("男");
            }else {
                empModel.setSex("女");
            }
        }
        if (store.getStatus() != null) {
            if (store.getStatus() == 1) {
                empModel.setStatus("已转正");
            } else if (store.getStatus() == 0) {
                empModel.setStatus("实习");
            }
        }
        return empModel;
    }
}
