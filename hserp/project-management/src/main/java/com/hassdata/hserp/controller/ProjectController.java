package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.ProjectDTO;
import com.hassdata.hserp.po.Project;
import com.hassdata.hserp.po.ProjectData;
import com.hassdata.hserp.service.ProjectService;
import com.hassdata.hserp.utils.DateUtils;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("system/project/api")
@ResponseBody
public class ProjectController {

    @Resource
    private ProjectService projectService;

    /**
     * 添加项目
     * @param project
     * @return
     */
    @RequestMapping(value = "addProject", method = RequestMethod.POST)
    public ServerResponse addProject(Project project) {
        project.setProjectCreateTime(new Date());
        projectService.save(project);
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    /**
     * 修改项目
     * @param project
     * @return
     */
    @RequestMapping(value = "updateProject", method = RequestMethod.POST)
    public ServerResponse updateProject(Project project) {
        projectService.update(project);
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    /**
     * 删除项目
     * @param id
     * @return
     */
    @RequestMapping(value = "delProject", method = RequestMethod.GET)
    public ServerResponse delProject(Integer id) {
        projectService.delById(id);
        return ServerResponse.createBySuccessMessage("删除成功");
    }


    /**
     * 根据ID查找项目
     * @param id
     * @return
     */
    @RequestMapping(value = "getProjectById", method = RequestMethod.GET)
    public ServerResponse getProjectById(Integer id) {
        Project project=projectService.getById(id);
        ProjectDTO projectDTO=new ProjectDTO();
        if(project!=null){
            projectDTO.setOrderNumber(1);
            projectDTO.setProjectRange(project.getProjectRange());
            projectDTO.setProjectName(project.getProjectName());
            projectDTO.setProjectLevel(project.getProjectLevel());
            projectDTO.setProjectFinishDate(DateUtils.dateFormatYMD(project.getProjectFinishDate()));
        }
        return ServerResponse.createBySuccess(projectDTO);
    }


    /**
     * 查找项目列表
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "getProjectList", method = RequestMethod.GET)
    public ServerResponse getProjectList(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit) {
        long count = projectService.count(null);
        List<Project> projectList = projectService.listPage(null, "id DESC", page, limit);
        List<ProjectDTO> projectDTOS = new ArrayList<>();

        getPorjectDTO(projectList, projectDTOS);

        return ServerResponse.createBySuccessForLayuiTable("查找成功", projectDTOS, count);

    }

    /**
     * 模糊查找项目
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "getProjectByLike", method = RequestMethod.GET)
    public ServerResponse getProjectByLike(HttpServletRequest request, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit) {
        String projectName = request.getParameter("projectName");
        try {
            projectName= URLDecoder.decode(URLDecoder.decode(projectName,"UTF-8"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Project project=new Project();
        project.setProjectName("%"+projectName+"%");
        long count = projectService.countLike(project);
        List<Project> projectList = projectService.listLikePage(project, "id DESC", page, limit);
        List<ProjectDTO> projectDTOS = new ArrayList<>();

        getPorjectDTO(projectList, projectDTOS);

        return ServerResponse.createBySuccessForLayuiTable("查找成功", projectDTOS, count);
    }

    private void getPorjectDTO(List<Project> projectList, List<ProjectDTO> projectDTOS) {
        int orderNumber = 1;

        ProjectDTO projectDTO;

        for (Project p : projectList) {
            projectDTO = new ProjectDTO();
            projectDTO.setId(p.getId());
            projectDTO.setProjectCreateTime(DateUtils.dateFormatYMDHMS(p.getProjectCreateTime()));
            projectDTO.setProjectFinishDate(DateUtils.dateFormatYMD(p.getProjectFinishDate()));
            projectDTO.setProjectLevel(p.getProjectLevel());
            projectDTO.setProjectName(p.getProjectName());
            projectDTO.setProjectRange(p.getProjectRange());
            projectDTO.setOrderNumber(orderNumber);
            orderNumber++;
            projectDTOS.add(projectDTO);
        }
    }

}
