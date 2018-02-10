package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.ProjectDataDTO;
import com.hassdata.hserp.po.ProjectData;
import com.hassdata.hserp.service.ProjectDataService;
import com.hassdata.hserp.utils.FileUploadUtils;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("system/project/api")
@ResponseBody
public class ProjectDataController {

    @Resource
    private ProjectDataService projectDataService;

    /**
     * 添加项目资料
     * @param request
     * @param projectData
     * @param file
     * @return
     */
    @RequestMapping(value = "addProjectData", method = RequestMethod.POST)
    public ServerResponse addProjectData(HttpServletRequest request, ProjectData projectData, MultipartFile file) {
        if (file.isEmpty()) {
            return ServerResponse.createByErrorMessage("请上传文件！");
        }
        String fn = file.getOriginalFilename();
        String suffix = fn.substring(fn.lastIndexOf('.') + 1, fn.length());
        String path = request.getSession().getServletContext().getRealPath("uploadFile/ProjectData");
        String fileName = new Date().getTime() + "." + suffix;
        try {
            FileUploadUtils.uploadSingleFile(path, fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("上传文件失败");
        }
        projectData.setProjectDataWay("uploadFile/ProjectData/" + fileName);
        projectDataService.save(projectData);
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    /**
     * 更新项目资料
     * @param projectData
     * @return
     */
    @RequestMapping(value = "updateProjectData", method = RequestMethod.POST)
    public ServerResponse updateProjectData(ProjectData projectData) {
        projectDataService.update(projectData);
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    /**
     * 更新项目资料文件
     * @param request
     * @param id
     * @param file
     * @return
     */
    @RequestMapping(value = "updateProjectDataFile", method = RequestMethod.POST)
    public ServerResponse updateProjectDataFile(HttpServletRequest request, Integer id, MultipartFile file) {
        ProjectData projectData = new ProjectData();
        projectData.setId(id);
        if (file.isEmpty()) {
            return ServerResponse.createByErrorMessage("请上传文件！");
        }
        String fn = file.getOriginalFilename();
        String suffix = fn.substring(fn.lastIndexOf('.') + 1, fn.length());
        String path = request.getSession().getServletContext().getRealPath("uploadFile/ProjectData");
        String fileName = new Date().getTime() + "." + suffix;
        try {
            FileUploadUtils.uploadSingleFile(path, fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("上传文件失败");
        }
        projectData.setProjectDataWay("uploadFile/ProjectData/" + fileName);
        projectDataService.update(projectData);
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    /**
     * 删除项目资料
     * @param id
     * @return
     */
    @RequestMapping(value = "delProjectData", method = RequestMethod.GET)
    public ServerResponse delProjectData(Integer id) {
        projectDataService.delById(id);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    /**
     * 通过ID获取项目资料
     * @param id
     * @return
     */
    @RequestMapping(value = "getProjectDataById", method = RequestMethod.GET)
    public ServerResponse getProjectById(Integer id) {
        return ServerResponse.createBySuccess(projectDataService.getById(id));
    }

    /**
     * 获取项目资料集合
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "getProjectDataList", method = RequestMethod.GET)
    public ServerResponse getProjectList(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit) {
        long count = projectDataService.count(null);
        List<ProjectData> projectDataList = projectDataService.listPage(null, "id DESC", page, limit);

        List<ProjectDataDTO> projectDataDTOS = new ArrayList<>();

        getProjectDataDTO(projectDataList, projectDataDTOS);
        return ServerResponse.createBySuccessForLayuiTable("查找成功", projectDataDTOS, count);
    }

    /**
     * 模糊查找项目资料
     * @param projectData
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "getProjectDataByLike", method = RequestMethod.GET)
    public ServerResponse getProjectDataByLike(ProjectData projectData, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit) {
        if (projectData.getProjectDataName() != null && !"".equals(projectData.getProjectDataName())) {
            projectData.setProjectDataName("%" + projectData.getProjectDataName() + "%");
        }
        long count = projectDataService.countLike(projectData);
        List<ProjectData> projectDataList = projectDataService.listLikePage(projectData, "id DESC", page, limit);

        List<ProjectDataDTO> projectDataDTOS = new ArrayList<>();

        getProjectDataDTO(projectDataList, projectDataDTOS);
        return ServerResponse.createBySuccessForLayuiTable("查找成功", projectDataDTOS, count);
    }

    private void getProjectDataDTO(List<ProjectData> projectDataList, List<ProjectDataDTO> projectDataDTOS) {
        int orderNumber = 0;
        ProjectDataDTO projectDataDTO;
        for (ProjectData projectData : projectDataList) {
            projectDataDTO = new ProjectDataDTO();
            projectDataDTO.setOrderNumber(++orderNumber);
            projectDataDTO.setId(projectData.getId());
            projectDataDTO.setPid(projectData.getPid());
            projectDataDTO.setProjectDataInfo(projectData.getProjectDataInfo());
            projectDataDTO.setProjectDataName(projectData.getProjectDataName());
            projectDataDTO.setProjectDataType(projectData.getProjectDataType());
            projectDataDTO.setProjectDataWay(projectData.getProjectDataWay());
            projectDataDTOS.add(projectDataDTO);
        }
    }
}
