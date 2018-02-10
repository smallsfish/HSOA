package com.hassdata.hserp.controller;

import com.hassdata.hserp.po.ProjectProgress;
import com.hassdata.hserp.service.ProjectProgressService;
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
public class ProjectProgressController {

    @Resource
    private ProjectProgressService projectProgressService;

    @RequestMapping(value = "addProjectProgress",method = RequestMethod.POST)
    public ServerResponse addProjectProjress(ProjectProgress projectProgress){
        projectProgressService.save(projectProgress);
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    @RequestMapping(value = "updateProjectProgress",method = RequestMethod.POST)
    public ServerResponse updateProjectProgress(ProjectProgress projectProgress){
        projectProgressService.update(projectProgress);
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    @RequestMapping(value = "delProjectProgress",method = RequestMethod.GET)
    public ServerResponse delProjectProgress(Integer id){
        projectProgressService.delById(id);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @RequestMapping(value = "getProjectProgressList",method = RequestMethod.GET)
    public ServerResponse getProjectProgressList(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit){
        long count=projectProgressService.count(null);
        List<ProjectProgress> projectProgressList=projectProgressService.listPage(null,"id DESC",page,limit);
        return ServerResponse.createBySuccessForLayuiTable("查找成功",projectProgressList,count);
    }

    @RequestMapping(value = "getProjectProgressListByLike",method = RequestMethod.GET)
    public ServerResponse getProjectProgressListByLike(ProjectProgress projectProgress,@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit){
        if(projectProgress.getDescript()!=null && !"".equals(projectProgress.getDescript())){
            projectProgress.setDescript("%"+projectProgress.getDescript()+"%");
        }
        long count=projectProgressService.countLike(projectProgress);
        List<ProjectProgress> projectProgressList=projectProgressService.listPage(projectProgress,"id DESC",page,limit);
        return ServerResponse.createBySuccessForLayuiTable("查找成功",projectProgressList,count);
    }

    
}
