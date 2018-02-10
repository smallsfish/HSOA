package com.hassdata.hserp.controller;

import com.hassdata.hserp.po.ProjectResource;
import com.hassdata.hserp.service.ProjectResourceService;
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
public class ProjectResourceController {

    @Resource
    private ProjectResourceService projectProgressService;

    @RequestMapping(value = "addProjectResource",method = RequestMethod.POST)
    public ServerResponse addProjectProjress(ProjectResource projectProgress){
        projectProgressService.save(projectProgress);
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    @RequestMapping(value = "updateProjectResource",method = RequestMethod.POST)
    public ServerResponse updateProjectResource(ProjectResource projectProgress){
        projectProgressService.update(projectProgress);
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    @RequestMapping(value = "delProjectResource",method = RequestMethod.GET)
    public ServerResponse delProjectResource(Integer id){
        projectProgressService.delById(id);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @RequestMapping(value = "getProjectResourceList",method = RequestMethod.GET)
    public ServerResponse getProjectResourceList(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit){
        long count=projectProgressService.count(null);
        List<ProjectResource> projectProgressList=projectProgressService.listPage(null,"id DESC",page,limit);
        return ServerResponse.createBySuccessForLayuiTable("查找成功",projectProgressList,count);
    }

}
