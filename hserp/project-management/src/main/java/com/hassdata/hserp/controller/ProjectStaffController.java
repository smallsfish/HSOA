package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.ProjectStaffDTO;
//import com.hassdata.hserp.po.HumanEmp;
import com.hassdata.hserp.po.HumanEmp;
import com.hassdata.hserp.po.ProjectStaff;
//import com.hassdata.hserp.service.HumanEmpService;
import com.hassdata.hserp.service.HumanEmpService;
import com.hassdata.hserp.service.ProjectStaffService;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("system/project/api")
@ResponseBody
public class ProjectStaffController {

    @Resource
    private ProjectStaffService projectStaffService;

    @Resource
    private HumanEmpService humanEmpService;

    @RequestMapping(value = "addProjectStaff",method = RequestMethod.POST)
    public ServerResponse addProjectProjress(ProjectStaff projectStaff){
        if(projectStaff.getSid()==null){
            return ServerResponse.createByErrorMessage("请选择参与人员");
        }
        ProjectStaff p=new ProjectStaff();
        p.setSid(projectStaff.getSid());
        if(projectStaffService.getOne(p)!=null){
            return ServerResponse.createByErrorMessage("该人员已参与");
        }
        projectStaffService.save(projectStaff);
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    @RequestMapping(value = "updateProjectStaff",method = RequestMethod.POST)
    public ServerResponse updateProjectStaff(ProjectStaff projectStaff){
        projectStaffService.update(projectStaff);
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    @RequestMapping(value = "delProjectStaff",method = RequestMethod.GET)
    public ServerResponse delProjectStaff(Integer id){
        projectStaffService.delById(id);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @RequestMapping(value = "getProjectStaffById",method = RequestMethod.GET)
    public ServerResponse getProjectStaffById(Integer id){
        ProjectStaff projectStaff=projectStaffService.getById(id);
        return ServerResponse.createBySuccess("查找成功",projectStaff);
    }

    @RequestMapping(value = "getProjectStaffList",method = RequestMethod.GET)
    public ServerResponse getProjectStaffList(ProjectStaff projectStaff,@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit){
        long count=projectStaffService.count(projectStaff);
        List<ProjectStaff> projectStaffList=projectStaffService.listPage(projectStaff,"id DESC",page,limit);
        List<ProjectStaffDTO> projectStaffDTOS=new ArrayList<>();
        setProjectStaffDTO(projectStaffList, projectStaffDTOS);
        return ServerResponse.createBySuccessForLayuiTable("查找成功",projectStaffDTOS,count);
    }

    private void setProjectStaffDTO(List<ProjectStaff> projectStaffList, List<ProjectStaffDTO> projectStaffDTOS) {
        int orderNumber=1;
        ProjectStaffDTO projectStaffDTO;
        HumanEmp humanEmp=null;
        for (ProjectStaff staff : projectStaffList) {
            projectStaffDTO=new ProjectStaffDTO();
            projectStaffDTO.setId(staff.getId());
            projectStaffDTO.setOrderNumber(orderNumber);
            projectStaffDTO.setPid(staff.getPid());
            projectStaffDTO.setProjectStaffModule(staff.getProjectStaffModule());
            humanEmp=new HumanEmp();
            humanEmp.setId(staff.getSid());
            projectStaffDTO.setSid(humanEmpService.getOne(humanEmp).getName());
            orderNumber++;
            projectStaffDTOS.add(projectStaffDTO);
        }
    }

    @RequestMapping(value = "getProjectStaffListByLike",method = RequestMethod.GET)
    public ServerResponse getProjectStaffListByLike(ProjectStaff projectStaff,@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit){
        if(projectStaff.getProjectStaffModule()!=null && !"".equals(projectStaff.getProjectStaffModule())){
            projectStaff.setProjectStaffModule("%"+projectStaff.getProjectStaffModule()+"%");
        }
        long count=projectStaffService.countLike(projectStaff);
        List<ProjectStaff> projectStaffList=projectStaffService.listPage(projectStaff,"id DESC",page,limit);
        List<ProjectStaffDTO> projectStaffDTOS=new ArrayList<>();
        setProjectStaffDTO(projectStaffList, projectStaffDTOS);
        return ServerResponse.createBySuccessForLayuiTable("查找成功",projectStaffDTOS,count);
    }
    
}
