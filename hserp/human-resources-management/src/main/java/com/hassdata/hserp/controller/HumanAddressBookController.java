package com.hassdata.hserp.controller;

import com.hassdata.hserp.dto.AddressBookModel;
import com.hassdata.hserp.po.HumanAddressBook;
import com.hassdata.hserp.po.HumanDept;
import com.hassdata.hserp.po.HumanEmp;
import com.hassdata.hserp.service.HumanAddressBookService;
import com.hassdata.hserp.service.HumanDeptService;
import com.hassdata.hserp.service.HumanEmpService;
import com.hassdata.hserp.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WTF
 * @DESCRIPTION 通讯录管理
 * @create 2018-02-03 14:58
 **/
@Controller
@RequestMapping("system/human/api")
public class HumanAddressBookController {

    @Autowired
    private HumanAddressBookService humanAddressBookService;
    @Autowired
    private HumanDeptService humanDeptService;
    @Autowired
    private HumanEmpService humanEmpService;

    //获取储备人员列表
    @RequestMapping("getAddressBookList")
    @ResponseBody
    public ServerResponse  getAddressBookList(HumanAddressBook addressBook, @RequestParam(required = false, defaultValue = "1", value = "page") Integer fromIndex, @RequestParam(required = false,defaultValue = "10", value = "limit") Integer pageSize){
        List<HumanAddressBook> humanAddressBooks = null;
        List<AddressBookModel> addressBookModels = null;
        long count = 0;
        try {
            if (!StringUtils.isEmpty(addressBook.getName())) {
                addressBook.setName("%" + addressBook.getName() + "%");
            }
            humanAddressBooks = humanAddressBookService.listLikePage(addressBook, "id ASC", fromIndex, pageSize);
            if (humanAddressBooks.size() > 0){
                List<HumanDept> humanDepts = (List<HumanDept>)humanDeptService.list(null, null);
                Integer index = 0;
                addressBookModels = new ArrayList<>();
                for (HumanAddressBook book: humanAddressBooks) {
                    index++;
                    addressBookModels.add(this.getAddressBookModel(book, index, humanDepts));
                }
            }
            count = humanAddressBookService.countLike(addressBook);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccessForLayuiTable("成功!", addressBookModels, count);
    }

    //根据ID获取通讯录信息
    @RequestMapping("getAddressBookById")
    @ResponseBody
    public ServerResponse  getAddressBookById(Integer id){
        if (id == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        HumanAddressBook addressBook = null;
        AddressBookModel addressBookModel = null;
        try {
            addressBook = humanAddressBookService.getById(id);
            if (addressBook == null){
                return  ServerResponse.createByErrorMessage("通讯录不存在!");
            }
            List<HumanDept> humanDepts = (List<HumanDept>)humanDeptService.list(null, null);
            addressBookModel = this.getAddressBookModel(addressBook, 0, humanDepts);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("系统异常!");
        }
        return ServerResponse.createBySuccess(addressBookModel);
    }

    //更新通讯录信息
    @RequestMapping("updateAddressBook")
    @ResponseBody
    public ServerResponse updateAddressBook(HumanAddressBook addressBook){
        if (addressBook.getId() == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            humanAddressBookService.update(addressBook);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("修改失败!");
        }
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

    //添加储备人员
    @RequestMapping("addAddressBook")
    @ResponseBody
    public ServerResponse addAddressBook(HumanAddressBook addressBook){
        if (addressBook.getName() == null || addressBook.getSex()==null || addressBook.getDeptId() == null || addressBook.getTel() == null || addressBook.getEmail() == null || addressBook.getAddress() == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            //根据通讯录人员姓名、手机获取唯一职员信息
            HumanEmp emp = new HumanEmp();
            emp.setName(addressBook.getName());
            emp.setTel(addressBook.getTel());
            emp = humanEmpService.getOne(emp);
            if (emp == null){
                return  ServerResponse.createByErrorMessage("添加职员信息不存在!，请核实职员信息");
            }
            addressBook.setEid(emp.getId());
            humanAddressBookService.save(addressBook);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("添加失败!");
        }
        return ServerResponse.createBySuccessMessage("添加成功!");
    }

    //删除通讯录信息
    @RequestMapping("deleteAddressBook")
    @ResponseBody
    public ServerResponse deleteAddressBook(Integer id){
        if (id == null){
            return  ServerResponse.createByErrorMessage("必须参数为空!");
        }
        try {
            HumanAddressBook book = humanAddressBookService.getById(id);
            if (book == null) {
                return  ServerResponse.createByErrorMessage("删除通讯录不存在!");
            }
            humanAddressBookService.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("删除失败!");
        }
        return ServerResponse.createBySuccessMessage("删除成功!");
    }
    //封装实体
    private AddressBookModel getAddressBookModel(HumanAddressBook book, Integer index, List<HumanDept> humanDepts){
        AddressBookModel empModel = new AddressBookModel();
        empModel.setIndex(index);
        empModel.setId(book.getId());
        empModel.setEid(book.getEid());
        if (humanDepts.size() >0){
            for (HumanDept dept: humanDepts) {
                if (dept.getId() == book.getDeptId()){
                    empModel.setDeptName(dept.getName());
                    break;
                }
            }
        }
        empModel.setAddress(book.getAddress());
        empModel.setName(book.getName());
        empModel.setTel(book.getTel());
        empModel.setEmail(book.getEmail());
        if (book.getSex() != null){
            if (book.getSex()){
                empModel.setSex("男");
            }else {
                empModel.setSex("女");
            }
        }

        return empModel;
    }
}
