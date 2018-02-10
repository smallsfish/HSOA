package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.CustomResourceDTO;
import com.hassdata.hserp.po.CustomResource;
import com.hassdata.hserp.service.CustomResourceService;
import com.hassdata.hserp.utils.DateUtils;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("system/project/api")
@ResponseBody
public class CustomResourceController {

    private final byte CUSTOMRESOURCE_NORMAL = 0;
    private final byte CUSTOMRESOURCE_DEL = 1;
    private final byte CUSTOMRESOURCE_LOCKED = 2;

    @Resource
    private CustomResourceService customResourceService;

    /**
     * 添加客户资源接口
     *
     * @param customResource
     * @return
     */
    @RequestMapping(value = "addCustomResource", method = RequestMethod.POST)
    public ServerResponse addCustomResource(CustomResource customResource) {
        customResource.setStatus(CUSTOMRESOURCE_NORMAL);
        customResource.setCreateTime(new Date());
        customResourceService.save(customResource);
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    /**
     *根据ID 删除客户资源接口
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delCustomResource", method = RequestMethod.GET)
    public ServerResponse delCustomResource(Integer id) {
        CustomResource customResource = new CustomResource();
        customResource.setId(id);
        customResource.setStatus(CUSTOMRESOURCE_DEL);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    /**
     * 修改客户资源接口
     *
     * @param customResource
     * @return
     */
    @RequestMapping(value = "updateCustomResource", method = RequestMethod.POST)
    public ServerResponse updateCustomResource(CustomResource customResource) {
        customResourceService.update(customResource);
        return ServerResponse.createBySuccessMessage("修改成功");
    }

    /**
     * 根据ID查找客户资源
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getCustomResourceById", method = RequestMethod.GET)
    public ServerResponse getCustomResourceById(Integer id) {
        CustomResource customResource = customResourceService.getById(id);
        if (customResource == null) {
            return ServerResponse.createBySuccessMessage("未查到数据");
        } else {
            return ServerResponse.createBySuccess("查询成功", customResource);
        }
    }

    /**
     * 根据给定条件查询
     * @param customResource
     * @param page
     * @param limit
     * @return
     */

    @RequestMapping(value = "getCustomResourceByLike", method = RequestMethod.GET)
    public ServerResponse getCustomResourceByLike(CustomResource customResource, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit) {
        if (customResource.getCompany() != null && !"".equals(customResource.getCompany())) {
            customResource.setCompany("%" + customResource.getCompany() + "%");
        }
        if (customResource.getLead() != null && !"".equals(customResource.getLead())) {
            customResource.setLead("%" + customResource.getLead() + "%");
        }
        if (customResource.getResponsiblePerson() != null && !"".equals(customResource.getResponsiblePerson())) {
            customResource.setResponsiblePerson("%" + customResource.getResponsiblePerson() + "%");
        }

        long count = customResourceService.countLike(customResource);

        List<CustomResource> customResourceList = customResourceService.listLikePage(customResource, "id DESC", page, limit);

        List<CustomResourceDTO> customResourceDTOS;
        customResourceDTOS = getCustomResourceDTOS(customResourceList);
        return ServerResponse.createBySuccessForLayuiTable("查找成功", customResourceDTOS, count);

    }

    /**
     * 按照分页查询所有客户资源
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "getCustomResourceList", method = RequestMethod.GET)
    public ServerResponse getCustomResourceList(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit) {
        long count = customResourceService.count(null);
        List<CustomResource> customResourceList = customResourceService.listPage(null, "id DESC", page, limit);
        List<CustomResourceDTO> customResourceDTOS;
        customResourceDTOS = getCustomResourceDTOS(customResourceList);
        return ServerResponse.createBySuccessForLayuiTable("查找成功", customResourceDTOS, count);
    }

    private List<CustomResourceDTO> getCustomResourceDTOS(List<CustomResource> customResourceList) {
        List<CustomResourceDTO> customResourceDTOS;
        customResourceDTOS = new ArrayList<>();
        CustomResourceDTO cr = null;
        for (int i = 0; i < customResourceList.size(); i++) {
            cr = new CustomResourceDTO();
            cr.setCompany(customResourceList.get(i).getCompany());
            cr.setCompanyEamil(customResourceList.get(i).getCompanyEamil());
            cr.setCompanyTel(customResourceList.get(i).getCompanyTel());
            cr.setCompanyTime(DateUtils.dateFormatYMD(customResourceList.get(i).getCompanyTime()));
            cr.setCreateTime(DateUtils.dateFormatYMDHMS(customResourceList.get(i).getCreateTime()));
            cr.setId(customResourceList.get(i).getId());
            cr.setLead(customResourceList.get(i).getLead());
            cr.setOrderNumber(i + 1);
            cr.setResponsibleBirthday(DateUtils.dateFormatYMD(customResourceList.get(i).getResponsibleBirthday()));
            cr.setResponsibleEmail(customResourceList.get(i).getResponsibleEmail());
            cr.setResponsiblePerson(customResourceList.get(i).getResponsiblePerson());
            cr.setResponsibleTel(customResourceList.get(i).getResponsibleTel());
            cr.setSize(customResourceList.get(i).getSize());
            cr.setStatus(customResourceList.get(i).getStatus());
            customResourceDTOS.add(cr);
        }
        return customResourceDTOS;
    }


}
