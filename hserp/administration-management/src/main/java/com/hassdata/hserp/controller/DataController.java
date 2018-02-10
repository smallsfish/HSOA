package com.hassdata.hserp.controller;

import com.hassdata.hserp.po.AdministrativeData;
import com.hassdata.hserp.service.data.DataService;
import com.hassdata.hserp.util.FileUploadType;
import com.hassdata.hserp.util.FileUploadUtils;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 注册资料管理
 */
@Controller
@Scope("prototype")
@RequestMapping("system/data/api")
@ResponseBody
public class DataController {

    @Autowired
    private DataService dataService;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /*公章管理列表*/
    @RequestMapping(value = "dataList",method = RequestMethod.GET)
    public ServerResponse dataList(AdministrativeData administrativeData, Integer page, Integer limit, String orderBy){
        if (page == null || limit == null){
            return ServerResponse.createByErrorMessage("请求失败");
        }

        /*模糊查询*/
        if (administrativeData.getText() != null){
            administrativeData.setText("%" + administrativeData.getText() + "%");
        }
        List<AdministrativeData> dataList = null;
        dataList = dataService.listLikePage(administrativeData,"id desc", page, limit);

        long count=dataService.countLike(administrativeData);
        return ServerResponse.createBySuccessForLayuiTable("请求成功",dataList,count);
    }

    /*删除*/
    @RequestMapping(value = "dataDelete", method = RequestMethod.POST)
    public ServerResponse dataDelete(Integer id){
        if(null == id || id == 0){
            return ServerResponse.createByErrorMessage("必填项不得为空");
        }
        try {
            dataService.delById(id);
        } catch (Exception e){
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("删除失败!");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }


    /*id查询*/
    @RequestMapping(value = "dataEditById", method = RequestMethod.POST)
    public ServerResponse dataEditById(Integer id){
        if(null == id || id == 0){
            return ServerResponse.createByErrorMessage("必填项不得为空");
        }

        AdministrativeData dataList = (AdministrativeData) dataService.getById(id);

        return ServerResponse.createBySuccess(dataList);
    }


    /*修改*/
    @RequestMapping(value = "dataSave", method = RequestMethod.POST)
    public ServerResponse dataSave(AdministrativeData administrativeData, HttpServletRequest request, MultipartFile file){
        if (file == null){
            return ServerResponse.createByErrorMessage("文件不能为空");
        }
        try {
            if (FileUploadUtils.processUpload(request,"/File/doc",3, file, FileUploadType.getDocType())) {
                String path = request.getAttribute("newName").toString();
                AdministrativeData data = new AdministrativeData();
                data.setAnnex(path);
                data.setText(administrativeData.getText());
                data.setTime(df.format(new Date()));

                dataService.update(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createBySuccess("修改失败!", "上传文件格式错误，请上传.doc .docx .word .wps 格式的文件");
        }
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

    /*添加*/
    @RequestMapping(value = "dataAdd", method = RequestMethod.POST)
    public ServerResponse dataAdd(AdministrativeData administrativeData, HttpServletRequest request, MultipartFile file){
        if (file == null){
            return ServerResponse.createByErrorMessage("文件不能为空");
        }

        try {
            if (FileUploadUtils.processUpload(request,"/File/doc",3, file, FileUploadType.getDocType())){
                String path = request.getAttribute("newName").toString();
                AdministrativeData data = new AdministrativeData();
                data.setAnnex(path);
                data.setText(administrativeData.getText());
                data.setTime(df.format(new Date()));
                dataService.save(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createBySuccess("添加失败", "上传文件格式错误，请上传.doc .docx .word .wps 格式的文件");
        }
        return ServerResponse.createBySuccessMessage("新增成功!");
    }
}
