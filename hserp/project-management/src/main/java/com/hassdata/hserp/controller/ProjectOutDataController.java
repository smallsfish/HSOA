package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.ProjectOutDataDTO;
import com.hassdata.hserp.po.ProjectData;
import com.hassdata.hserp.po.ProjectOutData;
import com.hassdata.hserp.service.ProjectOutDataService;
import com.hassdata.hserp.utils.FileUploadUtils;
import com.hassdata.hserp.utils.ServerResponse;
import org.omg.PortableServer.POA;
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
public class ProjectOutDataController {

    @Resource
    private ProjectOutDataService projectOutDataService;

    /**
     * 添加项目输出资料
     * @param request
     * @param projectOutData
     * @param file
     * @return
     */
    @RequestMapping(value = "addProjectOutData",method = RequestMethod.POST)
    public ServerResponse addProjectOutData(HttpServletRequest request, ProjectOutData projectOutData, MultipartFile file){
        if (file.isEmpty()) {
            return ServerResponse.createByErrorMessage("请上传文件！");
        }
        String fn = file.getOriginalFilename();
        String suffix = fn.substring(fn.lastIndexOf('.') + 1, fn.length());
        String path = request.getSession().getServletContext().getRealPath("uploadFile/ProjectOutData");
        String fileName = new Date().getTime() + "." + suffix;
        try {
            FileUploadUtils.uploadSingleFile(path, fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("上传文件失败");
        }
        projectOutData.setProjectOutDataWay("uploadFile/ProjectOutData/" + fileName);
        projectOutDataService.save(projectOutData);
        return ServerResponse.createBySuccessMessage("添加成功");
    }


    /**
     * 更新项目输出资料
     * @param projectOutData
     * @return
     */
    @RequestMapping(value = "updateProjectOutData", method = RequestMethod.POST)
    public ServerResponse updateProjectData(ProjectOutData projectOutData) {
        projectOutDataService.update(projectOutData);
        return ServerResponse.createBySuccessMessage("修改成功");
    }


    /**
     * 更新项目输出资料文件
     * @param request
     * @param id
     * @param file
     * @return
     */
    @RequestMapping(value = "updateProjectOutDataFile", method = RequestMethod.POST)
    public ServerResponse updateProjectDataFile(HttpServletRequest request, Integer id, MultipartFile file) {
        ProjectOutData projectOutData = new ProjectOutData();
        projectOutData.setId(id);
        if (file.isEmpty()) {
            return ServerResponse.createByErrorMessage("请上传文件！");
        }
        String fn = file.getOriginalFilename();
        String suffix = fn.substring(fn.lastIndexOf('.') + 1, fn.length());
        String path = request.getSession().getServletContext().getRealPath("uploadFile/ProjectOutData");
        String fileName = new Date().getTime() + "." + suffix;
        try {
            FileUploadUtils.uploadSingleFile(path, fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("上传文件失败");
        }
        projectOutData.setProjectOutDataWay("uploadFile/ProjectOutData/" + fileName);
        projectOutDataService.update(projectOutData);
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    /**
     * 删除项目输出资料
     * @param id
     * @return
     */
    @RequestMapping(value = "delProjectOutData", method = RequestMethod.GET)
    public ServerResponse delProjectData(Integer id) {
        projectOutDataService.delById(id);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    /**
     * 通过ID获取项目输出资料
     * @param id
     * @return
     */
    @RequestMapping(value = "getProjectOutDataById", method = RequestMethod.GET)
    public ServerResponse getProjectById(Integer id) {
        return ServerResponse.createBySuccess(projectOutDataService.getById(id));
    }

    /**
     * 获取项目输出文档集合
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "getProjectOutDataList",method = RequestMethod.GET)
    public ServerResponse getProjectDataList(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit){
        long count=projectOutDataService.count(null);
        List<ProjectOutData> projectOutDataList=projectOutDataService.listPage(null,"id DESC",page,limit);
        List<ProjectOutDataDTO> projectOutDataDTOS=new ArrayList<>();
        getProjectOutDataDTO(projectOutDataList, projectOutDataDTOS);
        return ServerResponse.createBySuccessForLayuiTable("查找成功",projectOutDataDTOS,count);
    }

    /**
     * 模糊查询项目输出文档集合
     * @param projectOutData
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "getProjectOutDataByLike",method = RequestMethod.GET)
    public ServerResponse getProjectOutDataByLike(ProjectOutData projectOutData,@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit){
        if(projectOutData.getProjectOutDataName()!=null && !"".equals(projectOutData.getProjectOutDataName())){
            projectOutData.setProjectOutDataName("%"+projectOutData.getProjectOutDataName()+"%");
        }
        long count=projectOutDataService.countLike(projectOutData);
        List<ProjectOutData> projectOutDataList=projectOutDataService.listLikePage(projectOutData,"id DESC",page,limit);
        List<ProjectOutDataDTO> projectOutDataDTOS=new ArrayList<>();
        getProjectOutDataDTO(projectOutDataList, projectOutDataDTOS);
        return ServerResponse.createBySuccessForLayuiTable("查找成功",projectOutDataDTOS,count);
    }

    private void getProjectOutDataDTO(List<ProjectOutData> projectOutDataList, List<ProjectOutDataDTO> projectOutDataDTOS) {
        int orderNumber=0;
        ProjectOutDataDTO outDataDTO=null;
        for (ProjectOutData projectOutData : projectOutDataList) {
            outDataDTO=new ProjectOutDataDTO();
            outDataDTO.setId(projectOutData.getId());
            outDataDTO.setOrderNumber(++orderNumber);
            outDataDTO.setPid(projectOutData.getPid());
            outDataDTO.setProjectOutDataInfo(projectOutData.getProjectOutDataInfo());
            outDataDTO.setProjectOutDataName(projectOutData.getProjectOutDataName());
            outDataDTO.setProjectOutDataType(projectOutData.getProjectOutDataType());
            outDataDTO.setProjectOutDataWay(projectOutData.getProjectOutDataWay());
            projectOutDataDTOS.add(outDataDTO);
        }
    }

}
