package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.CustomDataDTO;
import com.hassdata.hserp.po.CustomData;
import com.hassdata.hserp.service.CustomDataService;
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

@Scope("prototype")
@RequestMapping("system/project/api")
@Controller
@ResponseBody
public class CustomDataController {

    @Resource
    private CustomDataService customDataService;

    /**
     * 根据客户ID查找客户资料
     *
     * @param customData
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "getCustomDataByCID", method = RequestMethod.GET)
    public ServerResponse getCustomDataByCID(CustomData customData, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit) {
        long count = customDataService.count(customData);
        List<CustomData> customDataList = customDataService.listPage(customData, "id DESC", page, limit);
        return ServerResponse.createBySuccessForLayuiTable("查询成功", customDataList, count);
    }

    /**
     * 根据客户资料ID查找客户资料
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getCustomDataById", method = RequestMethod.GET)
    public ServerResponse getCustomDataById(Integer id) {
        return ServerResponse.createBySuccess("查询成功", customDataService.getById(id));
    }

    /**
     * 根据分页查找所有客户资料
     *
     * @param page
     * @param limit
     * @return
     */

    @RequestMapping(value = "getCustomDataList", method = RequestMethod.GET)
    public ServerResponse getCustomData(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit) {
        long count = customDataService.count(null);
        List<CustomData> customDataList = customDataService.listPage(null, "id DESC", page, limit);
        List<CustomDataDTO> customDataDTOS = new ArrayList<>();
        getCustomDataDTO(customDataList, customDataDTOS);
        return ServerResponse.createBySuccessForLayuiTable("查找成功", customDataDTOS, count);
    }

    /**
     * 根据客户资料名称模糊查询客户资料
     *
     * @param customData
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "getCustomDataListByLike", method = RequestMethod.GET)
    public ServerResponse getCustomDataByList(CustomData customData, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit) {
        if (customData.getCustomDataName() != null && "".equals(customData.getCustomDataName())) {
            customData.setCustomDataName("%" + customData.getCustomDataName() + "%");
        }

        long count = customDataService.countLike(customData);

        List<CustomData> customDataList = customDataService.listLikePage(customData, "id DESC", page, limit);
        List<CustomDataDTO> customDataDTOS = new ArrayList<>();
        getCustomDataDTO(customDataList, customDataDTOS);
        return ServerResponse.createBySuccessForLayuiTable("查找成功", customDataDTOS, count);
    }

    /**
     * 更新用户资料
     *
     * @param customData
     * @return
     */
    @RequestMapping(value = "updateCustomData", method = RequestMethod.POST)
    public ServerResponse updateCustomData(CustomData customData) {
        customDataService.update(customData);
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    /**
     * 添加用户资料
     * @param request
     * @param customData
     * @param file
     * @return
     */
    @RequestMapping(value = "addCustomData", method = RequestMethod.POST)
    public ServerResponse addCustomData(HttpServletRequest request, CustomData customData, MultipartFile file) {
        if (file.isEmpty()) {
            return ServerResponse.createByErrorMessage("请上传文件！");
        }
        String fn = file.getOriginalFilename();
        String suffix = fn.substring(fn.lastIndexOf('.') + 1, fn.length());
        String path = request.getSession().getServletContext().getRealPath("uploadFile/CustomData");
        String fileName = new Date().getTime() + "." + suffix;
        try {
            FileUploadUtils.uploadSingleFile(path, fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("上传文件失败");
        }
        customData.setCustomDataWay("uploadFile/CustomData/"+fileName);
        customDataService.save(customData);
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    /**
     *根据客户资料ID更新客户文件
     * @param request
     * @param id
     * @param file
     * @return
     */
    @RequestMapping(value = "updateCustomDataFile",method = RequestMethod.POST)
    public ServerResponse updateCustomDataFile(HttpServletRequest request,Integer id,MultipartFile file){
        CustomData customData=new CustomData();
        customData.setId(id);
        if (file.isEmpty()) {
            return ServerResponse.createByErrorMessage("请上传文件！");
        }
        String fn = file.getOriginalFilename();
        String suffix = fn.substring(fn.lastIndexOf('.') + 1, fn.length());
        String path = request.getSession().getServletContext().getRealPath("uploadFile/CustomData");
        String fileName = new Date().getTime() + "." + suffix;
        try {
            FileUploadUtils.uploadSingleFile(path, fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("上传文件失败");
        }
        customData.setCustomDataWay("uploadFile/CustomData/"+fileName);
        customDataService.update(customData);
        return ServerResponse.createBySuccessMessage("修改成功");
    }



    private void getCustomDataDTO(List<CustomData> customDataList, List<CustomDataDTO> customDataDTOS) {
        CustomDataDTO customDataDTO;
        int orderNumber = 1;
        for (CustomData data : customDataList) {
            customDataDTO = new CustomDataDTO();
            customDataDTO.setOrderNumber(orderNumber);
            customDataDTO.setCid(data.getCid());
            customDataDTO.setCustomDataInfo(data.getCustomDataInfo());
            customDataDTO.setCustomDataName(data.getCustomDataName());
            customDataDTO.setCustomDataType(data.getCustomDataType());
            customDataDTO.setCustomDataWay(data.getCustomDataWay());
            customDataDTO.setId(data.getId());
            customDataDTOS.add(customDataDTO);
            orderNumber++;
        }
    }

}
