package com.hassdata.hserp.controller.performance;

import com.hassdata.hserp.po.Assessment;
import com.hassdata.hserp.po.HumanEmp;
import com.hassdata.hserp.service.AssessmentService;
import com.hassdata.hserp.service.HumanEmpService;
import com.hassdata.hserp.utils.ServerResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("system/performance")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;
    @Autowired
    private HumanEmpService humanEmpService;

    //获取评论列表
    @RequestMapping("getAssessmentList")
    public @ResponseBody
    ServerResponse getAssessmentLsit(Assessment assessment,Integer page, Integer limit,String orerBy) {

        if(StringUtils.isNotEmpty(assessment.getUserName())){
            assessment.setUserName("%" + assessment.getUserName() + "%");
        }else {
            assessment.setUserName("%%");
        }

        List<Assessment> assessmentList = assessmentService.listLikePage(assessment,orerBy,page,limit);
        Long totalCount = assessmentService.countLike(assessment);

        for (Assessment a:assessmentList) {
            HumanEmp humanEmp = humanEmpService.getById(a.getUserId());
            a.setUserName(humanEmp.getName());
        }
        return ServerResponse.createBySuccessForLayuiTable("",assessmentList,totalCount);
    }

    //根据评论Id获取评论
    @RequestMapping("getAssessment")
    public @ResponseBody
    ServerResponse getAssessmentById(Integer id) {

        if(null == id || id == 0){
            return ServerResponse.createByErrorMessage("必填项不得为空");
        }

        Assessment assessment = (Assessment) assessmentService.getById(id);
        HumanEmp humanEmp = humanEmpService.getById(assessment.getUserId());
        assessment.setUserName(humanEmp.getName());

        return ServerResponse.createBySuccess(assessment);
    }

    //添加评论
    @RequestMapping("addAssessment")
    public @ResponseBody
    ServerResponse addAssessment(Assessment assessment) {

        if(null == assessment.getUserId() || assessment.getUserId() == 0){
            return ServerResponse.createByErrorMessage("必填项不得为空");
        }

        HumanEmp humanEmp = humanEmpService.getById(assessment.getUserId());
        assessment.setUserName(humanEmp.getName());

       assessmentService.save(assessment);

        return ServerResponse.createBySuccess(assessment);
    }

    //修改评论
    @RequestMapping("updateAssessment")
    public @ResponseBody
    ServerResponse updateAssessment(Assessment assessment) {

//        System.out.println(assessment.getUserName());
        if(null == assessment.getId() || assessment.getId() == 0){
            return ServerResponse.createByErrorMessage("必填项不得为空");
        }

        Assessment assessment1 = (Assessment) assessmentService.getById(assessment.getId());

        HumanEmp humanEmp = humanEmpService.getById(assessment1.getUserId());

        assessment.setUserName(humanEmp.getName());

        assessmentService.update(assessment);

        return ServerResponse.createBySuccess(assessment);
    }

    //修改评论根据userId
    @RequestMapping("updateAssessmentByUserId")
    public @ResponseBody
    ServerResponse updateAssessmentByUserId(Assessment assessment) {

//        System.out.println(assessment.getUserName());
        if(null == assessment.getUserId() || assessment.getUserId() == 0){
            return ServerResponse.createByErrorMessage("必填项不得为空");
        }

        assessment = (Assessment) assessmentService.getOne(assessment);

        HumanEmp humanEmp = humanEmpService.getById(assessment.getUserId());
        assessment.setUserName(humanEmp.getName());

        assessmentService.update(assessment);

        return ServerResponse.createBySuccess(assessment);
    }

    //删除评论
    @RequestMapping("deleteAssessment")
    public @ResponseBody
    ServerResponse deleteAssessment(Integer id) {

        if(null == id || id == 0){
            return ServerResponse.createByErrorMessage("必填项不得为空");
        }

        assessmentService.delById(id);

        return ServerResponse.createBySuccess();
    }
}
