package com.hassdata.hserp.controller;

import com.hassdata.hserp.po.ProjectStaff;
import com.hassdata.hserp.service.ProjectStaffService;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("system/project/api")
@ResponseBody
public class ProjectStaffController {

    @Resource
    private ProjectStaffService projectStaffService;

    @RequestMapping(value = "addProjectStaff",method = RequestMethod.POST)
    public ServerResponse addProjectProjress(ProjectStaff projectStaff){
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

    @RequestMapping(value = "getProjectStaffList",method = RequestMethod.GET)
    public ServerResponse getProjectStaffList(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit){
        long count=projectStaffService.count(null);
        List<ProjectStaff> projectStaffList=projectStaffService.listPage(null,"id DESC",page,limit);
        return ServerResponse.createBySuccessForLayuiTable("查找成功",projectStaffList,count);
    }

    @RequestMapping(value = "getProjectStaffListByLike",method = RequestMethod.GET)
    public ServerResponse getProjectStaffListByLike(ProjectStaff projectStaff,@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit){
        if(projectStaff.getProjectStaffModule()!=null && !"".equals(projectStaff.getProjectStaffModule())){
            projectStaff.setProjectStaffModule("%"+projectStaff.getProjectStaffModule()+"%");
        }
        long count=projectStaffService.countLike(projectStaff);
        List<ProjectStaff> projectStaffList=projectStaffService.listPage(projectStaff,"id DESC",page,limit);
        return ServerResponse.createBySuccessForLayuiTable("查找成功",projectStaffList,count);
    }
    
}
