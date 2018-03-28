package com.hassdata.hserp.controller;

import com.hassdata.hserp.po.AdministrativeMeeting;
import com.hassdata.hserp.po.AdministrativeMeetingimg;
import com.hassdata.hserp.service.meeting.MeetingService;
import com.hassdata.hserp.service.meetingimg.MeetingImgService;
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
 * 会议管理
 */
@Controller
@Scope("prototype")
@RequestMapping("system/meeting/api")
@ResponseBody
public class MeetingController {

    @Autowired
    private MeetingService meetingService;
    @Autowired
    private MeetingImgService meetingImgService;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /*会议管理列表*/
    @RequestMapping(value = "meetingList",method = RequestMethod.GET)
    public ServerResponse meetingList(AdministrativeMeeting administrativeMeeting, Integer page, Integer limit, String orderBy){
        if (page == null || limit == null){
            return ServerResponse.createByErrorMessage("请求失败");
        }
        /*模糊查询*/
        if (administrativeMeeting.getContent() != null){
            administrativeMeeting.setContent("%" + administrativeMeeting.getContent() + "%");
        }
        List<AdministrativeMeeting> meetingList = null;
        meetingList = meetingService.listLikePage(administrativeMeeting,"id desc", page, limit);

        long count = meetingService.countLike(administrativeMeeting);//获取总数
        return ServerResponse.createBySuccessForLayuiTable("请求成功",meetingList,count);
    }

    /*id查询会议*/
    @RequestMapping(value = "meetingEditById", method = RequestMethod.POST)
    public ServerResponse meetingEditById(Integer id){
        if(null == id || id == 0){
            return ServerResponse.createByErrorMessage("必填项不得为空");
        }

        AdministrativeMeeting meeting = (AdministrativeMeeting) meetingService.getById(id);

        return ServerResponse.createBySuccess(meeting);
    }

    /*id查询图片集合*/
    @RequestMapping(value = "meetingImgEditById", method = RequestMethod.POST)
    public ServerResponse meetingImgEditById(Integer img_id){
        if(null == img_id || img_id == 0){
            return ServerResponse.createByErrorMessage("必填项不得为空");
        }

        AdministrativeMeetingimg meetingimg = (AdministrativeMeetingimg) meetingImgService.getById(img_id);

        return ServerResponse.createBySuccess(meetingimg);
    }

    /*修改图片集合*/
    @RequestMapping(value = "meetingImgSave", method = RequestMethod.POST)
    public ServerResponse meetingImgSave(AdministrativeMeetingimg administrativeMeetingimg, HttpServletRequest request, MultipartFile file){
        if (administrativeMeetingimg.getId() == null){
            return ServerResponse.createByErrorMessage("参数不得为空");
        }
        try {
            if (FileUploadUtils.processUpload(request,"/File/img",3, file, FileUploadType.getImgType())) {
                String path = request.getAttribute("newName").toString();
                AdministrativeMeetingimg data = new AdministrativeMeetingimg();
                data.setImg(administrativeMeetingimg.getImg());
                data.setImg(path);
                meetingImgService.update(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createBySuccess("添加失败!", "上传文件格式错误，请上传.jpg，.jpeg，.gif，.bmp，.png格式的图片");
        }
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

    /*修改会议*/
    @RequestMapping(value = "meetingSave", method = RequestMethod.POST)
    public ServerResponse meetingSave(AdministrativeMeeting administrativeMeeting){
        if (administrativeMeeting.getId() == null){
            return ServerResponse.createByErrorMessage("参数不得为空");
        }
        try {
            meetingService.update(administrativeMeeting);
        } catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("修改失败!");
        }
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

    /*删除会议*/
    @RequestMapping(value = "meetingDelete", method = RequestMethod.POST)
    public ServerResponse meetingDelete(Integer id){
        if (id == null){
            return ServerResponse.createBySuccessMessage("参数不得为空");
        }
        try {
            meetingImgService.delById(id);
            meetingService.delById(id);
        } catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("删除失败!");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    /*删除会议图片*/
    @RequestMapping(value = "meetingImgDelete", method = RequestMethod.POST)
    public ServerResponse meetingImgDelete(Integer img_id){
        if (img_id ==null){
            return  ServerResponse.createBySuccessMessage("参数不得为空");
        }
        try {
            meetingImgService.delById(img_id);
        } catch (Exception e){
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("删除失败!");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    /*添加会议*/
    @RequestMapping(value = "meetingAdd", method = RequestMethod.POST)
    public ServerResponse meetingAdd(AdministrativeMeeting administrativeMeeting){
        if (administrativeMeeting.getShouldparticipateNumber() == null) {
            return ServerResponse.createByErrorMessage("应参与人数不得为空");
        } else if (administrativeMeeting.getRealparticipateNumber() == null){
            return ServerResponse.createByErrorMessage("实参与人数不得为空");
        } else if(administrativeMeeting.getContent() == null){
            return ServerResponse.createByErrorMessage("会议内容不得为空");
        }
        try {
            administrativeMeeting.setTime(df.format(new Date()));
            meetingService.save(administrativeMeeting);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("新增失败!");
        }
        return ServerResponse.createBySuccessMessage("新增成功!");
    }

    /*添加会议图片*/
    @RequestMapping(value = "meetingImgAdd", method = RequestMethod.POST)
    public ServerResponse meetingImgAdd(AdministrativeMeetingimg administrativeMeetingimg, HttpServletRequest request, MultipartFile file){
        if (file == null){
            return ServerResponse.createByErrorMessage("图片不能为空！");
        }
        try {
            if (FileUploadUtils.processUpload(request,"/File/img",3, file, FileUploadType.getImgType())) {
                String path = request.getAttribute("newName").toString();
                AdministrativeMeetingimg data = new AdministrativeMeetingimg();
                data.setImg(administrativeMeetingimg.getImg());
                data.setImg(path);
                meetingImgService.save(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createBySuccess("添加失败!", "上传文件格式错误，请上传.jpg，.jpeg，.gif，.bmp，.png格式的图片");
        }
        return ServerResponse.createBySuccessMessage("添加成功!");
    }


}
