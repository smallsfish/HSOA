package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.AdminUserDTO;
import com.hassdata.hserp.dto.IndexMenu;
import com.hassdata.hserp.dto.MenuUrl;
import com.hassdata.hserp.dto.RoleDTO;
import com.hassdata.hserp.po.AdminRole;
import com.hassdata.hserp.po.AdminUser;
import com.hassdata.hserp.po.Role;
import com.hassdata.hserp.service.*;
import com.hassdata.hserp.utils.ArrayUtils;
import com.hassdata.hserp.utils.FileUploadUtils;
import com.hassdata.hserp.utils.ServerResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
@ResponseBody
@RequestMapping("system")
public class AdminUserController {


    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Resource
    private PasswordHelper passwordHelper;

    @Resource
    private RoleService roleService;

    @Resource
    private AdminRoleService adminRoleService;

    @Resource
    private ResourceService resourceService;

    @Resource
    private AdminUserService adminUserService;

    @RequestMapping(value = "exit", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse exitSystem() {
        if (SecurityUtils.getSubject().getSession(true).getAttribute("CurrentAdminUser") != null) {
            SecurityUtils.getSubject().getSession(true).removeAttribute("CurrentAdminUser");
        }
        SecurityUtils.getSubject().logout();
        return ServerResponse.createBySuccessMessage("退出成功");
    }

    @RequestMapping(value = "getEditorAdminUserRole",method = RequestMethod.GET)
    public ServerResponse get(Integer id){
        List<Role> roles = roleService.list(null,null);
        AdminRole admin_role = new AdminRole();
        admin_role.setAid(id);
        List<AdminRole> admin_roleList = adminRoleService.list(admin_role,null);
        List<RoleDTO> roleDTOList = new ArrayList<>();
        RoleDTO roleDTO = null;
        for (Role r : roles) {
            roleDTO = new RoleDTO();
            roleDTO.setId(r.getId());
            roleDTO.setAvailable(r.getAvailable());
            roleDTO.setDescription(r.getDescription());
            roleDTO.setChecked(false);
            for (AdminRole ar : admin_roleList) {
                if (ar.getRid() == r.getId()) {
                    roleDTO.setChecked(true);
                }
            }
            roleDTOList.add(roleDTO);
        }
        return ServerResponse.createBySuccess("请求成功",roleDTOList);
    }

    @RequestMapping(value = "indexMenu", method = RequestMethod.GET)
    public ServerResponse getIndexMenu() {
        String account = (String) SecurityUtils.getSubject().getPrincipal();
        List<com.hassdata.hserp.po.Resource> resources = resourceService.list(null, null);
        Map<String, com.hassdata.hserp.po.Resource> resourceMap = new HashMap<>();
        resources.stream().forEach((r) -> resourceMap.put(r.getId() + "", r));
        List<com.hassdata.hserp.po.Resource> resourceList = resourceService.getResourceByAccount(account);
        List<IndexMenu> indexMenuList = new ArrayList<>();
        List<MenuUrl> menuUrlList = null;
        IndexMenu indexMenu = null;
        MenuUrl menuUrl = null;
        String[][] strings = new String[resources.size()][3];
        Integer index = 0, i = 0;
        for (com.hassdata.hserp.po.Resource r : resourceList) {
            if (r.getType().equals("menu")) {
                if (ArrayUtils.idExists(strings, r.getParentid() + "", false)) {
                    menuUrl = new MenuUrl();
                    menuUrl.setName(r.getName());
                    menuUrl.setUrl(r.getUrl());
                    menuUrl.setIconUrl(r.getIconurl());
                    Integer j = ArrayUtils.getIndex(strings, r.getParentid() + "");
                    indexMenuList.get(j).getMenuUrlList().add(menuUrl);
                    strings[i][0] = j + "";
                    strings[i][1] = r.getParentid() + "";
                    strings[i][2] = r.getId() + "";
                    i++;
                } else {
                    menuUrlList = new ArrayList<>();
                    com.hassdata.hserp.po.Resource res = resourceMap.get(r.getParentid() + "");
                    indexMenu = new IndexMenu();
                    indexMenu.setName(res.getName());
                    menuUrl = new MenuUrl();
                    menuUrl.setName(r.getName());
                    menuUrl.setUrl(r.getUrl());
                    menuUrl.setIconUrl(r.getIconurl());
                    menuUrlList.add(menuUrl);
                    indexMenu.setMenuUrlList(menuUrlList);
                    indexMenuList.add(indexMenu);
                    strings[i][0] = index + "";
                    strings[i][1] = r.getParentid() + "";
                    strings[i][2] = r.getId() + "";
                    i++;
                    index++;
                }
            } else {
                if (ArrayUtils.idExists(strings, r.getParentid() + "", true)) {
                    continue;
                } else {
                    com.hassdata.hserp.po.Resource reso = resourceMap.get(r.getParentid() + "");
                    if (ArrayUtils.idExists(strings, reso.getParentid() + "", false)) {
                        menuUrl = new MenuUrl();
                        menuUrl.setName(reso.getName());
                        menuUrl.setUrl(reso.getUrl());
                        menuUrl.setIconUrl(reso.getIconurl());
                        Integer j = ArrayUtils.getIndex(strings, reso.getParentid() + "");
                        strings[i][0] = j + "";
                        strings[i][1] = reso.getParentid() + "";
                        strings[i][2] = reso.getId() + "";
                        indexMenuList.get(j).getMenuUrlList().add(menuUrl);
                        i++;
                    } else {
                        menuUrlList = new ArrayList<>();
                        com.hassdata.hserp.po.Resource resou = resourceMap.get(reso.getParentid() + "");
                        indexMenu = new IndexMenu();
                        indexMenu.setName(resou.getName());
                        strings[i][0] = index + "";
                        strings[i][1] = reso.getParentid() + "";
                        strings[i][2] = reso.getId() + "";
                        menuUrl = new MenuUrl();
                        menuUrl.setName(reso.getName());
                        menuUrl.setUrl(reso.getUrl());
                        menuUrl.setIconUrl(reso.getIconurl());
                        menuUrlList.add(menuUrl);
                        indexMenu.setMenuUrlList(menuUrlList);
                        indexMenuList.add(indexMenu);
                        i++;
                        index++;
                    }
                }
            }
        }
        return ServerResponse.createBySuccess("获取成功", indexMenuList);
    }


    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse systemLogin(AdminUser adminUser, @RequestParam(required = false) Integer remind) {
        boolean rememberMe = false;
        if (adminUser.getAccount().isEmpty()) {
            return ServerResponse.createByErrorMessage("请输入账号！");
        } else if (adminUser.getPassword().isEmpty()) {
            return ServerResponse.createByErrorMessage("请输入密码！");
        }
        if (remind != null) {
            rememberMe = true;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(adminUser.getAccount(), adminUser.getPassword(), rememberMe);
        adminUser.setPassword(null);
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("账号或密码错误");
        } catch (IncorrectCredentialsException e) {
            return ServerResponse.createByErrorMessage("账号或密码错误");
        } catch (LockedAccountException e) {
            return ServerResponse.createByErrorMessage("对不起，账号已锁定");
        } catch (ExcessiveAttemptsException e) {
            return ServerResponse.createByErrorMessage("重试次数过多，已锁定");
        }
        AdminUser admin_user = adminUserService.getOne(adminUser);
        SecurityUtils.getSubject().getSession().setAttribute("CurrentAdminUser", admin_user);
        Integer id = admin_user.getId();
        admin_user = new AdminUser();
        admin_user.setId(id);
        admin_user.setLastlogintime(new Date());
        adminUserService.update(admin_user);
        return ServerResponse.createBySuccessMessage("登陆成功");
    }


    @RequestMapping(value = "getAdminUser", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getAdminUser(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "30") Integer limit) {
        long count = adminUserService.count(null);
        List<AdminUser> a_us = adminUserService.listLikePage(null, "id DESC", (page - 1) * limit, limit);
        List<AdminUserDTO> aus = new ArrayList<>();
        AdminUserDTO adminUserDTO = null;
        int aid = 1;
        count = setAdminUserDTO(adminUserDTO, a_us, aus, aid, count);
        return ServerResponse.createBySuccessForLayuiTable("请求成功", aus, count);
    }


    @RequestMapping(value = "addAdminUser", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addAdminUser(AdminUser adminUser, MultipartHttpServletRequest request, MultipartFile file, String[] roles) {
        if (file.isEmpty()) {
            return ServerResponse.createByErrorMessage("请上传头像！");
        }
        String fn = file.getOriginalFilename();
        String suffix = fn.substring(fn.lastIndexOf('.') + 1, fn.length());
        long fileSize = file.getSize();
        if (fileSize > 512000) {
            return ServerResponse.createByErrorMessage("请上传小于500k的图片");
        }
        if (suffix.equals("jpg") || suffix.equals("JPG") || suffix.equals("jpeg") || suffix.equals("JPEG") || suffix.equals("PNG") || suffix.equals("png") || suffix.equals("GIF") || suffix.equals("gif")) {
        } else return ServerResponse.createByErrorMessage("请上传jpg/jpeg/png/gif格式的图片！");
        String path = request.getSession().getServletContext().getRealPath("static/uploadimage");
        String fileName = new Date().getTime() + "." + suffix;
        try {
            FileUploadUtils.uploadSingleFile(path, fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("上传图片失败");
        }
        adminUser.setHeadimage("static/uploadimage/"+fileName);
        adminUser.setPassword("000000");
        passwordHelper.encryptPassword(adminUser);
        adminUser.setCreatedatetime(new Date());
        adminUserService.save(adminUser);
        int maxId = adminUserService.getIdMax();
        AdminRole admin_role = null;
        for (String rid : roles) {
            admin_role = new AdminRole();
            admin_role.setAid(maxId);
            admin_role.setRid(Integer.parseInt(rid));
            adminRoleService.save(admin_role);
        }
        return ServerResponse.createBySuccessMessage("添加管理员成功");
    }

    @RequestMapping(value = "adminUserSearch", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse adminUserSearch(String account, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
        if (page == null || limit == null) {
            page = 1;
            limit = 30;
        }
        AdminUser admin_user = new AdminUser();
        admin_user.setAccount("%" + account + "%");
        long count = adminUserService.countLike(admin_user);
        List<AdminUser> a_us = adminUserService.listLikePage(admin_user, "id DESC", (page - 1) * limit, limit);
        List<AdminUserDTO> aus = new ArrayList<>();
        AdminUserDTO adminUserDTO = null;
        int aid = 1;
        count = setAdminUserDTO(adminUserDTO, a_us, aus, aid, count);
        return ServerResponse.createBySuccessForLayuiTable("搜索成功", aus, count);
    }


    @RequestMapping(value = "getAdminUserById",method = RequestMethod.GET)
    public ServerResponse getAdminUserById(Integer id){
        AdminUser adminUser=adminUserService.getById(id);
        return ServerResponse.createBySuccess("请求成功",adminUser);
    }

    @RequestMapping(value = "updateAdminHeadImage", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateAdminUserHeadImage(Integer id, MultipartFile file, HttpServletRequest request, String image) {
        AdminUser admin_user = new AdminUser();
        admin_user.setId(id);
        if (file.isEmpty()) {
            return ServerResponse.createByErrorMessage("请上传头像！");
        }
        String fn = file.getOriginalFilename();
        String suffix = fn.substring(fn.lastIndexOf('.') + 1, fn.length());
        long fileSize = file.getSize();
        if (fileSize > 512000) {
            return ServerResponse.createByErrorMessage("请上传小于500k的图片");
        }
        if (suffix.equals("jpg") || suffix.equals("JPG") || suffix.equals("jpeg") || suffix.equals("JPEG") || suffix.equals("PNG") || suffix.equals("png") || suffix.equals("GIF") || suffix.equals("gif")) {
        } else return ServerResponse.createByErrorMessage("请上传jpg/jpeg/png/gif格式的图片！");
        String path = request.getSession().getServletContext().getRealPath("static/uploadimage");
        String fileName = new Date().getTime() + "." + suffix;
        try {
            FileUploadUtils.uploadSingleFile(path, fileName, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("上传图片失败");
        }
        File file1 = new File(path + "/" + image);
        if (file1.exists()) {
            file1.delete();
        }
        admin_user.setHeadimage("static/uploadimage/"+fileName);
        adminUserService.update(admin_user);
        return ServerResponse.createBySuccess("图片上传成功", "static/uploadimage/" + fileName);
    }


    @RequestMapping(value = "updateAdminUser", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateAdminUser(AdminUser admin_user, String[] roles) {
        adminUserService.update(admin_user);
        adminRoleService.deleteAdminRoleByAid(admin_user.getId());
        for (String r : roles) {
            AdminRole admin_role = new AdminRole();
            admin_role.setRid(Integer.parseInt(r));
            admin_role.setAid(admin_user.getId());
            //admin_roleService.updateAdminRoleByAid(admin_role);
            adminRoleService.save(admin_role);
        }
        return ServerResponse.createBySuccessMessage("更新成功");
    }

    @RequestMapping(value = "adminUserDel", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse adminUserDel(Integer id) {
        adminUserService.delById(id);
        return ServerResponse.createBySuccessMessage("管理员删除成功！");
    }

    @RequestMapping(value = "adminPasswordUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse adminPasswordUpdate(String newPassword) {

        AdminUser admin_user = (AdminUser) SecurityUtils.getSubject().getSession(true).getAttribute("CurrentAdminUser");
        AdminUser adu = new AdminUser();
        adu.setId(admin_user.getId());
        if (!newPassword.equals("") && newPassword != null) {
            adu.setAccount(admin_user.getAccount());
            adu.setPassword(newPassword);
            passwordHelper.encryptPassword(adu);
            adminUserService.update(adu);
            return ServerResponse.createBySuccessMessage("密码重置成功，下次登录将失效");
        }
        return ServerResponse.createByErrorMessage("密码重置失败");
    }


    private long setAdminUserDTO(AdminUserDTO adminUserDTO, List<AdminUser> a_us, List<AdminUserDTO> aus, int aid, long count) {
        for (AdminUser au : a_us) {
            if (!au.getAccount().equals("admin")) {
                adminUserDTO = new AdminUserDTO();
                adminUserDTO.setId(au.getId());
                adminUserDTO.setAid(aid);
                adminUserDTO.setAccount(au.getAccount());
                adminUserDTO.setHeadimage(au.getHeadimage());
                adminUserDTO.setEmail(au.getEmail());
                adminUserDTO.setRole(null);
                adminUserDTO.setIdentifier(au.getIdentifier());
                adminUserDTO.setCreatedatetime(format.format(au.getCreatedatetime()));
                if (au.getLastlogintime() == null) {
                    adminUserDTO.setLastlogintime("该用户没有登录过");
                } else {
                    adminUserDTO.setLastlogintime(format.format(au.getLastlogintime()));
                }
                adminUserDTO.setRemarks(au.getRemarks());
                AdminRole admin_role = new AdminRole();
                admin_role.setAid(au.getId());
                String roles = "";
                for (AdminRole ar : adminRoleService.list(admin_role, null)) {
                    roles += roleService.getById(ar.getRid()).getDescription() + "    ";
                }
                adminUserDTO.setRole(roles);
                aus.add(adminUserDTO);
                aid++;
            } else {
                count--;
            }
        }
        return count;
    }


}
