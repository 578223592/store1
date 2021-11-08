package com.swx.store1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swx.store1.entity.Address;
import com.swx.store1.entity.DictDistrict;
import com.swx.store1.mapper.DictDistrictMapper;
import com.swx.store1.service.impl.AddressServiceImpl;
import com.swx.store1.service.impl.DictDistrictServiceImpl;
import com.swx.store1.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Author: Admin
 * Date: 2021/11/7 20:00
 * FileName: AddressController
 * Description:
 */
@RequestMapping("/address")
@RestController
public class AddressController extends BaseController {
    @Autowired
    AddressServiceImpl addressService;
    @Autowired
    DictDistrictMapper districtMapper;

    @RequestMapping("/add_new_Address")
    public JsonResult<Void> addAddress(Address address, HttpSession session){
        JsonResult<Void> jsonResult = new JsonResult<>();
        Integer uid = getUidInSession(session);
        String username = getUsernameInSession(session);

        addressService.addAddress(address,username,uid);
        jsonResult.setState("200");
        return jsonResult;
    }
}
