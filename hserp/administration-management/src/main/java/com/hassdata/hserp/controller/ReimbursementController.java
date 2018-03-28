package com.hassdata.hserp.controller;

import com.hassdata.hserp.po.AdministrativeReimbursement;
import com.hassdata.hserp.service.reimbursement.ReimbursementService;
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
 * 报销管理
 */
@Controller
@Scope("prototype")
@RequestMapping("system/reimbursement/api")
@ResponseBody
public class ReimbursementController {

    @Autowired
    private ReimbursementService reimbursementService;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "reimbursementList", method = RequestMethod.GET)
    public ServerResponse reimbursementList(AdministrativeReimbursement administrativeReimbursement, String orderBy, Integer page, Integer limit){
        if (page == null || limit == null){
            return ServerResponse.createByErrorMessage("请求失败");
        }
        /*模糊查询*/
        if (administrativeReimbursement.getApprover() != null){
            administrativeReimbursement.setApprover("%" + administrativeReimbursement.getApprover() + "%");
        }
        List<AdministrativeReimbursement> reimbursementList = null;
        reimbursementList = reimbursementService.listLikePage(administrativeReimbursement, "id desc", page, limit);

        long count = reimbursementService.countLike(administrativeReimbursement);
        return ServerResponse.createBySuccessForLayuiTable("请求成功", reimbursementList, count);
    }

    /*删除*/
    @RequestMapping(value = "reimbursementDelete", method = RequestMethod.POST)
    public ServerResponse reimbursementDelete(Integer id){
        if(null == id || id == 0){
            return ServerResponse.createByErrorMessage("程序异常，稍后再试！");
        }
        try {
            reimbursementService.delById(id);
        }catch (Exception e){
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("删除失败!");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    /*id查询*/
    @RequestMapping(value = "reimbursementEditById", method = RequestMethod.POST)
    public ServerResponse reimbursementEditById(Integer id){
        if(null == id || id == 0){
            return ServerResponse.createByErrorMessage("必填项不得为空");
        }

        AdministrativeReimbursement reimbursement = (AdministrativeReimbursement) reimbursementService.getById(id);

        return ServerResponse.createBySuccess(reimbursement);
    }


    /*修改*/
    @RequestMapping(value = "reimbursementSave", method = RequestMethod.POST)
    public ServerResponse reimbursementSave(AdministrativeReimbursement administrativeReimbursement, HttpServletRequest request, MultipartFile file){
        if (administrativeReimbursement.getId() == null) {
            return ServerResponse.createByErrorMessage("必须参数不为空!");
        }
        try {

            /*if (FileUploadUtils.processUpload(request,"/File/img",3, file, FileUploadType.getImgType())) {
                String path = request.getAdttribute("newName").toString();*/
                AdministrativeReimbursement data = new AdministrativeReimbursement();
                data.setId(administrativeReimbursement.getId());
                data.setAmount(administrativeReimbursement.getAmount());
                data.setCategory(administrativeReimbursement.getCategory());
                data.setApprover(administrativeReimbursement.getApprover());
                //data.setImg(path);
                data.setDetails(administrativeReimbursement.getDetails());
                data.setCcperson(administrativeReimbursement.getCcperson());
                data.setTime(df.format(new Date()));
                reimbursementService.update(data);
            //}

        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createBySuccess("修改失败!","上传文件格式错误，请上传.jpg，.jpeg，.gif，.bmp，.png格式的图片");
        }
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

    /*添加*/
    @RequestMapping(value = "reimbursementAdd", method = RequestMethod.POST)
    public ServerResponse reimbursementAdd(AdministrativeReimbursement administrativeReimbursement, HttpServletRequest request, MultipartFile file){
        if (administrativeReimbursement.getApprover() == null && administrativeReimbursement.getCategory() == null && administrativeReimbursement.getAmount() == null) {
            return ServerResponse.createByErrorMessage("参数不能为空!");
        }
        try {
            /*if (FileUploadUtils.processUpload(request,"/File/img",3, file, FileUploadType.getImgType())) {
                String path = request.getAttribute("newName").toString();*/
                AdministrativeReimbursement data = new AdministrativeReimbursement();
                data.setAmount(administrativeReimbursement.getAmount());
                data.setCategory(administrativeReimbursement.getCategory());
                data.setApprover(administrativeReimbursement.getApprover());
                //data.setImg(path);
                data.setDetails(administrativeReimbursement.getDetails());
                data.setCcperson(administrativeReimbursement.getCcperson());
                data.setTime(df.format(new Date()));
                reimbursementService.save(data);
            //}
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createBySuccess("添加失败!", "上传文件格式错误，请上传.jpg，.jpeg，.gif，.bmp，.png格式的图片");
        }
        return ServerResponse.createBySuccessMessage("添加成功!");
    }

}
