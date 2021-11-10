package com.swx.store1.controller;

import com.swx.store1.entity.Address;
import com.swx.store1.mapper.DictDistrictMapper;
import com.swx.store1.service.impl.AddressServiceImpl;
import com.swx.store1.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping("/add_new_address")
    public JsonResult<Void> addAddress(Address address, HttpSession session) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        Integer uid = getUidInSession(session);
        String username = getUsernameInSession(session);

        addressService.addAddress(address, username, uid);
        jsonResult.setState("200");
        return jsonResult;
    }

    @RequestMapping("/get_addresses")
    public JsonResult<List<Address>> getAddresses(HttpSession session) {
        JsonResult<List<Address>> jsonResult = new JsonResult<>();
        Integer uid = getUidInSession(session);
        List<Address> addressList = addressService.showAddress(uid);
        jsonResult.setState("200");
        jsonResult.setData(addressList);
        return jsonResult;
    }

    //restful风格
    @RequestMapping("/set_default_address/{aid}")
    public JsonResult<Void> setDefaultAddress(@PathVariable("aid") Integer aid, HttpSession session){
        JsonResult<Void> jsonResult = new JsonResult<>();
        Integer uid = getUidInSession(session);
        String username = getUsernameInSession(session);
        addressService.setDefaultAddress(aid,uid,username);
        jsonResult.setState("200");
        return jsonResult;
    }

    @RequestMapping("/delete_address/{aid}")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid,HttpSession session){
        JsonResult<Void> jsonResult = new JsonResult<>();
        Integer uid = getUidInSession(session);
        String username = getUsernameInSession(session);
        addressService.deleteAddress(aid,uid,username);
        jsonResult.setState("200");
        return jsonResult;
    }
}
