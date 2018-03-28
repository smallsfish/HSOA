package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.ResourceDTO;
import com.hassdata.hserp.dto.RoleDTO;
import com.hassdata.hserp.po.Role;
import com.hassdata.hserp.po.RoleResource;
import com.hassdata.hserp.service.ResourceService;
import com.hassdata.hserp.service.RoleResourceService;
import com.hassdata.hserp.service.RoleService;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
@RequestMapping("system")
@ResponseBody
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private RoleResourceService roleResourceService;

    @Resource
    private ResourceService resourceService;

    @RequestMapping(value = "roleList", method = RequestMethod.GET)
    public ServerResponse roleList(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit) {
        long count = roleService.count(null);
        List<Role> roleList = roleService.listPage(null, "id desc", (page - 1) * limit, limit);
        List<RoleDTO> roleDTOList = new ArrayList<>();
        setRoleDTO(roleList, roleDTOList);
        return ServerResponse.createBySuccessForLayuiTable("请求成功", roleDTOList, count);
    }

    @RequestMapping(value = "roleDel", method = RequestMethod.GET)
    public ServerResponse roleDel(Integer id) {
        RoleResource role_resource = new RoleResource();
        role_resource.setRid(id);
        List<RoleResource> role_resources = roleResourceService.list(role_resource,null);
        for (RoleResource rr : role_resources) {
            roleResourceService.delById(rr.getId());
        }
        roleService.delById(id);
        return ServerResponse.createBySuccessMessage("删除成功");
    }


    @RequestMapping(value = "getRoleEditorResource", method = RequestMethod.GET)
    public ServerResponse getRoleEditor(Integer id) {
        RoleResource role_resource = new RoleResource();
        role_resource.setRid(id);
        List<RoleResource> role_resourceList = roleResourceService.list(role_resource,null);
        List<com.hassdata.hserp.po.Resource> resourceList = resourceService.list(null,null);
        clearRoot(resourceList);
        List<ResourceDTO> resourceDTOS = new ArrayList<>();
        ResourceDTO resourceDTO = null;
        for (com.hassdata.hserp.po.Resource r : resourceList) {
            resourceDTO = new ResourceDTO();
            resourceDTO.setChecked(false);
            resourceDTO.setAvailable(r.getAvailable());
            resourceDTO.setId(r.getId());
            resourceDTO.setpId(r.getParentid());
            resourceDTO.setName(r.getName());
            for (RoleResource rr : role_resourceList) {
                if (r.getId() == rr.getReid()) {
                    resourceDTO.setChecked(true);
                }
            }
            resourceDTOS.add(resourceDTO);
        }
        return ServerResponse.createBySuccess("请求成功",resourceDTOS);
    }

    @RequestMapping(value = "roleAvailableUpdate", method = RequestMethod.GET)
    public ServerResponse roleAvailableUpdate(Integer id, Integer available) {
        Role role = new Role();
        role.setAvailable(available == 1 ? true : false);
        role.setId(id);
        roleService.update(role);
        return ServerResponse.createBySuccessMessage("状态设置成功");
    }


    @RequestMapping(value = "searchRole", method = RequestMethod.GET)
    public ServerResponse searchRole(String name, @RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit) {
        Role role = new Role();
        role.setRolename("%" + name + "%");
        long count = roleService.countLike(role);
        List<Role> roleList = roleService.listLikePage(role, "id desc", (page - 1) * limit, limit);
        List<RoleDTO> roleDTOList = new ArrayList<>();
        setRoleDTO(roleList, roleDTOList);
        return ServerResponse.createBySuccessForLayuiTable("请求成功", roleDTOList, count);
    }

    @RequestMapping(value = "getAddRoleResource", method = RequestMethod.GET)
    public ServerResponse getAddRole() {
        List<com.hassdata.hserp.po.Resource> resourceList = resourceService.list(null,null);
        clearRoot(resourceList);
        return ServerResponse.createBySuccess("请求成功",resourceList);
    }

    @RequestMapping(value = "getRoleById", method = RequestMethod.GET)
    public ServerResponse getRoleById(Integer id) {
        Role role=roleService.getById(id);
        return ServerResponse.createBySuccess("请求成功",role);
    }

    private void clearRoot(List<com.hassdata.hserp.po.Resource> resourceList) {
        com.hassdata.hserp.po.Resource re = null;
        int i = 0;
        for (com.hassdata.hserp.po.Resource r : resourceList) {
            if (r.getParentid() == 0) {
                re = r;
            }
            if (r.getParentid() == 1) {
                resourceList.get(i).setAvailable(false);
            }
            i++;
        }
        resourceList.remove(re);
    }

    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    public ServerResponse addRole(Role role, String ids) {
        Integer maxId = roleService.getIdMax();
        role.setId(maxId);
        roleService.save(role);
        RoleResource role_resource = null;
        String[] id = ids.split(",");
        for (String i : id) {
            role_resource = new RoleResource();
            role_resource.setRid(maxId);
            role_resource.setReid(Integer.parseInt(i));
            roleResourceService.save(role_resource);
        }
        return ServerResponse.createBySuccessMessage("角色添加成功");
    }


    @RequestMapping(value = "editorRole", method = RequestMethod.POST)
    public ServerResponse editorRole(Role role, String ids) {
        roleService.update(role);
        RoleResource role_resource = null;
        roleResourceService.deleteByRoleId(role.getId());
        List<RoleResource> role_resources = new ArrayList<>();
        String[] id = ids.split(",");
        for (String i : id) {
            role_resource = new RoleResource();
            role_resource.setRid(role.getId());
            role_resource.setReid(Integer.parseInt(i));
            roleResourceService.save(role_resource);
        }
        return ServerResponse.createBySuccessMessage("角色修改成功");
    }

    private void setRoleDTO(List<Role> roleList, List<RoleDTO> roleDTOList) {
        RoleDTO roleDTO = null;
        int i = 1;
        for (Role r : roleList) {
            if (r.getRolename().equals("admin")) continue;
            String res = "";
            roleDTO = new RoleDTO();
            roleDTO.setAid(i);
            roleDTO.setId(r.getId());
            roleDTO.setAvailable(r.getAvailable());
            roleDTO.setDescription(r.getDescription());
            List<String> ss = resourceService.getResourceNameByRoleId(r.getId());
            for (int j = 0; j < ss.size(); j++) {
                if (j == ss.size() - 1) {
                    res += ss.get(j);
                } else {
                    res += ss.get(j) + " , ";
                }
            }
            roleDTO.setResources(res);
            roleDTO.setRolename(r.getRolename());
            i++;
            roleDTOList.add(roleDTO);
        }
    }

}
